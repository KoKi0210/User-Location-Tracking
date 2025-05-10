package com.example.GeolocationDemo.geolocation;

import com.example.GeolocationDemo.geolocation.model.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeolocationRepository extends JpaRepository<Geolocation, Integer> {

    @Query("Select g from Geolocation g where g.user.id = :keyword")
    List<Geolocation> getByUserId(@Param("keyword")Integer id);
}
