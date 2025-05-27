package org.example.service;

import org.example.interfaces.IPresenceCalculator;
import org.example.interfaces.IWorkDaySummaryService;
import org.example.model.Presence;
import org.example.model.WorkDaySummary;

import java.time.Duration;

public class WorkDaySummaryService implements IWorkDaySummaryService {
    private WorkDaySummary workDaySummary;

    public WorkDaySummaryService(){}

    public WorkDaySummaryService(WorkDaySummary workDaySummary){
        this.workDaySummary = workDaySummary;
    }

    @Override
    public WorkDaySummary getWorkDaySummary() {
        return workDaySummary;
    }

    @Override
    public Duration getTotalWorkedTime() {
        Duration total = Duration.ZERO;

        for (Presence presence : workDaySummary.getPresences()) {
            if (!hasIncompleteRecords(presence)){
                IPresenceCalculator calculator = new PresenceCalculator(presence);
                total = total.plus(calculator.getWorkingHoursDuration());
            }
        }
        return total;
    }

    @Override
    public boolean hasIncompleteRecords(Presence presence) {
        IPresenceCalculator presenceCalculator = new PresenceCalculator(presence);
        if (presenceCalculator.areWorkingHoursValid()){
            return false;
        }

        return true;
    }

    @Override
    public void addPresence(Presence presence) {
        if (
                presence != null
                && workDaySummary.getDate().equals(presence.getDate())
                && workDaySummary.getEmployee().equals(presence.getEmployee())
                && !workDaySummary.getPresences().contains(presence)
        ){
            workDaySummary.getPresences().add(presence);
        }
    }


}
