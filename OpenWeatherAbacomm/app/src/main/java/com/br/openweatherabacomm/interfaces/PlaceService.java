package com.br.openweatherabacomm.interfaces;

import com.br.openweatherabacomm.utils.WeatherListData;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by viniciuscarvalho on 30/12/15.
 */
public interface PlaceService {
    @GET("group?{id}&units=metric&{appid}")
        Call<List<WeatherListData>> listPlaces(@Path("id") int id,
                                          @Path("appId") String appId);

}
