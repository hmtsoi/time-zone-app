package com.thmlogwork.time.zone.app.rest;

import com.thmlogwork.time.zone.app.domain.LatLng;
import com.thmlogwork.time.zone.app.domain.TimeZoneInfo;
import com.thmlogwork.time.zone.app.domain.TimeZoneService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
class TimeZoneControllerTest {

    @Mock
    private TimeZoneService timeZoneService;
    @Mock
    private TimeZoneInfo timeZoneInfo;

    private TimeZoneController timeZoneController;

    private double longitude = 1;
    private double latitude = 3;
    private LatLng latLng = new LatLng(String.valueOf(latitude),
            String.valueOf(longitude));

    @BeforeEach
    public void init() {
        timeZoneController = new TimeZoneController(timeZoneService);
    }

    @Test
    void getLatlonSuccessfully() {

        when(timeZoneInfo.getTz_name1st()).thenReturn(
                "Australia/Sydney");
        when(timeZoneService.getTimeZoneInfo(latLng))
                .thenReturn(timeZoneInfo);
        given()
                .standaloneSetup(timeZoneController)
                .contentType(ContentType.JSON)
                .when()
                .get("/timeForLatLng/" + String.format("%s,%s", latitude,
                        longitude))
                .then()
                .statusCode(200);

        Mockito.verify(timeZoneService).getTimeZoneInfo(
                Mockito.eq(latLng));
    }

    @Test
    void getLatlonWithBadParameter() {

        given()
                .standaloneSetup(timeZoneController)
                .contentType(ContentType.JSON)
                .when()
                .get("/timeForLatLng/" + String.format("%s%s", latitude,
                        longitude))
                .then()
                .statusCode(400);

        Mockito.verify(timeZoneService, never()).getTimeZoneInfo(any());
    }

}
