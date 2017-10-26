package edu.tequila.tecmm.exampleitny;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Description:
 * Copyright 2017 TecMM Tequila
 * Created by luiscobian on 10/24/17.
 * Edit by ---- on 10/24/17
 */

public interface RetroInterfaceWs {

    //public static final String url="http://192.168.1.101/testing/ny/";
    public static final String url="http://devtequila.com/itny/services/";

    @GET("lista.php")
    Call<List<DataMovie>> listMovies();

    @POST("details.php")
    Call<DataMovie> getDetails(@Body DataMovie idJson);

    @POST("add.php")
    Call<DtoResult> addComments(@Body DtoComments comment);
}
