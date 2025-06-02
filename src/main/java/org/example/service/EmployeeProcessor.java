package org.example.service;

import org.example.interfaces.IDataReader;
import org.example.interfaces.IEmployeeService;
import org.example.interfaces.IValidator;
import org.example.model.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeProcessor {
  private final IDataReader<Employee> dataReader;
  private final IValidator<Employee> validator;
  private final IEmployeeService employeeService;

  public EmployeeProcessor(
      IDataReader<Employee> dataReader,
      IValidator<Employee> validator,
      IEmployeeService employeeService) {
    this.dataReader = dataReader;
    this.validator = validator;
    this.employeeService = employeeService;
  }

  public void processData() throws Exception {
    List<Employee> employeeList = dataReader.read();
    saveOnlyValidEmployees(employeeList);
  }

  private void saveOnlyValidEmployees(List<Employee> employeeList) throws SQLException {
    for (Employee employee : employeeList) {
      if (validator.isValid(employee)) {
        employeeService.save(employee);
      }
    }
  }
}
