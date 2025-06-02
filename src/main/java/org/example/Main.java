package org.example;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.example.db.DBConfiguration;
import org.example.interfaces.IEmployeeService;
import org.example.interfaces.IRecordMapper;
import org.example.interfaces.IValidator;
import org.example.model.Employee;
import org.example.repository.EmployeeRepository;
import org.example.service.EmployeeProcessor;
import org.example.service.EmployeeService;
import org.example.utils.*;

public class Main {
  public static void main(String[] args) throws SQLException {
    createTwoTypesEmployeeProcessors();
    compareDifferentEmployees();
  }

  private static void compareDifferentEmployees() {
    List<Employee> listOfEmployees = getListOfEmployees();
    listOfEmployees.sort(new EmployeeComparator());

    printSortedEmployees(listOfEmployees);
  }

  private static void printSortedEmployees(List<Employee> listOfEmployees) {
    System.out.println("Sorted employees by lastName: ");
    for (Employee employee : listOfEmployees) {
      System.out.println(employee);
    }
  }

  private static List<Employee> getListOfEmployees() {
    Employee emp1 = new Employee(1, "Alice", "Smith", "HR", "Recruiter");
    Employee emp2 = new Employee(2, "Bob", "Johnson", "IT", "Software Engineer");
    Employee emp3 = new Employee(3, "David", "Brown", "IT", "DevOps Engineer");
    List<Employee> employees =
        new ArrayList<>() {
          {
            add(emp1);
            add(emp2);
            add(emp3);
          }
        };

    return employees;
  }

  private static void createTwoTypesEmployeeProcessors() throws SQLException {
    File filePath =
        new File(
            "C:\\Users\\crme267\\IdeaProjects\\junit-practice\\src\\main\\resources\\employee_data.csv");
    IRecordMapper<Employee> employeeRecordMapper = new EmployeeRecordMapper();
    IValidator<Employee> employeeValidator = new EmployeeValidator();
    IEmployeeService employeeService =
        new EmployeeService(new EmployeeRepository(DBConfiguration.getConnection()), employeeValidator);

    EmployeeProcessor processor =
        getCSVEmployeeProcessor(filePath, employeeRecordMapper, employeeValidator, employeeService);

    EmployeeProcessor processor2 =
        getConsoleEmployeeProcessor(employeeRecordMapper, employeeValidator, employeeService);

    try {
      processor.processData();
      processor2.processData();
      System.out.println("Employee data processed successfully");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static EmployeeProcessor getConsoleEmployeeProcessor(
      IRecordMapper<Employee> employeeRecordMapper,
      IValidator<Employee> employeeValidator,
      IEmployeeService employeeService) {
    return new EmployeeProcessor(
        new ConsoleDataReader<>(employeeRecordMapper, new Scanner(System.in)),
        employeeValidator,
        employeeService);
  }

  private static EmployeeProcessor getCSVEmployeeProcessor(
      File filePath,
      IRecordMapper<Employee> employeeRecordMapper,
      IValidator<Employee> employeeValidator,
      IEmployeeService employeeService) {
    return new EmployeeProcessor(
        new CSVDataReader<>(filePath, employeeRecordMapper), employeeValidator, employeeService);
  }
}
