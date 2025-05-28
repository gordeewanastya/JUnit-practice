package org.example.service;

import org.example.interfaces.IEmployeeService;
import org.example.model.Employee;

import static java.lang.String.join;
import static java.util.Objects.requireNonNullElse;

public class EmployeeService implements IEmployeeService {

  @Override
  public String getFullName(Employee employee) {
    if (!isValid(employee)) {
      return requireNonNullElse(
          employee.getFirstName(), requireNonNullElse(employee.getLastName(), "No full name"));
    }

    return join(" ", employee.getFirstName(), employee.getLastName());
  }

  @Override
  public boolean isValid(Employee employee) {
    final String firstName = employee.getFirstName();
    final String lastName = employee.getLastName();

    return firstName != null && lastName != null && !firstName.isBlank() && !lastName.isBlank();
  }
}
