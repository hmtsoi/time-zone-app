package com.thmlogwork.time.zone.app.domain;

import com.thmlogwork.time.zone.app.persistence.TimeZoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith( SpringExtension.class )
class TimeZoneServiceImplTest {

    @MockitoBean
    private TimeZoneRepository timeZoneRepositoryImpl;
    @Mock
    private TimeZoneInfo timeZoneInfo;

    private TimeZoneServiceImpl timeZoneService = new TimeZoneServiceImpl();

    private double longitude = 1;
    private double latitude = 3;
    private LatLng latLng = new LatLng( String.valueOf( latitude ),
                                        String.valueOf( longitude ) );

    @BeforeEach
    void init() {
        Mockito.when( timeZoneRepositoryImpl.getTimeZone(latLng) ).thenReturn(
                timeZoneInfo );
        timeZoneService.timeZoneRepository = timeZoneRepositoryImpl;
    }

    @Test
    void createTimeZoneInfoResponse() throws Exception {

        timeZoneService.getTimeZoneInfo(latLng);
        Mockito.verify( timeZoneRepositoryImpl ).getTimeZone(latLng);
    }

}
