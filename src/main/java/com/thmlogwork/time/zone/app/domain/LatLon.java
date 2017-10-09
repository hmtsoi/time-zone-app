package com.thmlogwork.time.zone.app.domain;

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

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        LatLon latLon = (LatLon) o;

        if ( Double.compare( latLon.latitude, latitude ) != 0 )
            return false;
        return Double.compare( latLon.longitude, longitude ) == 0;
    }

    @Override public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits( latitude );
        result = (int) ( temp ^ ( temp >>> 32 ) );
        temp = Double.doubleToLongBits( longitude );
        result = 31 * result + (int) ( temp ^ ( temp >>> 32 ) );
        return result;
    }
}
