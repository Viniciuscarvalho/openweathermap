package com.br.openweatherabacomm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.openweatherabacomm.R;
import com.br.openweatherabacomm.parcelables.PlacesData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by viniciuscarvalho on 26/12/15.
 */
public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private Context mContext;
    private View.OnClickListener mListener;
    private ArrayList<PlacesData> mPlaces = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mPlacesImage;
        public TextView mPlacesName;
        public TextView mPlacesTemperature;
        public TextView mPlacesConditions;

        public ViewHolder(View view) {
            super(view);

            mPlacesImage = (ImageView) view.findViewById(R.id.itemplace_image);
            mPlacesName = (TextView) view.findViewById(R.id.itemplace_name);
            mPlacesTemperature = (TextView) view.findViewById(R.id.itemplace_temperature);
            mPlacesConditions = (TextView) view.findViewById(R.id.itemplace_conditions);
        }
    }

    public PlaceAdapter(Context context, View.OnClickListener listener,
                        ArrayList<PlacesData> places) {
        mContext = context;
        mListener = listener;
        mPlaces = places;
    }

    @Override
    public PlaceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_places, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PlacesData places = mPlaces.get(position);

        Picasso.with(mContext)
                .load(mContext.getString(R.string.web_services) + "/weather" + "/icon")
                .placeholder(R.drawable.ic_menu_gallery)
                .into(holder.mPlacesImage);

        holder.mPlacesName.setText(places.getName());
        holder.mPlacesTemperature.setText(places.getTemperature());
        holder.mPlacesConditions.setText(places.getConditions());

    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }
}
