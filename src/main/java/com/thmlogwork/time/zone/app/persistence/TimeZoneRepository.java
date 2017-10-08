package com.thmlogwork.time.zone.app.persistence;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TimeZoneRepository {

    @Autowired
    private EntityManager entityManager;

    public Timezones find( Point point ) {
        Query query = entityManager.createQuery(
                "select e from Timezones e where contains(e.geom, :filter) = true",
                Timezones.class );
        query.setParameter( "filter", point );
        return (Timezones) query.getSingleResult();
    }

}
