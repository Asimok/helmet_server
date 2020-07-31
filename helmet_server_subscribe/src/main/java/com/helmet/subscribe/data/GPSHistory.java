package com.helmet.subscribe.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "gps_history")
public class GPSHistory {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String helmet_id;

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

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHelmet_id() {
        return helmet_id;
    }

    public void setHelmet_id(String helmet_id) {
        this.helmet_id = helmet_id;
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
}
