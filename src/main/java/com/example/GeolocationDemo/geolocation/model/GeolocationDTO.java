package com.example.GeolocationDemo.geolocation.model;

import com.example.GeolocationDemo.user.model.User;
import lombok.Data;

@Data
public class GeolocationDTO {
    private Integer id;
    private User user;
    private Double latitude;
    private Double longitude;

    public GeolocationDTO(Geolocation geolocation) {
        this.id = geolocation.getId();
        this.user = geolocation.getUser();
        this.latitude = geolocation.getLatitude();
        this.longitude = geolocation.getLongitude();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
