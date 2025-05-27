package org.example.interfaces;

import org.example.model.Presence;

import java.time.Duration;

public interface IPresenceCalculator {

  Duration getWorkingHoursDuration(Presence presence);

  boolean areWorkingHoursValid(Presence presence);
}
