package com.thmlogwork.time.zone.app.rest;

import com.thmlogwork.time.zone.app.domain.LatLon;
import com.thmlogwork.time.zone.app.domain.TimeZoneService;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;

@RunWith( MockitoJUnitRunner.class )
public class TimeZoneControllerTest {

    @Mock
    private TimeZoneService timeZoneService;

    private TimeZoneController timeZoneController = new TimeZoneController();

    private double longitude = 1;
    private double latitude = 3;
    private LatLon latLon = new LatLon( String.valueOf( latitude ), String.valueOf( longitude ) );

    @Before
    public void init() {
        Mockito.when( timeZoneService.getTimeZoneInfo( latLon ) )
                .thenReturn( new TimeZoneInfoResponse() );

        timeZoneController.timeZoneService = timeZoneService;
    }

    @Test
    public void getLatlonSuccessfully() {
        given()
                .standaloneSetup( timeZoneController )
                .contentType( ContentType.JSON )
                .when()
                .get( "/timeForLatLon/" + String.format( "%s,%s", latitude, longitude ) )
                .then()
                .statusCode( 200 );

        Mockito.verify( timeZoneService ).getTimeZoneInfo( Mockito.eq( latLon ) );
    }

    @Test
    public void getLatlonWithBadParameter() {
        given()
                .standaloneSetup( timeZoneController )
                .contentType( ContentType.JSON )
                .when()
                .get( "/timeForLatLon/" + String.format( "%s%s", latitude, longitude ) )
                .then()
                .statusCode( 400 );

        Mockito.verify( timeZoneService, never() ).getTimeZoneInfo( any() );
    }

}
