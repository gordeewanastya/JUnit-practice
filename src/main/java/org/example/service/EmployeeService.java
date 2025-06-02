package org.example.service;

import static java.lang.String.join;
import static java.util.Objects.requireNonNullElse;

import java.sql.SQLException;
import org.example.interfaces.IEmployeeService;
import org.example.interfaces.IRepository;
import org.example.interfaces.IValidator;
import org.example.model.Employee;

public class EmployeeService implements IEmployeeService {
  private final IRepository<Employee> employeeRepository;
  private final IValidator<Employee> validator;

  public EmployeeService(IRepository<Employee> employeeRepository, IValidator<Employee> validator) {
    this.employeeRepository = employeeRepository;
      this.validator = validator;
  }

  @Override
  public String getFullName(Employee employee) {
    if (!validator.isValid(employee)) {
      return requireNonNullElse(
          employee.getFirstName(), requireNonNullElse(employee.getLastName(), "No full name"));
    }

    return join(" ", employee.getFirstName(), employee.getLastName());
  }

  @Override
  public Employee save(Employee employee) throws SQLException {
    return employeeRepository.save(employee);
  }
}
