package com.br.openweatherabacomm.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.br.openweatherabacomm.BD.WeatherApplication;
import com.br.openweatherabacomm.R;
import com.br.openweatherabacomm.fragments.PlaceFragment;
import com.br.openweatherabacomm.fragments.WeatherFragment;
import com.br.openweatherabacomm.parcelables.PlaceParcelable;

/**
 * Created by viniciuscarvalho on 07/01/16.
 */
public class PlacesPageAdapter extends FragmentPagerAdapter{

    private PlaceFragment placesFragment;
    private WeatherFragment weatherFragment;

    public PlacesPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return (position == 0) ? PlaceFragment.newInstance()
                : WeatherFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (position == 0) ? WeatherApplication.getInstance().getString(R.string.places)
                : WeatherApplication.getInstance().getString(R.string.weather);
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
