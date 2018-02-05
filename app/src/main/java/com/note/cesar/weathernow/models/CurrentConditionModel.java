package com.note.cesar.weathernow.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shekh on 05-02-2018.
 */

public class CurrentConditionModel {

    private String LocalObservationDateTime;
    private int EpochTime;
    private String WeatherText;

    @SerializedName("Temperature")
    @Expose
    private Temperature temperature;

    public String getLocalObservationDateTime() {
        return LocalObservationDateTime;
    }

    public void setLocalObservationDateTime(String localObservationDateTime) {
        LocalObservationDateTime = localObservationDateTime;
    }

    public int getEpochTime() {
        return EpochTime;
    }

    public void setEpochTime(int epochTime) {
        EpochTime = epochTime;
    }

    public String getWeatherText() {
        return WeatherText;
    }

    public void setWeatherText(String weatherText) {
        WeatherText = weatherText;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }



}
