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

        String expectedFullName = "No full name";

        assertEquals(expectedFullName, employee.getFullName());
    }


    @Test
    public void testIsValidWhenFirstAndLastNameArePresent(){
        boolean expectedIsValid = true;
        assertEquals(expectedIsValid, employee.isValid());
    }

    @Test
    public void testIsValidWhenFirstNameIsBlank(){
        employee.setFirstName("");
        boolean expectedIsValid = false;

        assertEquals(expectedIsValid, employee.isValid());
    }

    @Test
    public void testIsValidWhenLastNameIsBlank(){
        employee.setLastName("");
        boolean expectedIsValid = false;

        assertEquals(expectedIsValid, employee.isValid());
    }

    @Test
    public void testIsValidWhenBothNamesAreNotPresent(){
        employee.setFirstName(null);
        employee.setLastName(null);
        boolean expectedIsValid = false;

        assertEquals(expectedIsValid, employee.isValid());
    }

    @Test
    public void testIsValidWhenBothNamesAreBlank(){
        employee.setFirstName("");
        employee.setLastName("");
        boolean expectedIsValid = false;

        assertEquals(expectedIsValid, employee.isValid());
    }
}
