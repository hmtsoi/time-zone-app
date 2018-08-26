package com.thmlogwork.time.zone.app.rest;

import com.thmlogwork.time.zone.app.domain.LatLon;
import com.thmlogwork.time.zone.app.domain.TimeZoneInfo;
import com.thmlogwork.time.zone.app.domain.TimeZoneService;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;

@RunWith( SpringRunner.class )
public class TimeZoneControllerTest {

    @Mock
    private TimeZoneService timeZoneService;
    @Mock
    private TimeZoneInfo timeZoneInfo;

    private TimeZoneController timeZoneController = new TimeZoneController();

    private double longitude = 1;
    private double latitude = 3;
    private LatLon latLon = new LatLon( String.valueOf( latitude ), String.valueOf( longitude ) );

    @Before
    public void init() {
        Mockito.when( timeZoneInfo.getTz_name1st() ).thenReturn( "Australia/Sydney" );
        Mockito.when( timeZoneService.getTimeZoneInfo( latLon ) )
                .thenReturn( timeZoneInfo );

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
