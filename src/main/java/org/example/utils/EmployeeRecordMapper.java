package org.example.utils;

import org.example.interfaces.IRecordMapper;
import org.example.model.Employee;

public class EmployeeRecordMapper implements IRecordMapper<Employee> {
  @Override
  public Employee mapToEntity(String[] record) {
    Integer id = Integer.parseInt(record[0]);
    String firstName = record[1];
    String lastName = record[2];
    String department = record[3];
    String jobTitle = record[4];

    return new Employee(id, firstName, lastName, department, jobTitle);
  }
}
