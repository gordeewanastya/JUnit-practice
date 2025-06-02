package org.example.utils;

import org.example.interfaces.IRecordMapper;
import org.example.model.Employee;

public class EmployeeRecordMapper implements IRecordMapper<Employee> {
    @Override
    public Employee mapToEntity(String[] record) {
        Employee employee = new Employee();
        employee.setFirstName(record[0]);
        employee.setLastName(record[1]);
        employee.setDepartment(record[2]);
        employee.setJobTitle(record[3]);
        return employee;
    }
}
