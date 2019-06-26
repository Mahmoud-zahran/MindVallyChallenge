package com.teztour.zahranrxdownloadandcachelib;

import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET(ApiUrls.API_FEEDS_URL)
    Observable<MDownloadDataType> getApiData();
}
