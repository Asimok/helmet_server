package com.helmet.publish.data;

public class Temperature {
    private long helmet_id;
    private double temperature;
    private long time;

    public long getHelmet_id() {
        return helmet_id;
    }

    public void setHelmet_id(long helmet_id) {
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

    @Override
    public String toString() {
        return "{" +
                "helmet_id=" + helmet_id +
                ", temperature=" + temperature +
                ", time=" + time +
                '}';
    }
}
