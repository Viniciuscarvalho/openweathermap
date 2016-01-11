package com.br.openweatherabacomm.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.br.openweatherabacomm.fragments.PlaceFragment;
import com.br.openweatherabacomm.fragments.WeatherFragment;
import com.br.openweatherabacomm.parcelables.PlaceParcelable;
import com.br.openweatherabacomm.parcelables.WeatherParcelable;

/**
 * Created by viniciuscarvalho on 10/01/16.
 */
public class TabsAdapter extends FragmentPagerAdapter{

    private static final int PLACES = 0;
    private static final int WEATHER = 1;

    private PlaceFragment placesFragment;
    private WeatherFragment weatherFragment;

    private PlaceParcelable mPlaceParcel;
    private WeatherParcelable mWeatherParcel;

    public TabsAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);

    }

    @Override
    public Fragment getItem (int position) {
        switch (position) {
            case PLACES:
                return PlaceFragment.newInstance(mPlaceParcel);
            case WEATHER:
                return WeatherFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public CharSequence getPageTitle (int position) {
        switch (position) {
            case PLACES:
                return "Locais";
            case WEATHER:
                return "Tempo";
        }
        return null;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        if (position == 0) placesFragment = (PlaceFragment) fragment;
        if (position == 1) weatherFragment = (WeatherFragment) fragment;
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (position == 0) placesFragment = null;
        if (position == 1) weatherFragment = null;
        super.destroyItem(container, position, object);
    }

    public void updatePlacesList() {
        if (placesFragment != null)
            placesFragment.updatePlacesList();
    }

//    public void updateWeatherDetail() {
//        if (weatherFragment != null)
//            weatherFragment.updateWeatherDetail();
//    }

    public void updateWeatherDetail(PlaceParcelable mPlace) {
        if (weatherFragment != null)
            weatherFragment.updateWeatherDetail(mPlace);
    }

}
