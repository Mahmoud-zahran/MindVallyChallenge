package com.teztour.zahranrxdownloadandcachelib;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Mahmoud Zahran on 27/06/2019.
 */
public interface ApiService {
    @GET
    Observable<ResponseBody> getApiData(@Url String url);

    @POST(".")
    Observable<String> getApiDataPost();
}
