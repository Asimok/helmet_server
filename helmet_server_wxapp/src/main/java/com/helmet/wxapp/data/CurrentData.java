package com.helmet.wxapp.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "currentdata")
public class CurrentData {
    @Id
    private String helmet_id;

    @Column
    private double temperature;

    @Column
    private int warning;

    @Column
    private double light;

    @Column
    private String longitude;

    @Column
    private String latitude;

    @Column
    private String N_S;

    @Column
    private String E_W;

    @Column
    private String altitude;

    @Column
    private long time;

    public String getHelmet_id() {
        return helmet_id;
    }

    public void setHelmet_id(String helmet_id) {
        this.helmet_id = helmet_id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getWarning() {
        return warning;
    }

    public void setWarning(int warning) {
        this.warning = warning;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public double getLight() {
        return light;
    }

    public void setLight(double light) {
        this.light = light;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getN_S() {
        return N_S;
    }

    public void setN_S(String n_S) {
        N_S = n_S;
    }

    public String getE_W() {
        return E_W;
    }

    public void setE_W(String e_W) {
        E_W = e_W;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
