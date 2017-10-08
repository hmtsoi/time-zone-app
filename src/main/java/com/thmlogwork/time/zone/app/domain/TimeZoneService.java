package com.thmlogwork.time.zone.app.domain;

import com.thmlogwork.time.zone.app.rest.TimeZoneInfoResponse;

public interface TimeZoneService {

    TimeZoneInfoResponse createTimeZoneInfoResponse( LatLon latLon );

}
