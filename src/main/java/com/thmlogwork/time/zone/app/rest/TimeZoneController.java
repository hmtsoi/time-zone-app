package com.thmlogwork.time.zone.app.rest;

import com.thmlogwork.time.zone.app.domain.LatLon;
import com.thmlogwork.time.zone.app.domain.TimeZoneInfo;
import com.thmlogwork.time.zone.app.domain.TimeZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

@RestController
@RequestMapping(value = "/timeForLatLon")
public class TimeZoneController {

    @Autowired
    protected TimeZoneService timeZoneService;

    @GetMapping(path = "/{latLonStr:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTimeForLatLon(@PathVariable String latLonStr) {

        final LatLon latLon;
        try {
            latLon = validateAndParseLatLonInput(latLonStr);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Number with wrong format: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        final TimeZoneInfo timezoneInfo = timeZoneService.getTimeZoneInfo(latLon);
        final TimeZoneInfoResponse response = mapTimezoneInfo(timezoneInfo);

        return ResponseEntity.ok(response);
    }

    private TimeZoneInfoResponse mapTimezoneInfo(TimeZoneInfo entity) {
        return new TimeZoneInfoResponse(
                entity.getTz_name1st(),
                entity.getUtc_format(),
                Instant.now()
                        .atZone(ZoneId.of(entity.getTz_name1st())).toLocalDateTime().toString(),
                Instant.now()
                        .atOffset(ZoneOffset.UTC).toString()
        );
    }

    private LatLon validateAndParseLatLonInput(String latLonStr) {

        final String[] arr = latLonStr.split(",");
        if (arr.length != 2) {
            throw new IllegalArgumentException("Please input longitude and latitude comma separated"
                    + " in form of {latitude},{longitude}");
        }
        return new LatLon(arr[0], arr[1]);
    }

}
