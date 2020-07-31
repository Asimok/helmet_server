package com.helmet.subscribe.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "temperature_history")
public class TemperatureHistory {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String helmet_id;

    @Column
    private double temperature;

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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
