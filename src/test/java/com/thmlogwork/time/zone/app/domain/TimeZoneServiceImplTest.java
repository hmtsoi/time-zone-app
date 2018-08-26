package com.thmlogwork.time.zone.app.domain;

import com.thmlogwork.time.zone.app.persistence.TimeZoneRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith( SpringRunner.class )
public class TimeZoneServiceImplTest {

    @Mock
    private TimeZoneRepository timeZoneRepositoryImpl;
    @Mock
    private TimeZoneInfo timeZoneInfo;

    private TimeZoneServiceImpl timeZoneService = new TimeZoneServiceImpl();

    private double longitude = 1;
    private double latitude = 3;
    private LatLon latLon = new LatLon( String.valueOf( latitude ), String.valueOf( longitude ) );

    @Before
    public void init() {
        Mockito.when( timeZoneRepositoryImpl.getTimeZone( latLon ) ).thenReturn( timeZoneInfo );
        timeZoneService.timeZoneRepository = timeZoneRepositoryImpl;
    }

    @Test
    public void createTimeZoneInfoResponse() throws Exception {

        timeZoneService.getTimeZoneInfo( latLon );
        Mockito.verify( timeZoneRepositoryImpl ).getTimeZone( latLon );
    }

}
