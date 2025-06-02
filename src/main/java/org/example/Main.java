package org.example;

import org.example.db.DBConfiguration;
import org.example.interfaces.IEmployeeService;
import org.example.interfaces.IRecordMapper;
import org.example.interfaces.IValidator;
import org.example.model.Employee;
import org.example.repository.EmployeeRepository;
import org.example.service.EmployeeProcessor;
import org.example.service.EmployeeService;
import org.example.utils.CSVDataReader;
import org.example.utils.EmployeeRecordMapper;
import org.example.utils.EmployeeValidator;

import java.io.File;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) throws SQLException {
    File filePath =
        new File(
            "C:\\Users\\crme267\\IdeaProjects\\junit-practice\\src\\main\\resources\\employee_data.csv");
    IRecordMapper<Employee> employeeRecordMapper = new EmployeeRecordMapper();
    IValidator<Employee> employeeValidator = new EmployeeValidator();
    IEmployeeService employeeService =
        new EmployeeService(
            new EmployeeRepository(DBConfiguration.getConnection()));

    EmployeeProcessor processor =
        new EmployeeProcessor(
            new CSVDataReader<>(filePath, employeeRecordMapper),
            employeeValidator,
            employeeService);

    try {
      processor.processData();
      System.out.println("Employee data processed successfully");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
