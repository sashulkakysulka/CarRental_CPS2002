package org.Vehicle.Model;

import java.time.LocalDate;

public class DateRange {

    Long id;
    private LocalDate startDate;
    private LocalDate endDate;

    public DateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate(){
        return startDate;
    }
    public LocalDate getEndDate(){
        return endDate;
    }
}
