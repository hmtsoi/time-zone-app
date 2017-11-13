package com.thmlogwork.time.zone.app.rest;

import com.thmlogwork.time.zone.app.domain.LatLon;
import com.thmlogwork.time.zone.app.domain.TimeZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "/timeForLatLon" )
public class TimeZoneController {

    @Autowired
    protected TimeZoneService timeZoneService;

    @GetMapping( path = "/{latLonStr:.+}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity getTimeForLatLon( @PathVariable String latLonStr ) {

        final LatLon latLon;
        try {
            latLon = validateAndParseLatLonInput( latLonStr );
        } catch ( NumberFormatException e ) {
            return ResponseEntity.badRequest().body( "Number with wrong format: " + e.getMessage() );
        } catch ( IllegalArgumentException e ) {
            return ResponseEntity.badRequest().body( e.getMessage() );
        }

        final TimeZoneInfoResponse response = timeZoneService.getTimeZoneInfo( latLon );

        return ResponseEntity.ok( response );
    }

    private LatLon validateAndParseLatLonInput( String latLonStr ) {

        final String[] arr = latLonStr.split( "," );
        if ( arr.length != 2 ) {
            throw new IllegalArgumentException( "Please input longitude and latitude comma separated"
                                                        + " in form of {latitude},{longitude}" );
        }
        return new LatLon( arr[0], arr[1] );
    }

}
