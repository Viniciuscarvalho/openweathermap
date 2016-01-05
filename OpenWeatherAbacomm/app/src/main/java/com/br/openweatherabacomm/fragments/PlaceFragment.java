package com.br.openweatherabacomm.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.br.openweatherabacomm.R;
import com.br.openweatherabacomm.adapters.PlaceAdapter;
import com.br.openweatherabacomm.interfaces.PlaceService;
import com.br.openweatherabacomm.parcelables.PlacesData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class PlaceFragment extends Fragment {

    private static final String ARG_PLACES = "places";

    private RecyclerView mRecyclerView;
    private Button mButton;
    private PlaceAdapter mAdapter;
    private PlacesData placesData;
    private ArrayList<PlacesData> mPlaces = new ArrayList<>();

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

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewPlaces);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //Adicionar nova cidade via mapa
        mButton = (Button) view.findViewById(R.id.action_add);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        refreshList();
        return view;
    }

    private void refreshList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.web_services))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceService service = retrofit.create(PlaceService.class);
        Call<List<PlacesData>> placesCall = service.listPlaces(placesData.getId());
        placesCall.enqueue(new Callback<List<PlacesData>>() {
            @Override
            public void onResponse(Response<List<PlacesData>> response, Retrofit retrofit) {
                mPlaces.clear();
                for (PlacesData placesData : response.body()) {
                    mPlaces.add(placesData);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


    public void onClick(View view) {
        //TODO IMPLEMENTAR FUNÇÃO DE DELETAR DA LISTA
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
