package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTask;

import java.time.LocalDate;
import java.util.*;

public class Schedule {

    private final List<PlannedTask> schedule;

    public Schedule(){
        schedule = new LinkedList<>();
    }

    public void add(PertTask task, LocalDate date){
        schedule.add(new PlannedTask(task, date));
    }

    public List<PlannedTask> getSchedule(){
        return new LinkedList<>(schedule);
    }
}
