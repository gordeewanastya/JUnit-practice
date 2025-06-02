package org.example.interfaces;

import org.example.model.Employee;

import java.sql.SQLException;

public interface IEmployeeService {

  String getFullName(Employee employee);

  boolean isValid(Employee employee);

  Employee save(Employee employee) throws SQLException;
}
