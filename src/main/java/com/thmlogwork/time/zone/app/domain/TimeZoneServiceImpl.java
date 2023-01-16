package com.thmlogwork.time.zone.app.domain;

import com.thmlogwork.time.zone.app.persistence.TimeZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
class TimeZoneServiceImpl implements TimeZoneService {

    @Autowired
    protected TimeZoneRepository timeZoneRepository;

    @Override
    public TimeZoneInfo getTimeZoneInfo(LatLng latLng) {
        Objects.requireNonNull(latLng);
        return timeZoneRepository.getTimeZone(latLng);
    }
}
