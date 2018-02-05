package com.note.cesar.weathernow.models;

/**
 * Created by shekh on 05-02-2018.
 */

public class Metric {

    Double Value;

    public Metric(Double value, String unit, Integer unitType) {
        Value = value;
        Unit = unit;
        UnitType = unitType;
    }

    String Unit;
    Integer UnitType;

    public Double getValue() {
        return Value;
    }

    public void setValue(Double value) {
        Value = value;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public Integer getUnitType() {
        return UnitType;
    }

    public void setUnitType(Integer unitType) {
        UnitType = unitType;
    }
}
