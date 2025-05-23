package org.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorkDaySummary {
    private Employee employee;
    private LocalDate date;
    private List<Presence> presences;

    public WorkDaySummary(){}

    public WorkDaySummary(Employee employee, LocalDate date, List<Presence> presences) {
        this.employee = employee;
        this.date = date;
        this.presences = new ArrayList<>(presences);
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Presence> getPresences() {
        return presences;
    }

    public void setPresences(List<Presence> presences) {
        this.presences = presences;
    }
}
