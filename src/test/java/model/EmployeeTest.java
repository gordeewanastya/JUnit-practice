package model;

import org.example.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    private Employee employee;

    @BeforeEach
    public void setUpEmployee(){
        employee = new Employee(1,"Anastasia", "Gordeeva", "IT", "Developer");
    }

    @Test
    public void testGetFullName(){
        String expectedFullName = employee.getFirstName() + " " + employee.getLastName();
        assertEquals(expectedFullName, employee.getFullName());
    }

    @Test
    public void testGetFullNameWhenFirstNameIsNull(){
        employee.setFirstName(null);
        String expectedFullName = employee.getLastName();

        assertEquals(expectedFullName, employee.getFullName());
    }

    @Test
    public void testGetFullNameWhenLastNameIsNull(){
        employee.setLastName(null);
        String expectedFullName = employee.getFirstName();

        assertEquals(expectedFullName, employee.getFullName());
    }

    @Test
    public void testGetFullNameWhenBothNamesAreNull(){
        employee.setFirstName(null);
        employee.setLastName(null);

        String expectedFullName = "";

        assertEquals(expectedFullName, employee.getFullName());
    }

}
