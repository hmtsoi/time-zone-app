package com.thmlogwork.time.zone.app.persistence;

import com.thmlogwork.time.zone.app.domain.LatLon;
import com.thmlogwork.time.zone.app.domain.TimeZoneInfo;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityNotFoundException;


@Repository
@RequiredArgsConstructor
class TimeZoneRepositoryImpl implements TimeZoneRepository {

    private final TimeZoneJpaRepository timeZoneJpaRepository;

    public TimeZoneInfo getTimeZone(LatLon latLon) {

        final Point point = new GeometryFactory()
                .createPoint(new Coordinate(latLon.getLongitude(), latLon.getLatitude()));
        point.setSRID(4326);

        final Timezones entity = timeZoneJpaRepository.getTimezone(point)
                .orElseThrow(EntityNotFoundException::new);
        return new TimeZoneInfo(
                entity.getUtc_format(),
                entity.getTz_name1st()
        );
    }

}
