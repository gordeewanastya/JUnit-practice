//package service;
//
//import org.example.interfaces.IEmployeeService;
//import org.example.model.Employee;
//import org.example.model.Presence;
//import org.example.model.WorkDaySummary;
//import org.example.service.PresenceCalculator;
//import org.example.service.WorkDaySummaryService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.Duration;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Collections;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class WorkDaySummaryServiceTestUsingMockito {
//    @Mock
//    private IEmployeeService employeeService;
//
//    @Mock
//    private WorkDaySummary workDaySummary;
//
//    @Mock
//    private Presence presence;
//
//    private WorkDaySummaryService workDaySummaryService;
//
//    private Employee employee;
//    private LocalDate date;
//    private LocalDateTime timeIn;
//    private LocalDateTime timeOut;
//
//    @BeforeEach
//    void setUp() {
//        employee = new Employee(1, "Anastasia", "Gordeeva", "IT", "Developer");
//        date = LocalDate.of(2025, 5, 23);
//        timeIn = LocalDateTime.of(2025, 5, 23, 9, 0);
//        timeOut = LocalDateTime.of(2025, 5, 24, 18, 0);
//
//        when(workDaySummary.getDate()).thenReturn(date);
//        when(workDaySummary.getEmployee()).thenReturn(employee);
//        when(workDaySummary.getPresences()).thenReturn(new ArrayList<>());
//        when(presence.getDate()).thenReturn(date);
//        when(presence.getEmployee()).thenReturn(employee);
//        when(presence.getTimeIn()).thenReturn(timeIn);
//        when(presence.getTimeOut()).thenReturn(timeOut);
//
////        when(employeeService.isValid()).thenReturn(true);
////        when(employeeService.getFullName()).thenReturn("Anastasia Gordeeva");
//
//        workDaySummaryService = new WorkDaySummaryService(workDaySummary, employeeService);
//    }
//
//    @Test
//    public void getTotalWorkedTimeWhenValidEmployeeAndPresenceDuration(){
//        when(employeeService.isValid()).thenReturn(true);
//        when(workDaySummary.getPresences()).thenReturn(Collections.singletonList(presence));
//        when().areWorkingHoursValid()).thenReturn(true);
//        when(new PresenceCalculator(presence).getWorkingHoursDuration()).thenReturn(Duration.between(timeIn, timeOut));
//
//        Duration result = workDaySummaryService.getTotalWorkedTime();
//
//        assertEquals(Duration.between(timeIn, timeOut), result);
//        verify(employeeService).isValid();
//    }
//}
