package com.thmlogwork.time.zone.app.persistence;

import com.thmlogwork.time.zone.app.domain.LatLng;
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

    public TimeZoneInfo getTimeZone(LatLng latLng) {

        final Point point = new GeometryFactory()
                .createPoint(new Coordinate(latLng.getLongitude(), latLng.getLatitude()));
        point.setSRID(4326);

        final Timezones entity = timeZoneJpaRepository.getTimezone(point)
                .orElseThrow(EntityNotFoundException::new);
        return new TimeZoneInfo(
                entity.getUtc_format(),
                entity.getTz_name1st()
        );
    }

}
