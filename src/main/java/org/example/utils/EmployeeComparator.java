package org.example.utils;

import org.example.model.Employee;

import java.util.Comparator;

public class EmployeeComparator implements Comparator<Employee> {

  @Override
  public int compare(Employee emp1, Employee emp2) {
    return emp1.getLastName().compareTo(emp2.getLastName());
  }
}
