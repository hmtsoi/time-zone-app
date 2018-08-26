package com.thmlogwork.time.zone.app.persistence;

import com.thmlogwork.time.zone.app.domain.LatLon;
import com.thmlogwork.time.zone.app.domain.TimeZoneInfo;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository class TimeZoneRepositoryImpl implements TimeZoneRepository {

    @Autowired
    private TimeZoneJpaRepository timeZoneJpaRepository;

    public TimeZoneInfo getTimeZone( LatLon latLon ) {

        final Point point = new GeometryFactory()
                .createPoint( new Coordinate( latLon.getLongitude(), latLon.getLatitude() ) );
        point.setSRID( 4326 );

        final Timezones entity = timeZoneJpaRepository.getTimezone( point )
                .orElseThrow( EntityNotFoundException::new );
        return new TimeZoneInfo(
                entity.getUtc_format(),
                entity.getTz_name1st()
        );
    }

}
