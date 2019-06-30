package com.teztour.zahranrxdownloadandcachelib;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Mahmoud Zahran on 27/06/2019.
 */
public interface ApiService {
    @GET(".")
    Observable<String> getApiData();

    @POST(".")
    Observable<String> getApiDataPost();
}
