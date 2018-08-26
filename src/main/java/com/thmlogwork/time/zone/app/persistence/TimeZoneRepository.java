package com.thmlogwork.time.zone.app.persistence;

import com.thmlogwork.time.zone.app.domain.LatLon;
import com.thmlogwork.time.zone.app.domain.TimeZoneInfo;

public interface TimeZoneRepository {

    TimeZoneInfo getTimeZone( LatLon latLon );

}
