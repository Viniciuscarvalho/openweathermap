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
import com.br.openweatherabacomm.utils.ToStringConverterFactory;
import com.br.openweatherabacomm.utils.WeatherListData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class PlaceFragment extends Fragment {

    private static final String ARG_PLACES = "places";
    SearchView searchView;

    private RecyclerView mRecyclerView;
    private PlaceAdapter mAdapter;
    private WeatherListData weatherListData;
    private PlaceParcelable mPlaceParcel;
    private List<WeatherListData> mWeatherList = new ArrayList<>();

    private PlaceInteractionListener mPlaceListener;

    public PlaceFragment() {
    }

    public static PlaceFragment newInstance(PlaceParcelable mPlaceParcel) {
        PlaceFragment fragment = new PlaceFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PLACES, mPlaceParcel);
        fragment.setArguments(args);
        return new PlaceFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPlaceParcel = getArguments().getParcelable(ARG_PLACES);
        } else if (savedInstanceState != null) {
            mPlaceParcel = savedInstanceState.getParcelable(ARG_PLACES);
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
                .addConverterFactory(ToStringConverterFactory.create())
                .build();

        PlaceService service = retrofit.create(PlaceService.class);
        Call<String> placesCall = service.listPlaces(524901, "metric", "2de143494c0b295cca9337e1e96b00e0");

        placesCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {

                if (response.body() != null){
                    WeatherListData weatherListData = new Gson().fromJson(response.body(), WeatherListData.class);

                    if (weatherListData != null){

                    }
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

//    private void teste(){
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://api.openweathermap.org")
//                .addConverterFactory(ToStringConverterFactory.create())
//                .build();
//
//        Foursquare.PlaceService service = retrofit.create(Foursquare.PlaceService.class);
//        Call<String> placesCall = service.listPlaces(524901, "metric", "2de143494c0b295cca9337e1e96b00e0");
//
//        placesCall.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Response<String> response, Retrofit retrofit) {
//
//                if (response.body() != null){
//                    WeatherListData weatherListData = new Gson().fromJson(response.body(), WeatherListData.class);
//
//                    if (weatherListData != null){
//
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                if (t != null){
//
//                }
//            }
//        });
//    }


    public void onButtonPressed(Uri uri) {
        if (mPlaceListener != null) {
            mPlaceListener.PlaceFragmentInteraction(uri);
        }
    }

    public interface PlaceInteractionListener {
        void PlaceFragmentInteraction(Uri uri);
    }

}
