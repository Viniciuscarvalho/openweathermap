package com.br.openweatherabacomm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.openweatherabacomm.R;
import com.br.openweatherabacomm.parcelables.PlaceParcelable;
import com.br.openweatherabacomm.utils.WeatherListData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by viniciuscarvalho on 26/12/15.
 */
public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private Context mContext;
    private View.OnClickListener mListener;
    private ArrayList<PlaceParcelable> mPlaces = new ArrayList<>();

    public PlaceAdapter(List<PlaceParcelable> placeList) {

    }

    public Object getItem(int position) {
        return mPlaces.get(position);
    }

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
                        ArrayList<PlaceParcelable> placesList) {
        mContext = context;
        mListener = listener;
        mPlaces = placesList;
    }

    @Override
    public PlaceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_places, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PlaceParcelable placesList = (PlaceParcelable) getItem(position);

        Picasso.with(mContext)
                .load(mContext.getString(R.string.web_services) + "/weather" + "/icon")
                .placeholder(R.drawable.ic_menu_gallery)
                .into(holder.mPlacesImage);

        holder.mPlacesName.setText(placesList.getCity() + ", " + placesList.getCountry());
        holder.mPlacesTemperature.setText(String.format(Locale.getDefault(), "%.1fº", placesList.getWeather().getTemperature()));
        holder.mPlacesConditions.setText(placesList.getWeather().getDescription());

    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }
}
