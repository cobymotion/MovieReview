package edu.tequila.tecmm.exampleitny;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DescriptionMovie extends AppCompatActivity {

    private String name;
    private int id;
    ProgressDialog dialog;
    private DataMovie movie;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.collapser)
    CollapsingToolbarLayout collapser;

    @BindView(R.id.image_background)
    ImageView background;

    @BindView(R.id.txtComments)
    TextView txtComments;

    @BindView(R.id.txtSynopsis)
    TextView txtSynopsis;

    public static void createInstance(Activity activity, DataMovie movie){
        Intent intent = getLaunchIntent(activity, movie);
        activity.startActivity(intent);
    }

    private static Intent getLaunchIntent(Activity activity, DataMovie movie) {
        Intent intent = new Intent(activity, DescriptionMovie.class);
        intent.putExtra("movieName",movie.getName());
        intent.putExtra("movieImg",movie.getIdDrawable());
        intent.putExtra("movieId", movie.getId());
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_movie);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();

        name = i.getStringExtra("movieName");
        //int idDrawable = i.getIntExtra("movieImg", -1);
        id = i.getIntExtra("movieId",-1);

        collapser.setTitle(name);
        // load image ....

      /*  background.setImageDrawable(ContextCompat.getDrawable
                (this,idDrawable));
                */
    }

    public void fillEnviroment() {
        if(movie!=null){
            if(movie.checkImage())
            background.setImageDrawable(ContextCompat.getDrawable
                    (this,movie.getIdDrawable()));
            else
                Picasso.with(this).load(RetroInterfaceWs.url + movie.getPathImg()).into(background);
            txtComments.setText(movie.getComments());
            txtSynopsis.setText(movie.getPlot());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(id<1)
            Toast.makeText(this,"Connection error: Data no found", Toast.LENGTH_LONG).show();
        else {
            dialog = ProgressDialog.show(this,"","Loading ....",true);
            Retrofit retrofit = new Retrofit.Builder().baseUrl(RetroInterfaceWs.url)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            RetroInterfaceWs request = retrofit.create(RetroInterfaceWs.class);
            DataMovie dtoMovie = new DataMovie();
            dtoMovie.setId(id);
            Call<DataMovie> requestData = request.getDetails(dtoMovie);
            requestData.enqueue(new Callback<DataMovie>() {
                @Override
                public void onResponse(Call<DataMovie> call, Response<DataMovie> response) {
                    if(!response.isSuccessful())
                        Toast.makeText(DescriptionMovie.this,"Request Wrong!",Toast.LENGTH_LONG)
                                .show();
                    movie = response.body();
                    dialog.dismiss();
                    fillEnviroment();
                }

                @Override
                public void onFailure(Call<DataMovie> call, Throwable t) {
                    Toast.makeText(DescriptionMovie.this,"Server not found!",Toast.LENGTH_LONG)
                            .show();
                    t.printStackTrace();
                }
            });
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab_add)
    public void addComments(){

        Intent i = new Intent(this, CommentsMovieActivity.class);
        i.putExtra("name",name);
        i.putExtra("id",movie.getId());
        startActivity(i);

    }
}
