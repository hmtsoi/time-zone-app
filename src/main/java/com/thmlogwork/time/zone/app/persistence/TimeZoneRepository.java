package com.thmlogwork.time.zone.app.persistence;

import com.thmlogwork.time.zone.app.domain.LatLng;
import com.thmlogwork.time.zone.app.domain.TimeZoneInfo;

public interface TimeZoneRepository {

    TimeZoneInfo getTimeZone(LatLng latLng);

}
