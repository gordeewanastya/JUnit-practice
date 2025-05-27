package org.example.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Presence {
  private LocalDate date;
  private LocalDateTime timeIn;
  private LocalDateTime timeOut;
  private Employee employee;

  public Presence() {}

  public Presence(LocalDate date, LocalDateTime timeIn, LocalDateTime timeOut, Employee employee) {
    this.date = date;
    this.timeIn = timeIn;
    this.timeOut = timeOut;
    this.employee = employee;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public LocalDateTime getTimeIn() {
    return timeIn;
  }

  public void setTimeIn(LocalDateTime timeIn) {
    this.timeIn = timeIn;
  }

  public LocalDateTime getTimeOut() {
    return timeOut;
  }

  public void setTimeOut(LocalDateTime timeOut) {
    this.timeOut = timeOut;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }
}
