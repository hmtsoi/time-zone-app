package com.thmlogwork.time.zone.app.persistence;

import com.vividsolutions.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

interface TimeZoneJpaRepository extends JpaRepository<Timezones, Integer> {

    @Query( "SELECT e from Timezones e WHERE FUNCTION('st_contains', e.geom, :point) = TRUE" )
    Optional<Timezones> getTimezone( @Param( "point" ) Point point );

}
