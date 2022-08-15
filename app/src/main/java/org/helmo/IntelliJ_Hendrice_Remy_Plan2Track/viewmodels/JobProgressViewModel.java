package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.JobProgress;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JobProgressViewModel {
    private final JobProgress jobProgress;
    private final DateTimeFormatter dateTimeFormatter;

    public JobProgressViewModel(JobProgress jobProgress){
        this.jobProgress = jobProgress;
        this.dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
    }

    public String getName(){
        return jobProgress.getName();
    }

    public String getExpectedStartDate(){
        return formatDate(jobProgress.getExpectedStart());
    }

    public String getExpectedEndDate(){
        return formatDate(jobProgress.getExpectedEnd());
    }

    public String getActualStartDate(){
        return formatDate(jobProgress.getActualStart());
    }

    public String getActualEndDate(){
        return formatDate(jobProgress.getActualEnd());
    }

    public String getStatus(){
        return jobProgress.getStatus().getRepresentation();
    }

    public String getIsOnTime(){
        return jobProgress.isOnTime() ? "" : "En retard !";
    }

    private String formatDate(LocalDate date){
        return date.format(dateTimeFormatter);
    }
}
