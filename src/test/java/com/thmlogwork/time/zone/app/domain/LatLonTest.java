package com.thmlogwork.time.zone.app.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LatLonTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void throwExceptionWhenLatitudeOutOfBound() {
        String latitudeStr = "101";
        String longitudeStr = "21.08";
        exception.expect( IllegalArgumentException.class );
        exception.expectMessage( "Latitude should be between -90 and 90" );
        new LatLon( latitudeStr, longitudeStr );
    }

    @Test
    public void throwExceptionWhenLongitudeOutOfBound() {
        String latitudeStr = "71.3";
        String longitudeStr = "200.03";
        exception.expect( IllegalArgumentException.class );
        exception.expectMessage( "Longitude should be between -180 and 180" );
        new LatLon( latitudeStr, longitudeStr );
    }

    @Test
    public void createLatLonSuccessfully() {
        Double latitude = ThreadLocalRandom.current().nextDouble( 0, 90 );
        Double longitude = ThreadLocalRandom.current().nextDouble( 0, 180 );
        LatLon latLon = new LatLon( String.valueOf( latitude ), String.valueOf( longitude ) );

        assertThat( latLon.getLatitude(), equalTo( latitude ) );
        assertThat( latLon.getLongitude(), equalTo( longitude ) );
    }
}
