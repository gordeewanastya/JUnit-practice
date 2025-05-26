package service;

import org.example.interfaces.IPresenceCalculator;
import org.example.model.Employee;
import org.example.model.Presence;
import org.example.service.PresenceCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PresenceCalculatorTest {
    private Presence presence;
    private IPresenceCalculator presenceCalculator;


    @BeforeEach
    public void setUp() {
        LocalDate date = LocalDate.of(2025, 5, 23);
        LocalDateTime timeIn = LocalDateTime.of(2025, 5, 23, 9, 0);
        LocalDateTime timeOut = LocalDateTime.of(2025, 5, 23, 18, 30);


        Employee employee = new Employee(1, "Anastasia", "Gordeeva", "IT", "Developer");
        presence = new Presence(date, timeIn, timeOut, employee);
        presenceCalculator = new PresenceCalculator(presence);
    }

    @Test
    public void testGetWorkingHoursDurationWhenAreValid(){
        Duration expectedDuration = Duration.ofHours(9).plusMinutes(30);

        assertEquals(expectedDuration, presenceCalculator.getWorkingHoursDuration());
    }

    @Test
    public void testGetWorkingHoursDurationWhenAreInvalid(){
        presence.setTimeIn(null);

        assertEquals(Duration.ZERO, presenceCalculator.getWorkingHoursDuration());
    }

    @Test
    public void testAreWorkingHoursValid(){
        assertTrue(presenceCalculator.areWorkingHoursValid());
    }

    @Test
    public void testAreWorkingHoursWhenTimeInIsNull(){
        presence.setTimeIn(null);
        assertFalse(presenceCalculator.areWorkingHoursValid());
    }

    @Test
    public void testAreWorkingHoursWhenTimeOutIsNull(){
        presence.setTimeOut(null);
        assertFalse(presenceCalculator.areWorkingHoursValid());
    }

    @Test
    public void testAreWorkingHoursWhenTimeOutIsBeforeTimeIn(){
        presence.setTimeOut(LocalDateTime.of(2025,5,23,6,0));
        assertFalse(presenceCalculator.areWorkingHoursValid());
    }
}
