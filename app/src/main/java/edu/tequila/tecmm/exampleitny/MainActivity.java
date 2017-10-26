package edu.tequila.tecmm.exampleitny;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    private LinearLayoutManager llm;
    public ProgressDialog dialogWaiting;
    List<DataMovie> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle("My Movies");

        /// Provisional



        List<DataMovie> datos  = new ArrayList<>();
        datos.add(new DataMovie("Harry Potter I"));
        datos.add(new DataMovie("Harry Potter II"));
        datos.add(new DataMovie("Harry Potter III"));
        datos.add(new DataMovie("Harry Potter IV"));
        datos.add(new DataMovie("Harry Potter V"));
        datos.add(new DataMovie("Harry Potter VI"));
        datos.add(new DataMovie("Harry Potter VII"));
        datos.add(new DataMovie("Harry Potter VIII Part 1"));
        datos.add(new DataMovie("Harry Potter VIII Part 2"));
        datos.add(new DataMovie("American Made"));
        datos.add(new DataMovie("Blade Runner 2049"));





    }

    public void fillRecycler() {
        if(datas!=null){
            AdapterRecyclerSimple adapter = new AdapterRecyclerSimple(this, datas);
            llm = new LinearLayoutManager(this);
            recycler.setLayoutManager(llm);
            recycler.setAdapter(adapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        dialogWaiting = ProgressDialog.show(this,"", "Waiting for data", true);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetroInterfaceWs.url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        RetroInterfaceWs request = retrofit.create(RetroInterfaceWs.class);
        Call<List<DataMovie>> requestDatas = request.listMovies();
        requestDatas.enqueue(new Callback<List<DataMovie>>() {
            @Override
            public void onResponse(Call<List<DataMovie>> call, Response<List<DataMovie>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Request wrong!",Toast.LENGTH_LONG)
                            .show();
                }
                datas = response.body();
                dialogWaiting.dismiss();
                fillRecycler();
            }

            @Override
            public void onFailure(Call<List<DataMovie>> call, Throwable t) {
                datas = null;
                Toast.makeText(MainActivity.this,"Server not found!",Toast.LENGTH_LONG)
                        .show();
                t.printStackTrace();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
