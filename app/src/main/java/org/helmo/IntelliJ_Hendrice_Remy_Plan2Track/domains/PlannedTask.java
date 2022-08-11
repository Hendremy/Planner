package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTask;

import java.util.Date;

public class PlannedTask {
    private final PertTask task;
    private final Date date;

    public PlannedTask(PertTask task, Date date){
        this.task = task;
        this.date = date;
    }

    public String getTaskName(){ return task.getName(); }

    public Date getDate(){
        return date;
    }
}
