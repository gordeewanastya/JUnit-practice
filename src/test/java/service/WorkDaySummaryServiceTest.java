package service;

import org.example.interfaces.IEmployeeService;
import org.example.interfaces.IPresenceCalculator;
import org.example.model.Employee;
import org.example.model.Presence;
import org.example.model.WorkDaySummary;
import org.example.service.WorkDaySummaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WorkDaySummaryServiceTest {

  @Mock private IPresenceCalculator presenceCalculator;
  @Mock private IEmployeeService employeeService;

  @InjectMocks private WorkDaySummaryService workDaySummaryService;

  private Employee employee;
  private Presence presence1;
  private Presence presence2;
  private WorkDaySummary workDaySummary;

  @BeforeEach
  void setUp() {
    employee = new Employee(1, "Anastasia", "Gordeeva", "IT", "Developer");
    LocalDate date = LocalDate.of(2025, 5, 24);

    workDaySummary = new WorkDaySummary(employee, date, new ArrayList<>());

    presence1 =
        new Presence(
            date,
            LocalDateTime.of(2025, 5, 24, 9, 0),
            LocalDateTime.of(2025, 5, 24, 13, 0),
            employee);

    presence2 =
        new Presence(
            date,
            LocalDateTime.of(2025, 5, 24, 14, 0),
            LocalDateTime.of(2025, 5, 24, 18, 0),
            employee);
  }

  @Test
  public void testAddPresenceWhenEmployeeIsValid() {
    when(employeeService.isValid(employee)).thenReturn(true);

    workDaySummaryService.addPresence(presence1, employee, workDaySummary);
    workDaySummaryService.addPresence(presence2, employee, workDaySummary);

    assertAll(
        () -> assertEquals(2, workDaySummary.getPresences().size()),
        () -> assertEquals(presence1, workDaySummary.getPresences().get(0)),
        () -> assertEquals(presence2, workDaySummary.getPresences().get(1)));
  }

  @Test
  public void testAddPresenceWhenEmployeeIsNotValid() {
    when(employeeService.isValid(employee)).thenReturn(false);

    assertThrows(
        IllegalStateException.class,
        () -> workDaySummaryService.addPresence(presence1, employee, workDaySummary));
  }

  @Test
  public void testAddPresenceWhenPresenceIsNull() {
    when(employeeService.isValid(employee)).thenReturn(true);

    workDaySummaryService.addPresence(null, employee, workDaySummary);

    assertEquals(0, workDaySummary.getPresences().size());
  }

  @Test
  public void testAddPresenceWhenSameDayAndDifferentEmployee() {
    Employee differentEmployee = new Employee(2, "Bob", "Feston", "IT", "Developer");
    presence1.setEmployee(differentEmployee);
    when(employeeService.isValid(differentEmployee)).thenReturn(true);
    when(employeeService.isValid(employee)).thenReturn(true);

    workDaySummaryService.addPresence(presence1, differentEmployee, workDaySummary);
    workDaySummaryService.addPresence(presence2, employee, workDaySummary);

    assertAll(
        () -> assertEquals(1, workDaySummary.getPresences().size()),
        () -> assertEquals(presence2, workDaySummary.getPresences().get(0)));
  }

  @Test
  public void testAddPresenceWhenDifferentDayAndSamePresence() {
    presence1.setDate(LocalDate.of(2025, 5, 20));
    when(employeeService.isValid(employee)).thenReturn(true);

    workDaySummaryService.addPresence(presence1, employee, workDaySummary);
    workDaySummaryService.addPresence(presence2, employee, workDaySummary);

    assertAll(
        () -> assertEquals(1, workDaySummary.getPresences().size()),
        () -> assertEquals(presence2, workDaySummary.getPresences().get(0)));
  }

  @Test
  public void testAddPresenceWhenListAlreadyContainsPresence() {
    when(employeeService.isValid(employee)).thenReturn(true);

    workDaySummary.getPresences().add(presence1);
    workDaySummaryService.addPresence(presence1, employee, workDaySummary);

    assertEquals(1, workDaySummary.getPresences().size());
  }

  @Test
  public void testHasIncompleteRecordsWhenRecordsAreComplete() {
    when(presenceCalculator.areWorkingHoursValid(presence1)).thenReturn(true);

    assertFalse(workDaySummaryService.hasIncompleteRecords(presence1));
  }

  @Test
  public void testHasIncompleteRecordsWhenRecordsAreNotComplete() {
    when(presenceCalculator.areWorkingHoursValid(presence1)).thenReturn(false);

    assertTrue(workDaySummaryService.hasIncompleteRecords(presence1));
  }

  @Test
  public void getTotalWorkedTime() {
    when(employeeService.isValid(employee)).thenReturn(true);
    when(presenceCalculator.areWorkingHoursValid(presence1)).thenReturn(true);
    when(presenceCalculator.areWorkingHoursValid(presence2)).thenReturn(true);
    when(presenceCalculator.getWorkingHoursDuration(presence1)).thenReturn(Duration.ofHours(4));
    when(presenceCalculator.getWorkingHoursDuration(presence2)).thenReturn(Duration.ofHours(4));

    workDaySummaryService.addPresence(presence1, employee, workDaySummary);
    workDaySummaryService.addPresence(presence2, employee, workDaySummary);

    assertEquals(
        Duration.ofHours(8), workDaySummaryService.getTotalWorkedTime(employee, workDaySummary));
  }

  @Test
  public void getTotalWorkedTimeWhenEmployeeIsNotValid() {
    when(employeeService.isValid(employee)).thenReturn(false);

    assertThrows(
        IllegalStateException.class,
        () -> workDaySummaryService.getTotalWorkedTime(employee, workDaySummary));
  }

  @Test
  public void getTotalWorkedTimeWhenRecordsAreNotComplete() {
    when(employeeService.isValid(employee)).thenReturn(true);
    when(presenceCalculator.areWorkingHoursValid(presence1)).thenReturn(false);
    when(presenceCalculator.areWorkingHoursValid(presence2)).thenReturn(true);

    workDaySummaryService.addPresence(presence1, employee, workDaySummary);
    workDaySummaryService.addPresence(presence2, employee, workDaySummary);

    assertEquals(
        Duration.ofHours(0), workDaySummaryService.getTotalWorkedTime(employee, workDaySummary));
  }
}
