package com.br.openweatherabacomm.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.openweatherabacomm.R;
import com.br.openweatherabacomm.adapters.PlaceAdapter;
import com.br.openweatherabacomm.parcelables.Places;

import java.util.ArrayList;

import retrofit.Retrofit;


public class PlaceFragment extends Fragment {

    private static final String ARG_PLACES = "places";

    private RecyclerView mRecyclerView;

    private PlaceAdapter mAdapter;
    private ArrayList<Places> mPlaces = new ArrayList<>();

    private OnPlacesFragmentInteractionListener mListener;

    public PlaceFragment() {
    }


    public static PlaceFragment newInstance(Parcelable places) {
        PlaceFragment fragment = new PlaceFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PLACES, places);
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
        View view = inflater.inflate(R.layout.fragment_place, container, false);

        return view;
    }

    private void refreshList() {
        //TODO IMPLEMENTAR FUNÇÃO DE CHAMADA DE TODOS AS CIDADES
    }

    public void onClick(View view) {

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnPlacesFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
