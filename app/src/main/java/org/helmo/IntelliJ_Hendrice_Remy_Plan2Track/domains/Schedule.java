package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import java.time.LocalDate;
import java.util.*;

public class Schedule {

    private final List<PlannedJob> schedule;

    public Schedule(){
        schedule = new LinkedList<>();
    }

    public void add(Job job, LocalDate date){
        schedule.add(new PlannedJob(job, date));
    }

    public List<PlannedJob> getSchedule(){
        return new LinkedList<>(schedule);
    }
}
