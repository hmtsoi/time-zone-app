package com.thmlogwork.time.zone.app.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class LatLonTest {

    @Test
    void throwExceptionWhenLatitudeOutOfBound() {
        String latitudeStr = "101";
        String longitudeStr = "21.08";

        Exception e = assertThrows( IllegalArgumentException.class, () ->
                new LatLon( latitudeStr, longitudeStr )
        );
        assertEquals( e.getMessage(), "Latitude should be between -90 and 90" );
    }

    @Test
    void throwExceptionWhenLongitudeOutOfBound() {
        String latitudeStr = "71.3";
        String longitudeStr = "200.03";

        Exception e = assertThrows( IllegalArgumentException.class, () ->
                new LatLon( latitudeStr, longitudeStr )
        );
        assertEquals( e.getMessage(),
                      "Longitude should be between -180 and 180" );

        List<Integer> i = Arrays.asList( 1, 2, 3 );
        assertIterableEquals( i, Arrays.asList( 1, 2, 3 ) );
    }

    @Test
    void createLatLonSuccessfully() {
        Double latitude = ThreadLocalRandom.current().nextDouble( 0, 90 );
        Double longitude = ThreadLocalRandom.current().nextDouble( 0, 180 );
        LatLon latLon = new LatLon( String.valueOf( latitude ),
                                    String.valueOf( longitude ) );

        assertThat( latLon.getLatitude(), equalTo( latitude ) );
        assertThat( latLon.getLongitude(), equalTo( longitude ) );
    }
}
