package edu.tequila.tecmm.exampleitny;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentsMovieActivity extends AppCompatActivity {

    @BindView(R.id.txtTitleComments)
    TextView title;
    @BindView(R.id.txtComment)
    EditText txtComment;

    ProgressDialog dialog;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_movie);
        ButterKnife.bind(this);

        Intent i = getIntent();
        title.setText(i.getStringExtra("name"));
        id=i.getIntExtra("id",-1);
    }

    @OnClick(R.id.btnAdd)
    public void addComment(){
        if(id>0)
        {
            String cad = txtComment.getText().toString();
            sendComment(cad);
        }
    }

    public void closeActivity() {
        this.finish();
    }

    public void sendComment(String cad){
        dialog = ProgressDialog.show(this,"","Sending.....",true);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetroInterfaceWs.url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        final RetroInterfaceWs request = retrofit.create(RetroInterfaceWs.class);

        DtoComments dto = new DtoComments();
        dto.setId(id);
        dto.setComments(cad);
        Call<DtoResult> requestData = request.addComments(dto);

        requestData.enqueue(new Callback<DtoResult>() {
            @Override
            public void onResponse(Call<DtoResult> call, Response<DtoResult> response) {
                if(!response.isSuccessful())
                    Toast.makeText(CommentsMovieActivity.this,"Request is Wrong",Toast.LENGTH_LONG).show();
                DtoResult result = response.body();
                dialog.dismiss();
                String cadResult = "";
                if(!result.isResult())
                    cadResult = "WARNING: Couldn't add comment";
                else
                    cadResult = "Added Comment";
                Toast.makeText(CommentsMovieActivity.this,cadResult,Toast.LENGTH_LONG).show();

                closeActivity();
            }

            @Override
            public void onFailure(Call<DtoResult> call, Throwable t) {
                Toast.makeText(CommentsMovieActivity.this,"Server not found",Toast.LENGTH_LONG).show();
            }
        });
    }

}
