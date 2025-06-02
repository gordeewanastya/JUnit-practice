package org.example.service;

import static java.lang.String.join;
import static java.util.Objects.requireNonNullElse;

import java.sql.SQLException;
import org.example.interfaces.IEmployeeService;
import org.example.interfaces.IRepository;
import org.example.model.Employee;

public class EmployeeService implements IEmployeeService {
  private final IRepository<Employee> employeeRepository;

  public EmployeeService(IRepository<Employee> employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

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

  @Override
  public Employee save(Employee employee) throws SQLException {
    return employeeRepository.save(employee);
  }
}
