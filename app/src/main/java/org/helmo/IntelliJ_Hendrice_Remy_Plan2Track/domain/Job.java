package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Job {

    private final String name;
    private final String description;
    private final int duration;
    private final List<Job> priorJobs;

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getDuration(){
        return duration;
    }

    public Iterator<Job> getPriorJobs(){
        return priorJobs.iterator();
    }

    public Job(String name, String description, int duration, List<Job> priorJobs){
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.priorJobs = new ArrayList<>(priorJobs);
    }

    public Job(String name){
        this(name, "",1,new ArrayList<>());
    }
}
