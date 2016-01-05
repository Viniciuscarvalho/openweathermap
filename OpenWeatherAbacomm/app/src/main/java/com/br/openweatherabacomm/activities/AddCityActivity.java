package com.br.openweatherabacomm.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.br.openweatherabacomm.fragments.PlaceFragment;
import com.br.openweatherabacomm.parcelables.PlacesData;
import com.google.android.gms.maps.model.LatLng;

import com.br.openweatherabacomm.R;

public class AddCityActivity extends AppCompatActivity {

    private static final String ARGS = "args";
    private static final String ARG_LAT_LNG = "latLng";

    private PlacesData mPlace;
    private LatLng mLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                onBackPressed();
            }
        });

        mLatLng = null;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddCity);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mLatLng != null) {
                    Bundle args = new Bundle();
                    args.putParcelable(ARG_LAT_LNG, mLatLng);

                    Intent intent = new Intent();
                    intent.putExtra(ARGS, args);
                    setResult(RESULT_OK, intent);
                    onBackPressed();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddCityActivity.this);
                    builder.setMessage("O campo de busca está vazio.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                });

                Dialog dialog = builder.create();
                dialog.show();

                }
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceFragment.newInstance(mPlace))
                .commit();
    }



}