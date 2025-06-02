package org.example.service;

import java.time.Duration;
import org.example.interfaces.IPresenceCalculator;
import org.example.interfaces.IValidator;
import org.example.interfaces.IWorkDaySummaryService;
import org.example.model.Employee;
import org.example.model.Presence;
import org.example.model.WorkDaySummary;

public class WorkDaySummaryService implements IWorkDaySummaryService {
  private final IValidator<Employee> employeeValidator;
  private final IPresenceCalculator calculator;

  public WorkDaySummaryService(
      IValidator<Employee> employeeValidator, IPresenceCalculator calculator) {
    this.calculator = calculator;
    this.employeeValidator = employeeValidator;
  }

  @Override
  public Duration getTotalWorkedTime(Employee employee, WorkDaySummary workDaySummary) {
    if (!employeeValidator.isValid(employee)) {
      throw new IllegalStateException("Employee data is not valid!");
    }

    Duration total = Duration.ZERO;
    for (Presence presence : workDaySummary.getPresences()) {
      if (!hasIncompleteRecords(presence)) {
        total = total.plus(calculator.getWorkingHoursDuration(presence));
      }
    }
    return total;
  }

  @Override
  public boolean hasIncompleteRecords(Presence presence) {
    return !calculator.areWorkingHoursValid(presence);
  }

  @Override
  public void addPresence(Presence presence, Employee employee, WorkDaySummary workDaySummary) {
    if (!employeeValidator.isValid(employee)) {
      throw new IllegalStateException("Employee data is not valid!");
    }
    if (presence != null
        && workDaySummary.getDate().equals(presence.getDate())
        && workDaySummary.getEmployee().equals(presence.getEmployee())
        && !workDaySummary.getPresences().contains(presence)) {
      workDaySummary.getPresences().add(presence);
    }
  }
}
