package org.example.utils;

import org.example.interfaces.IValidator;
import org.example.model.Employee;

public class EmployeeValidator implements IValidator<Employee> {
  @Override
  public boolean isValid(Employee entity) {
    final String firstName = entity.getFirstName();
    final String lastName = entity.getLastName();

    return firstName != null && lastName != null && !firstName.isBlank() && !lastName.isBlank();
  }
}
