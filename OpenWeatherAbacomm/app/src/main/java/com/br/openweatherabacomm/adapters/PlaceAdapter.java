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
import com.br.openweatherabacomm.parcelables.WeatherParcelable;
import com.br.openweatherabacomm.utils.WeatherListData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by viniciuscarvalho on 26/12/15.
 */
public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlacesViewHolder> {

    private Context mContext;
    private PlaceOnClickListener mPlaceListener;
    private List<PlaceParcelable> mPlaces = new ArrayList<PlaceParcelable>();

    public Object getItem(int position) {
        return mPlaces.get(position);
    }

    public PlaceAdapter(List<PlaceParcelable> places, Context context, PlaceOnClickListener mPlaceListener) {
        mContext = context;
        mPlaceListener = mPlaceListener;
        mPlaces = places;

    }

    public static class PlacesViewHolder extends RecyclerView.ViewHolder {
        public ImageView mPlacesImage;
        public TextView mPlacesName;
        public TextView mPlacesTemperature;
        public TextView mPlacesConditions;

        public PlacesViewHolder(View view) {
            super(view);

            mPlacesImage = (ImageView) view.findViewById(R.id.itemplace_image);
            mPlacesName = (TextView) view.findViewById(R.id.itemplace_name);
            mPlacesTemperature = (TextView) view.findViewById(R.id.itemplace_temperature);
            mPlacesConditions = (TextView) view.findViewById(R.id.itemplace_conditions);
        }
    }


    @Override
    public PlacesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_places, parent, false);
        PlacesViewHolder holder = new PlacesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final PlacesViewHolder holder, final int position) {
        PlaceParcelable places = (PlaceParcelable) getItem(position);

        Picasso.with(mContext)
                .load(mContext.getString(R.string.web_services) + "/weather" + "/icon")
                .placeholder(R.drawable.ic_menu_gallery)
                .into(holder.mPlacesImage);

        holder.mPlacesName.setText(places.getCity() + ", " + places.getCountry());
        holder.mPlacesTemperature.setText(String.format(Locale.getDefault(), "%.1fº", places.getWeather().getTemperature()));
        holder.mPlacesConditions.setText(places.getWeather().getDescription());

        if(mPlaceListener != null) {
            holder.mPlacesName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPlaceListener.onClickPlace(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public interface PlaceOnClickListener {
        public void onClickPlace(View view, int owId);
    }
}
