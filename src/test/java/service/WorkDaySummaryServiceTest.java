package service;

import org.example.interfaces.IPresenceCalculator;
import org.example.interfaces.IWorkDaySummaryService;
import org.example.model.Employee;
import org.example.model.Presence;
import org.example.model.WorkDaySummary;
import org.example.service.PresenceCalculator;
import org.example.service.WorkDaySummaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WorkDaySummaryServiceTest {

    private Employee employee;
    private LocalDate date;
    private Presence presence1;
    private Presence presence2;
    private WorkDaySummary workDaySummary;

    private IPresenceCalculator presenceCalculator;
    private IWorkDaySummaryService workDaySummaryService;

    @BeforeEach
    public void setUp() {
        employee = new Employee(1, "Anastasia", "Gordeeva", "IT", "Developer");
        date = LocalDate.of(2025, 5, 23);
        workDaySummary = new WorkDaySummary(employee, date);

        workDaySummaryService = new WorkDaySummaryService(workDaySummary);


        presence1 = new Presence(
                date,
                LocalDateTime.of(2025, 5, 23, 9, 0),
                LocalDateTime.of(2025, 5, 23, 16, 0),
                employee);

        presence2 = new Presence(
                date,
                LocalDateTime.of(2025, 5, 23, 16, 30),
                LocalDateTime.of(2025, 5, 23, 18, 30),
                employee);

    }

    @Test
    public void testGetTotalWorkedTime(){
        workDaySummaryService.addPresence(presence1);
        workDaySummaryService.addPresence(presence2);

        Duration presence1Duration = Duration.between(presence1.getTimeIn(), presence1.getTimeOut());
        Duration presence2Duration = Duration.between(presence2.getTimeIn(), presence2.getTimeOut());

        Duration expectedDuration = presence1Duration.plus(presence2Duration);

        assertEquals(expectedDuration, workDaySummaryService.getTotalWorkedTime());
    }

    @Test
    public void testGetTotalWorkedTime2(){
        workDaySummaryService.addPresence(presence1);
        workDaySummaryService.addPresence(presence2);

        Duration presence1Duration = Duration.between(presence1.getTimeIn(), presence1.getTimeOut());
        Duration presence2Duration = Duration.between(presence2.getTimeIn(), presence2.getTimeOut());

        Duration expectedDuration = presence1Duration.plus(presence2Duration);



    }

    @Test
    public void testGetTotalWorkedTimeWhenPresenceHasInvalidRecords(){
        presence1.setTimeIn(null);
        Presence invalidPresence = presence1;

        workDaySummaryService.addPresence(invalidPresence);

        assertEquals(Duration.ZERO, workDaySummaryService.getTotalWorkedTime());
    }

    @Test
    public void testHasInvalidRecordsWhenRecordsAreValid(){
        assertFalse(workDaySummaryService.hasIncompleteRecords(presence1));
        assertFalse(workDaySummaryService.hasIncompleteRecords(presence2));
    }

    @Test
    public void testHasInvalidRecordsWhenOneRecordIsInvalid(){
        presence1.setTimeIn(null);
        Presence invalidPresence = presence1;

        assertTrue(workDaySummaryService.hasIncompleteRecords(invalidPresence));
    }

    @Test
    public void testAddPresenceWhenAreTheSameDayAndSameEmployee(){
        workDaySummaryService.addPresence(presence1);
        workDaySummaryService.addPresence(presence2);

        WorkDaySummary workDaySummary1 = workDaySummaryService.getWorkDaySummary();

        assertEquals(2, workDaySummary1.getPresences().size());
        assertEquals(presence1, workDaySummary1.getPresences().get(0));
        assertEquals(presence2, workDaySummary1.getPresences().get(1));
    }

    @Test
    public void testAddPresenceWhenItIsNull(){
        workDaySummaryService.addPresence(null);

        assertEquals(0 , workDaySummaryService.getWorkDaySummary().getPresences().size());
    }

    @Test
    public void testAddPresenceWhenAreTheSameDayAndDifferentEmployee(){
        Employee differentEmployee = new Employee(2, "John", "Doe", "IT", "Developer");
        presence1.setEmployee(differentEmployee);

        workDaySummaryService.addPresence(presence1);
        workDaySummaryService.addPresence(presence2);

        assertEquals(1, workDaySummaryService.getWorkDaySummary().getPresences().size());
        assertEquals(presence2, workDaySummaryService.getWorkDaySummary().getPresences().get(0));
    }

    @Test
    public void testAddPresenceWhenAreTheDifferentDayAndSameEmployee(){
        presence1.setDate(LocalDate.of(2024,6,27));

        workDaySummaryService.addPresence(presence1);
        workDaySummaryService.addPresence(presence2);

        assertEquals(1, workDaySummaryService.getWorkDaySummary().getPresences().size());
        assertEquals(presence2, workDaySummaryService.getWorkDaySummary().getPresences().get(0));
    }

    @Test
    public void testAddPresenceWhenAreTheOtherDayAndDifferentEmployee(){
        Employee differentEmployee = new Employee(2, "John", "Doe", "IT", "Developer");
        presence1.setEmployee(differentEmployee);
        presence1.setDate(LocalDate.of(2024,6,27));

        workDaySummaryService.addPresence(presence1);
        workDaySummaryService.addPresence(presence2);

        assertEquals(1, workDaySummaryService.getWorkDaySummary().getPresences().size());
        assertEquals(presence2, workDaySummaryService.getWorkDaySummary().getPresences().get(0));
    }
}
