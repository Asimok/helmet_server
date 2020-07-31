package com.helmet.subscribe.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "mpu_history")
public class MpuHistory {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String helmet_id;

    @Column
    private int warning;

    @Column
    private long time;

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

    public int getWarning() {
        return warning;
    }

    public void setWarning(int warning) {
        this.warning = warning;
    }
}
