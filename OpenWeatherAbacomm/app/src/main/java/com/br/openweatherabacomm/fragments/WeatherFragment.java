package com.br.openweatherabacomm.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.openweatherabacomm.R;
import com.br.openweatherabacomm.parcelables.PlaceParcelable;
import com.br.openweatherabacomm.parcelables.WeatherParcelable;

import java.text.SimpleDateFormat;

public class WeatherFragment extends Fragment {

    private PlaceParcelable mPlace;
    private WeatherInteractionListener mWeatherListener;

    public WeatherFragment() {
    }

    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mWeatherListener = (WeatherInteractionListener) getActivity();
    }

    public void updateWeatherDetail(PlaceParcelable mPlace) {
        if (mPlace != null) {

            updateWeatherDetail(PlaceParcelable.findById(PlaceParcelable.class, mPlace.getOwId()));
        }
    }

    public void onButtonPressed(WeatherParcelable weatherParcelable) {
        if (mWeatherListener != null) {
            mWeatherListener.WeatherFragmentInteraction(weatherParcelable);
        }
    }

    public interface WeatherInteractionListener {
        void WeatherFragmentInteraction(WeatherParcelable weatherParcelable);
    }
}
