package com.br.openweatherabacomm.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.br.openweatherabacomm.R;
import com.br.openweatherabacomm.adapters.TabsAdapter;
import com.br.openweatherabacomm.fragments.PlaceFragment;
import com.br.openweatherabacomm.fragments.TabsFragment;
import com.br.openweatherabacomm.fragments.WeatherFragment;
import com.br.openweatherabacomm.parcelables.PlaceParcelable;
import com.br.openweatherabacomm.parcelables.WeatherParcelable;

public class MainActivity extends AppCompatActivity
        implements PlaceFragment.PlaceInteractionListener, WeatherFragment.WeatherInteractionListener {

    protected ViewPager pager;

    private PlaceFragment placesFragment;
    private WeatherFragment weatherFragment;
    private TabsAdapter pagerAdapter;

    private PlaceParcelable mPlaceParcel;
    private WeatherParcelable mWeatherParcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TabsFragment.newInstance())
                .commit();

        loadListCities();

    }

    private void loadListCities() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, PlaceFragment.newInstance(mPlaceParcel))
                .commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, WeatherFragment.newInstance())
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (!ListCity()) {
            if (pager.getCurrentItem() == 0) {
                super.onBackPressed();
            } else {
                pager.setCurrentItem(pager.getCurrentItem() -1);
            }
        }
    }

    public void onPlaceClicked(PlaceParcelable placeParcelable) {
        if (ListCity()) {
            weatherFragment.updateWeatherDetail(placeParcelable);
        } else {
            pagerAdapter.updateWeatherDetail(placeParcelable);
            pager.setCurrentItem(1);
        }
    }

    public void onPlaceLongClick(final PlaceFragment placeFragment) {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setMessage("Você gostaria de excluir esta cidade?");
        dialog.setCancelable(true);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int buttonId) {


                //placeFragment.delete();
//
//                if (ListCity()) {
//                    placesFragment.updatePlacesList();
//                    weatherFragment.updateWeatherDetail();
//                } else {
//                    pagerAdapter.updatePlacesList();
//                    pagerAdapter.updateWeatherDetail();
//                }
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int buttonId) {

            }
        });

        dialog.show();
    }

    protected boolean ListCity() {
        return getResources().getBoolean(R.bool.ListCity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void PlaceFragmentInteraction(Uri uri) {

    }

    @Override
    public void WeatherFragmentInteraction(WeatherParcelable weatherParcelable) {

    }
}
