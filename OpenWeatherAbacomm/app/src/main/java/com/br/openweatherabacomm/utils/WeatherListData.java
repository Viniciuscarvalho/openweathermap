package com.br.openweatherabacomm.utils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viniciuscarvalho on 30/12/15.
 */
public class WeatherListData {

    @SerializedName("list")
    public List<WeatherParams> list;

}