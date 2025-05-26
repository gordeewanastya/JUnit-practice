package org.example.service;

import org.example.interfaces.IPresenceCalculator;
import org.example.model.Presence;

import java.time.Duration;
import java.time.LocalDateTime;

public class PresenceCalculator implements IPresenceCalculator {
    private Presence presence;

    public PresenceCalculator(Presence presence) {
        if (presence == null) throw new IllegalArgumentException("Presence cannot be null");
        this.presence = presence;
    }

    @Override
    public Duration getWorkingHoursDuration() {
        if (areWorkingHoursValid()) return Duration.between(presence.getTimeIn(), presence.getTimeOut());

        return Duration.ZERO;
    }

    @Override
    public boolean areWorkingHoursValid() {
        LocalDateTime timeIn = presence.getTimeIn();
        LocalDateTime timeOut = presence.getTimeOut();

        return timeIn != null && timeOut !=null && timeOut.isAfter(timeIn);
    }
}
