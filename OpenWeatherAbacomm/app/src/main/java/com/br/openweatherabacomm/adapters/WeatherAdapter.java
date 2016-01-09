package com.br.openweatherabacomm.adapters;

import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.openweatherabacomm.R;
import com.br.openweatherabacomm.fragments.PlaceFragment;
import com.br.openweatherabacomm.parcelables.PlaceParcelable;
import com.br.openweatherabacomm.parcelables.WeatherParcelable;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by viniciuscarvalho on 08/01/16.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder>{

    private WeatherParcelable mWeather;
    private PlaceParcelable mPlace;
    private Context mContext;
    private View.OnClickListener mListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgWeather;
        public TextView mNameCity;
        public TextView mTemperature;
        public TextView mHumidity;
        public TextView mPressure;
        public TextView mWindDirection;
        public TextView mWindSpeed;

        public ViewHolder(View view) {
            super(view);

            imgWeather = (ImageView) view.findViewById(R.id.imgWeather);
            mNameCity = (TextView) view.findViewById(R.id.detailsNameCity);
            mTemperature = (TextView) view.findViewById(R.id.valueTemperature);
            mHumidity = (TextView) view.findViewById(R.id.valueHumidity);
            mPressure = (TextView) view.findViewById(R.id.valuePressure);
            mWindDirection = (TextView) view.findViewById(R.id.valueDirection);
            mWindSpeed = (TextView) view.findViewById(R.id.valueVelocity);

        }
    }

    public WeatherAdapter(Context context, View.OnClickListener listener,
                          WeatherParcelable weather, PlaceParcelable place) {
        mContext = context;
        mListener = listener;
        mWeather = weather;
        mPlace = place;

    }

    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.details_weather, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherAdapter.ViewHolder holder, int position) {

        Picasso.with(mContext)
                .load(mContext.getString(R.string.web_services) + "/weather" + "/icon")
                .placeholder(R.drawable.ic_menu_gallery)
                .into(holder.imgWeather);

        holder.mNameCity.setText(mPlace.getCity());
        holder.mTemperature.setText(String.format("%.1f%s", mWeather.getTemperature(), "ºC"));
        holder.mHumidity.setText(String.format("%s%%", mWeather.getHumidity()));
        holder.mPressure.setText(String.format("%.2f hPa", mWeather.getPressure()));
        holder.mWindDirection.setText(String.format("%.2f", mWeather.getWindDegree()));
        holder.mWindSpeed.setText(String.format("%.2f %s", mWeather.getWindSpeed(), "m/s"));

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
