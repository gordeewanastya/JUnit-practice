package org.example.repository;

import org.example.interfaces.IRepository;
import org.example.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeRepository implements IRepository<Employee> {
  private final Connection connection;

  public EmployeeRepository(Connection connection) {
    this.connection = connection;
  }

  @Override
  public Employee save(Employee employee) throws SQLException {
    String sql =
        "INSERT INTO employee (id, first_name, last_name, department, job_title) VALUES (?,?,?,?,?)";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, employee.getId());
      statement.setString(2, employee.getFirstName());
      statement.setString(3, employee.getLastName());
      statement.setString(4, employee.getDepartment());
      statement.setString(5, employee.getJobTitle());
      statement.executeUpdate();
    }
    return employee;
  }
}
