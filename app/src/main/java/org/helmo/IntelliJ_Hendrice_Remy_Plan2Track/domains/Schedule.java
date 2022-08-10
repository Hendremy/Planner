package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTask;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Schedule {

    private final Map<PertTask, Date> schedule;

    public Schedule(){
        schedule = new HashMap<>();
    }

    public void add(PertTask task, Date date){
        schedule.put(task, date);
    }

    public Map<PertTask, Date> getSchedule(){
        return new HashMap<>(schedule);
    }
}
