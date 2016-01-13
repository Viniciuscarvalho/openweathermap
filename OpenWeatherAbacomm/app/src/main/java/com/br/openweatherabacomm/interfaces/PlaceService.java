package com.br.openweatherabacomm.interfaces;

import com.br.openweatherabacomm.utils.WeatherListData;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by viniciuscarvalho on 30/12/15.
 */
public interface PlaceService {
    @GET("data/2.5/group?")
    Call<String> listPlaces(@Query("id") long id,
                            @Query("units") String metric,
                            @Query("appid") String appId);

}
