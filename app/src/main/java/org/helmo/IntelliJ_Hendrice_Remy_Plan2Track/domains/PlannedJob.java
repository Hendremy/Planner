package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import java.time.LocalDate;

public class PlannedJob {
    private final Job job;
    private final LocalDate date;

    public PlannedJob(Job job, LocalDate date){
        this.job = job;
        this.date = date;
    }

    public String getName(){ return job.getName(); }

    public LocalDate getStartDate(){
        return date;
    }

    public LocalDate getEndDate(){
        return getStartDate().plusDays(job.getDuration());
    }

    public String getTechName(){return job.getTechnicianName();}

    public String getTechCode(){ return job.getTechnicianCode();}

    public String getDescription(){
        return job.getDescription();
    }
}
