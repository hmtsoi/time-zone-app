package com.thmlogwork.time.zone.app.domain;

import com.thmlogwork.time.zone.app.persistence.TimeZoneDao;
import com.thmlogwork.time.zone.app.persistence.Timezones;
import com.thmlogwork.time.zone.app.rest.TimeZoneInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeZoneServiceImpl implements TimeZoneService {

    @Autowired
    protected TimeZoneDao timeZoneDao;

    @Override public TimeZoneInfoResponse getTimeZoneInfo( LatLon latLon ) {
        final Timezones entity = timeZoneDao.getTimeZone( latLon );
        return new TimeZoneInfoResponse( entity );
    }
}
