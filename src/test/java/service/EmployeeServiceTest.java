package service;

import org.example.interfaces.IEmployeeService;
import org.example.model.Employee;
import org.example.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceTest {

  private IEmployeeService employeeService;
  private Employee employee;

  @BeforeEach
  public void setUpEmployee() {
    employee = new Employee(1, "Anastasia", "Gordeeva", "IT", "Developer");
    employeeService = new EmployeeService();
  }

  @Test
  public void testGetFullName() {
    String expectedFullName = employee.getFirstName() + " " + employee.getLastName();

    assertEquals(expectedFullName, employeeService.getFullName(employee));
  }

  @Test
  public void testGetFullNameWhenFirstNameIsNull() {
    employee.setFirstName(null);
    String expectedFullName = employee.getLastName();

    assertEquals(expectedFullName, employeeService.getFullName(employee));
  }

  @Test
  public void testGetFullNameWhenLastNameIsNull() {
    employee.setLastName(null);
    String expectedFullName = employee.getFirstName();

    assertEquals(expectedFullName, employeeService.getFullName(employee));
  }

  @Test
  public void testGetFullNameWhenBothNamesAreNull() {
    employee.setFirstName(null);
    employee.setLastName(null);
    String expectedFullName = "No full name";

    assertEquals(expectedFullName, employeeService.getFullName(employee));
  }

  @Test
  public void testIsValidWhenFirstAndLastNameArePresent() {
    boolean expectedIsValid = true;

    assertEquals(expectedIsValid, employeeService.isValid(employee));
  }

  @Test
  public void testIsValidWhenFirstNameIsBlank() {
    employee.setFirstName(" ");
    boolean expectedIsValid = false;

    assertEquals(expectedIsValid, employeeService.isValid(employee));
  }

  @Test
  public void testIsValidWhenLastNameIsBlank() {
    employee.setLastName(" ");
    boolean expectedIsValid = false;

    assertEquals(expectedIsValid, employeeService.isValid(employee));
  }

  @Test
  public void testIsValidWhenBothNamesAreNotPresent() {
    employee.setFirstName(null);
    employee.setLastName(null);
    boolean expectedIsValid = false;

    assertEquals(expectedIsValid, employeeService.isValid(employee));
  }

  @Test
  public void testIsValidWhenBothNamesAreBlank() {
    employee.setFirstName(" ");
    employee.setLastName(" ");
    boolean expectedIsValid = false;

    assertEquals(expectedIsValid, employeeService.isValid(employee));
  }
}
