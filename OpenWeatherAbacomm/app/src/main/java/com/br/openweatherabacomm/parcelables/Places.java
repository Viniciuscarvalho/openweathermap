package com.br.openweatherabacomm.parcelables;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by viniciuscarvalho on 30/12/15.
 */
public class Places implements Parcelable {

    private int id;
    private String name;
    private String image;
    private int temperature;
    private String conditions;
    private String description;

    public Places() {
    }

    private Places(Parcel from) {
        setId(from.readInt());
        setName(from.readString());
        setImage(from.readString());
        setTemperature(from.readValue(null));
        setConditions(from.readString());
        setDescription(from.readString());

    }
    public static final Parcelable.Creator<Places>
            CREATOR = new Parcelable.Creator<Places>() {

        public Places createFromParcel(Parcel in) {
            return new Places(in);
        }

        public Places[] newArray(int size) {
            return new Places[size];
        }
    };

    @Override
    public String toString() {
        return "Places{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", image='" + getImage() + '\'' +
                ", temperature=" + getTemperature() +
                ", description='" + getDescription() + '\'' +
                ", conditions='" + getConditions() + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
        dest.writeString(getName());
        dest.writeString(getImage());
        dest.writeValue(getTemperature());
        dest.writeString(getConditions());
        dest.writeString(getDescription());
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setTemperature(Object temperature) {
        this.temperature = (int) temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getConditions() {
        return conditions;
    }
}