package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTask;

import java.time.LocalDate;

public class PlannedTask {
    private final PertTask task;
    private final LocalDate date;

    public PlannedTask(PertTask task, LocalDate date){
        this.task = task;
        this.date = date;
    }

    public String getTaskName(){ return task.getName(); }

    public LocalDate getDate(){
        return date;
    }
}
