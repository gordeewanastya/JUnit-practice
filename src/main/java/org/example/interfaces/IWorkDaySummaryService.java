package org.example.interfaces;

import java.time.Duration;
import org.example.model.Presence;
import org.example.model.WorkDaySummary;

public interface IWorkDaySummaryService {
    Duration getTotalWorkedTime();
    boolean hasIncompleteRecords(Presence presence);
    void addPresence(Presence presence);
    WorkDaySummary getWorkDaySummary();
}
