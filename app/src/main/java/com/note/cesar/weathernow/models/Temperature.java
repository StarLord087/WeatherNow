package com.note.cesar.weathernow.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shekh on 05-02-2018.
 */

public class Temperature {
    @SerializedName("Metric")
    @Expose
    private Metric metric;
    @SerializedName("Imperial")
    @Expose
    private Imperial imperial;

    public Temperature(Metric metric, Imperial imperial) {
        this.metric = metric;
        this.imperial = imperial;
    }

    public Metric getMetric() {

        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public Imperial getImperial() {
        return imperial;
    }

    public void setImperial(Imperial imperial) {
        this.imperial = imperial;
    }
}
