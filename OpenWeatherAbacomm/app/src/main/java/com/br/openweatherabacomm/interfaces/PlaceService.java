package com.br.openweatherabacomm.interfaces;

import com.br.openweatherabacomm.parcelables.PlacesData;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by viniciuscarvalho on 30/12/15.
 */
public interface PlaceService {
    @GET("/data/2.5/group")
        Call<List<PlacesData>> listPlaces(@Path("id") int id);

}
