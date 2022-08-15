package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlannedJob;

public class ScheduleRowViewModel {

    private final PlannedJob plannedJob;

    public ScheduleRowViewModel(PlannedJob plannedJob){
        this.plannedJob = plannedJob;
    }

    public String getTaskName(){
        return plannedJob.getName();
    }

    public String getTechName(){
        return plannedJob.getTechName();
    }

    public String getStartDate(){
        return plannedJob.getStartDate().toString();
    }
}
