package com.thmlogwork.time.zone.app.rest;

import com.thmlogwork.time.zone.app.domain.LatLng;
import com.thmlogwork.time.zone.app.domain.TimeZoneInfo;
import com.thmlogwork.time.zone.app.domain.TimeZoneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest(value = TimeZoneController.class, properties = "spring.cloud.gcp.sql.enabled=false")
class TimeZoneControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private TimeZoneService timeZoneService;
    @MockitoBean
    private TimeZoneInfo timeZoneInfo;

    private TimeZoneController timeZoneController;

    private double longitude = 1;
    private double latitude = 3;
    private LatLng latLng = new LatLng(String.valueOf(latitude),
            String.valueOf(longitude));

    @Test
    void getLatlonSuccessfully() throws Exception {

        when(timeZoneInfo.getTz_name1st()).thenReturn(
                "Australia/Sydney");
        when(timeZoneService.getTimeZoneInfo(latLng))
                .thenReturn(timeZoneInfo);

        this.mockMvc.perform(get("/timeForLatLng/" + String.format("%s,%s", latitude, longitude)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        Mockito.verify(timeZoneService).getTimeZoneInfo(
                Mockito.eq(latLng));
    }

    @Test
    void getLatlonWithBadParameter() throws Exception {
        this.mockMvc.perform(get("/timeForLatLng/" + String.format("%s%s", latitude,
                        longitude)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Please input longitude and latitude comma separated in form of {latitude},{longitude}")));
    }

    @Test
    void getLatlonWithInvalidLatitude() throws Exception {
        this.mockMvc.perform(get("/timeForLatLng/" + String.format("%s,%s", 300,
                        longitude)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Latitude should be between -90 and 90")));
    }

}
