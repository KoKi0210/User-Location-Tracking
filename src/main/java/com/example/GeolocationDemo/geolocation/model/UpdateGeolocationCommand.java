package com.example.GeolocationDemo.geolocation.model;

import lombok.Data;

@Data
public class UpdateGeolocationCommand {
    private Integer id;
    private Geolocation geolocation;

    public UpdateGeolocationCommand(Integer id, Geolocation geolocation) {
        this.id = id;
        this.geolocation = geolocation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }
}
