package com.br.openweatherabacomm.BD;

import com.orm.SugarApp;
/**
 * Created by viniciuscarvalho on 07/01/16.
 */
public class WeatherApplication extends SugarApp {

    private static WeatherApplication instance;

    public WeatherApplication() {
        instance = this;
    }

    public static synchronized WeatherApplication getInstance() {
        if (instance == null) {
            new WeatherApplication();
        }

        return instance;
    }
}
