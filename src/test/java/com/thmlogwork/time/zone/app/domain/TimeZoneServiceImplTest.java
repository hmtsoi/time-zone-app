package com.thmlogwork.time.zone.app.domain;

import com.thmlogwork.time.zone.app.persistence.TimeZoneDao;
import com.thmlogwork.time.zone.app.persistence.Timezones;
import com.thmlogwork.time.zone.app.rest.TimeZoneInfoResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.never;

@RunWith( MockitoJUnitRunner.class )
public class TimeZoneServiceImplTest {

    @Mock
    private TimeZoneDao timeZoneDao;
    @Mock
    private Timezones timezones;

    private TimeZoneServiceImpl timeZoneService = new TimeZoneServiceImpl();

    private double longitude = 1;
    private double latitude = 3;
    private LatLon latLon = new LatLon( String.valueOf( latitude ), String.valueOf( longitude ) );

    @Before
    public void init() {
        Mockito.when( timezones.getTz_name1st() ).thenReturn( "Europe/Berlin" );
        Mockito.when( timeZoneDao.getTimeZone( latLon ) ).thenReturn( timezones );

        timeZoneService.timeZoneDao = timeZoneDao;
    }

    @Test
    public void createTimeZoneInfoResponse() throws Exception {

        timeZoneService.getTimeZoneInfo( latLon );

        Mockito.verify( timeZoneDao ).getTimeZone( latLon );
        Mockito.verify( timezones ).getTz_name1st();
        Mockito.verify( timezones ).getUtc_format();
        Mockito.verify( timezones, never() ).getGeom();

    }

}