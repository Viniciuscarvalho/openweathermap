package com.br.openweatherabacomm.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.br.openweatherabacomm.BuildConfig;
import com.br.openweatherabacomm.R;
import com.br.openweatherabacomm.activities.AddPlaceActivity;
import com.br.openweatherabacomm.adapters.PlaceAdapter;
import com.br.openweatherabacomm.interfaces.PlaceService;
import com.br.openweatherabacomm.parcelables.PlaceParcelable;
import com.br.openweatherabacomm.utils.WeatherListData;
import com.google.android.gms.location.places.Place;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


public class PlaceFragment extends Fragment {

    private static final String ARG_PLACES = "places";
    SearchView searchView;

    private RecyclerView mRecyclerView;
    private Button mButton;
    private PlaceAdapter mAdapter;
    private WeatherListData weatherListData;
    private ArrayList<PlaceParcelable> mPlaces = new ArrayList<>();

    private PlaceInteractionListener mPlaceListener;

    public PlaceFragment() {
    }

    public static PlaceFragment newInstance() {
        return new PlaceFragment();
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
        View view = inflater.inflate(R.layout.fragment_place, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewPlaces);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        refreshList();

        return view;
    }

    public void updatePlacesList() {
        String filter = "";

        if (searchView != null) {
            filter = searchView.getQuery().toString();
        }

        updatePlacesList(PlaceParcelable.filterByCity(filter));
    }

    private void updatePlacesList(List<PlaceParcelable> placeList) {
        if (placeList.size() > 0) {
            mRecyclerView.setAdapter(new PlaceAdapter(placeList));
        } else {
            mRecyclerView.setAdapter(null);

        }
    }

    private void refreshList() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.web_services))
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();


//        PlaceService service = retrofit.create(PlaceService.class);
//        Call<List<WeatherListData>> placesCall = service.listPlaces();
//        placesCall.enqueue(new Callback<List<WeatherListData>>() {
//            @Override
//            public void onResponse(Response<List<WeatherListData>> response, Retrofit retrofit) {
//                mPlaces.clear();
//
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//
//            }
//        });
    }


    public void onButtonPressed(Uri uri) {
        if (mPlaceListener != null) {
            mPlaceListener.PlaceFragmentInteraction(uri);
        }
    }

    public interface PlaceInteractionListener {
        void PlaceFragmentInteraction(Uri uri);
    }

}
