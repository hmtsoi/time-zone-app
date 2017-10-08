package com.thmlogwork.time.zone.app.rest;

import com.thmlogwork.time.zone.app.persistence.Timezones;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class TimeZoneInfoResponse {

    public String nameOfTimeZone;
    public String offsetFromUtc;
    public String currentLocalTime;
    public String currentUtcTime;

    public TimeZoneInfoResponse() {
    }

    public TimeZoneInfoResponse( Timezones entity ) {
        this.nameOfTimeZone = entity.getTz_name1st();
        this.offsetFromUtc = entity.getUtc_format();
        this.currentLocalTime = Instant.now()
                .atZone( ZoneId.of( nameOfTimeZone ) ).toLocalDateTime().toString();
        this.currentUtcTime = Instant.now()
                .atOffset( ZoneOffset.UTC ).toString();
    }
}
