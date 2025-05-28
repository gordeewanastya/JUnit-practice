package org.example.interfaces;

import org.example.model.Employee;

public interface IEmployeeService {

  String getFullName(Employee employee);

  boolean isValid(Employee employee);
}
