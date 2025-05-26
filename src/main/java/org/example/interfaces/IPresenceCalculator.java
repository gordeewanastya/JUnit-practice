package org.example.interfaces;

import java.time.Duration;

public interface IPresenceCalculator {
    Duration getWorkingHoursDuration();
    boolean areWorkingHoursValid();
}
