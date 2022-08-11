package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;

import java.util.Date;

public class ScheduleRowViewModel {

    private final Job task;
    private final Date startDate;

    public ScheduleRowViewModel(Job task, Date startDate){
        this.task = task;
        this.startDate = startDate;
    }

    public String getTaskName(){
        return task.getName();
    }

    public String getTechName(){
        return task.getTechnicianName();
    }

    public String getStartDate(){
        return startDate.toString();
    }
}
