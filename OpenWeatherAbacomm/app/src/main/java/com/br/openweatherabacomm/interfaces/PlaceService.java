package com.br.openweatherabacomm.interfaces;

import com.br.openweatherabacomm.parcelables.Places;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by viniciuscarvalho on 30/12/15.
 */
public interface PlaceService {
    @GET("/weather/?q=Fortaleza,ce/{id}")
    Call<List<Places>> listPlaces(@Path("id") int id);
}
