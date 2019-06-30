package com.teztour.zahranrxdownloadandcachelib;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Mahmoud Zahran on 27/06/2019.
 */
public interface ApiService {
    @GET("placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5/u0026q=80/u0026fm=jpg/u0026crop=faces/u0026fit=crop/u0026h=32/u0026w=32/u0026s=46caf91cf1f90b8b5ab6621512f102a8/")
    Observable<String> getApiData();

    @POST(".")
    Observable<String> getApiDataPost();
}
