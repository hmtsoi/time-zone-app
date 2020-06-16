package com.thmlogwork.time.zone.app.domain;

import com.thmlogwork.time.zone.app.persistence.TimeZoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith( SpringExtension.class )
class TimeZoneServiceImplTest {

    @Mock
    private TimeZoneRepository timeZoneRepositoryImpl;
    @Mock
    private TimeZoneInfo timeZoneInfo;

    private TimeZoneServiceImpl timeZoneService = new TimeZoneServiceImpl();

    private double longitude = 1;
    private double latitude = 3;
    private LatLon latLon = new LatLon( String.valueOf( latitude ),
                                        String.valueOf( longitude ) );

    @BeforeEach
    void init() {
        Mockito.when( timeZoneRepositoryImpl.getTimeZone( latLon ) ).thenReturn(
                timeZoneInfo );
        timeZoneService.timeZoneRepository = timeZoneRepositoryImpl;
    }

    @Test
    void createTimeZoneInfoResponse() throws Exception {

        timeZoneService.getTimeZoneInfo( latLon );
        Mockito.verify( timeZoneRepositoryImpl ).getTimeZone( latLon );
    }

}
