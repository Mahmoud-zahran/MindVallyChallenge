package com.teztour.zahranrxdownloadandcachelib;

import io.reactivex.Observable;
import retrofit2.http.GET;
/**
 * Created by Mahmoud Zahran on 27/06/2019.
 */
public interface ApiService {
    @GET(".")
    Observable<String> getApiData();
}
