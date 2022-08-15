package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import java.time.LocalDate;

public class JobProgress {
    private final String name;
    private final TimeReport timeReport;

    public JobProgress(String name, TimeReport timeReport){
        this.name = name;
        this.timeReport = timeReport;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpectedStart(){
        return timeReport.getExpectedStart();
    }

    public LocalDate getExpectedEnd(){
        return timeReport.getExpectedEnd();
    }

    public LocalDate getActualStart(){
        return timeReport.getActualStart();
    }

    public LocalDate getActualEnd(){
        return timeReport.getActualEnd();
    }

    public Status getStatus(){
        return timeReport.getStatus();
    }

    public boolean isOnTime(){
        return timeReport.isOnTime();
    }
}
