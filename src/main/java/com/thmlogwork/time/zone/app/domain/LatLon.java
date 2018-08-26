package com.thmlogwork.time.zone.app.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class LatLon {

    private double latitude;
    private double longitude;

    public LatLon( String latitudeStr, String longitudeStr ) {
        this.latitude = Double.parseDouble( latitudeStr );
        this.longitude = Double.parseDouble( longitudeStr );
        if ( latitude > 90 || latitude < -90 ) {
            throw new IllegalArgumentException( "Latitude should be between -90 and 90" );
        }
        if ( longitude > 180 || longitude < -180 ) {
            throw new IllegalArgumentException( "Longitude should be between -180 and 180" );
        }
    }
}
