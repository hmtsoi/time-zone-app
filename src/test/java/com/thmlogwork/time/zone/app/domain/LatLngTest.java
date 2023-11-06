package com.thmlogwork.time.zone.app.domain;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class LatLngTest {

    @Test
    void createLatLonSuccessfully() {
        Double latitude = ThreadLocalRandom.current().nextDouble( 0, 90 );
        Double longitude = ThreadLocalRandom.current().nextDouble( 0, 180 );
        LatLng latLng = new LatLng( String.valueOf( latitude ),
                                    String.valueOf( longitude ) );

        assertThat( latLng.getLatitude(), equalTo( latitude ) );
        assertThat( latLng.getLongitude(), equalTo( longitude ) );
    }
}
