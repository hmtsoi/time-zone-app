package com.thmlogwork.time.zone.app.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TimeZoneInfoResponse {

    public String nameOfTimeZone;
    public String offsetFromUtc;
    public String currentLocalTime;
    public String currentUtcTime;

}
