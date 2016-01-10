package com.br.openweatherabacomm.utils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by viniciuscarvalho on 07/01/16.
 */

public class WeatherParams {

    @SerializedName("coord")
    public Coord coordinates;

    @SerializedName("sys")
    public Sys system;

    @SerializedName("weather")
    public List<Weather> weathers;

    @SerializedName("main")
    public Main main;

    @SerializedName("wind")
    public Wind wind;

    @SerializedName("dt")
    public long timestamp;

    public long id;

    public String name;

    @SerializedName("cod")
    public int retCode;

    public static class Coord {
        @SerializedName("lon")
        public double longitude;
        @SerializedName("lat")
        public double latitude;
    }

    public static class Sys {
        public String country;
    }

    public static class Weather {
        @SerializedName("id")
        public int code;
        public String icon;
    }

    public static class Main {
        public double temp;
        public double pressure;
        public int humidity;
    }

    public static class Wind {
        public double speed;
        @SerializedName("deg")
        public double degree;
    }
}

