package org.example.interfaces;

import org.example.model.Presence;
import org.example.model.WorkDaySummary;

import java.time.Duration;
import java.util.List;

public interface IWorkDaySummaryService {
    Duration getTotalWorkedTime();
    boolean hasIncompleteRecords(Presence presence);
    void addPresence(Presence presence);
    WorkDaySummary getWorkDaySummary();
}
