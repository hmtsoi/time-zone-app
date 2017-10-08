package com.thmlogwork.time.zone.app.persistence;

import com.thmlogwork.time.zone.app.domain.LatLon;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimeZoneDao {

    @Autowired
    private TimeZoneRepository timeZoneRepository;

    public Timezones getTimeZone( LatLon latLon ) {

        final Point point = new GeometryFactory()
                .createPoint( new Coordinate( latLon.getLongitude(), latLon.getLatitude() ) );
        point.setSRID( 4326 );

        return timeZoneRepository.find( point );
    }

}