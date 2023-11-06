package com.thmlogwork.time.zone.app.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class LatLng {

    private double latitude;
    private double longitude;

    public LatLng(String latitudeStr, String longitudeStr) {
        this.latitude = Double.parseDouble(latitudeStr);
        this.longitude = Double.parseDouble(longitudeStr);
    }
}
