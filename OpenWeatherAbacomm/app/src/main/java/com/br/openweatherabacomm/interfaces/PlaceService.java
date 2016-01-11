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
    @GET("group?id={id}&units=metric&appid={appid}")
        Call<List<WeatherListData>> listPlaces(@Path("id") int id,
                                          @Path("appid") String appId);

}
