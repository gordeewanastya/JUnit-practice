package org.example.interfaces;

import java.time.Duration;

import org.example.model.Employee;
import org.example.model.Presence;
import org.example.model.WorkDaySummary;

public interface IWorkDaySummaryService {

  Duration getTotalWorkedTime(Employee employee, WorkDaySummary workDaySummary);

  boolean hasIncompleteRecords(Presence presence);

  void addPresence(Presence presence, Employee employee, WorkDaySummary workDaySummary);
}
