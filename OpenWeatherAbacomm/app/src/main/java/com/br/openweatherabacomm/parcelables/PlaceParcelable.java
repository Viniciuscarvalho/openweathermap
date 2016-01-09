package com.br.openweatherabacomm.parcelables;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

/**
 * Created by viniciuscarvalho on 07/01/16.
 */
public class PlaceParcelable extends BaseParcelable<PlaceParcelable> implements Parcelable{

    private Long owId;
    private String city;
    private String country;
    private Double latitude;
    private Double longitude;
    private WeatherParcelable weather;

    public PlaceParcelable() {
        weather = new WeatherParcelable();
    }

    private PlaceParcelable(Parcel from) {
        setOwId(from.readLong());
        setCity(from.readString());
        setCountry(from.readString());
        setLatitude(from.readDouble());
        setLongitude(from.readDouble());
        setWeather((WeatherParcelable) from.readSerializable());
    }

    public static final Parcelable.Creator<PlaceParcelable>
            CREATOR = new Parcelable.Creator<PlaceParcelable>() {

        public PlaceParcelable createFromParcel(Parcel in) {
            return new PlaceParcelable(in);
        }

        public PlaceParcelable[] newArray(int size) {
            return new PlaceParcelable[size];
        }
    };

    public Long getOwId() {
        return owId;
    }

    public void setOwId(Long owId) {
        this.owId = owId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public WeatherParcelable getWeather() {
        return weather;
    }

    public void setWeather(WeatherParcelable weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "PlaceParcelable{" +
                "owId='" + getOwId() + '\'' +
                ", city=" + getCity() +
                ", country='" + getCountry() + '\'' +
                ", latitude=" + getLatitude() +
                ", longitude=" + getLongitude() +
                ", weather=" + getWeather() +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(getOwId());
        dest.writeString(getCity());
        dest.writeString(getCountry());
        dest.writeDouble(getLatitude());
        dest.writeDouble(getLongitude());
    }

    public static List<PlaceParcelable> filterByCity(String city) {
        return Select.from(PlaceParcelable.class).where(Condition.prop("city")
                .like("%" + city + "%")).list();
    }

    public static PlaceParcelable findByOwId(long owId) {
        return Select.from(PlaceParcelable.class).where(Condition.prop("ow_id")
                .eq(String.valueOf(owId))).first();
    }
}
