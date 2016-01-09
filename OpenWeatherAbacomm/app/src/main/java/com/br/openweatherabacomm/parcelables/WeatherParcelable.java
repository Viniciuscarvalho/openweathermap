package com.br.openweatherabacomm.parcelables;

import android.os.Parcel;
import android.os.Parcelable;

import com.br.openweatherabacomm.BD.WeatherApplication;

import java.util.Date;

/**
 * Created by viniciuscarvalho on 07/01/16.
 */
public class WeatherParcelable extends BaseParcelable<WeatherParcelable> implements Parcelable{

    private int code;
    private String icon;
    private double temperature;
    private int humidity;
    private double pressure;
    private double windDegree;
    private double windSpeed;
    private Date lastUpdate;

    public WeatherParcelable() {
        lastUpdate = new Date();
    }

    private WeatherParcelable(Parcel from) {
        setCode(from.readInt());
        setIcon(from.readString());
        setTemperature(from.readDouble());
        setHumidity(from.readInt());
        setPressure(from.readDouble());
        setWindDegree(from.readDouble());
        setWindSpeed(from.readDouble());
        setLastUpdate((Date) from.readSerializable());
    }

    public static final Parcelable.Creator<WeatherParcelable>
            CREATOR = new Parcelable.Creator<WeatherParcelable>() {

        public WeatherParcelable createFromParcel(Parcel in) {
            return new WeatherParcelable(in);
        }

        public WeatherParcelable[] newArray(int size) {
            return new WeatherParcelable[size];
        }
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(double windDegree) {
        this.windDegree = windDegree;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getDescription() {
        String description = "";

                if (icon != null && icon.length() == 3) {
                    description = String.format("condition_%s", icon.substring(0, 2));
                    description = WeatherApplication.getInstance().getString(
                            WeatherApplication.getInstance().getResources()
                            .getIdentifier(description, "string",
                                    WeatherApplication.getInstance().getPackageName()));

                }
        return description;
    }

    @Override
    public String toString() {
        return "WeatherParcelable{" +
                "code='" + getCode() +
                ", icon=" + getIcon() +
                ", temperature='" + getTemperature() +
                ", humidity=" + getHumidity() +
                ", pressure=" + getPressure() +
                ", windDegree=" + getWindDegree() +
                ", windSpeed=" + getWindDegree() +
                ", lastUpdate=" + getLastUpdate() +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getCode());
        dest.writeString(getIcon());
        dest.writeDouble(getTemperature());
        dest.writeInt(getHumidity());
        dest.writeDouble(getPressure());
        dest.writeDouble(getWindDegree());
        dest.writeDouble(getWindSpeed());
        dest.writeSerializable(getLastUpdate());
    }
}
