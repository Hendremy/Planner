package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import java.time.LocalDate;

public class PlannedJob {
    private final Job job;
    private final LocalDate date;

    public PlannedJob(Job job, LocalDate date){
        this.job = job;
        this.date = date;
    }

    public String getTaskName(){ return job.getName(); }

    public LocalDate getDate(){
        return date;
    }

    public String getTechName(){return job.getTechnicianName();}
}
