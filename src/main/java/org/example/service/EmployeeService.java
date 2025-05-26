package org.example.service;

import org.example.interfaces.IEmployeeService;
import org.example.model.Employee;

import static java.lang.String.join;
import static java.util.Objects.requireNonNullElse;

public class EmployeeService implements IEmployeeService {
    private final Employee employee;

    public EmployeeService(Employee employee){
        if (employee == null) throw new IllegalArgumentException("Employee cannot be null");
        this.employee = employee;
    }

    @Override
    public String getFullName(){

        if (!isValid()){
            return requireNonNullElse(employee.getFirstName(), requireNonNullElse(employee.getLastName(), "No full name"));
        }

        return join(" ", employee.getFirstName(), employee.getLastName());
    }

    @Override
    public boolean isValid(){
        final String firstName = employee.getFirstName();
        final String lastName = employee.getLastName();

        return firstName != null && lastName != null && !firstName.isBlank() && !lastName.isBlank();
    }
}
