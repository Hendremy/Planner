package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class Job {

    private final String name;
    private final String description;
    private final int duration;
    private final Map<String,Job> priorJobs;

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
        return priorJobs.values().iterator();
    }

    public Job(String name, String description, int duration, Collection<Job> priorJobs){
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.priorJobs = new HashMap<>();
        for (var job : priorJobs) {
            this.priorJobs.put(job.getName(), job);
        }
    }

    public Job(String name){
        this(name, "",1,new ArrayList<>());
    }

    public boolean hasPrior(String name){
        return priorJobs.containsKey(name);
    }

    public boolean hasPrior(Job job){
        return hasPrior(job.getName());
    }

    public void removePrior(Job job){
        if(hasPrior(job)){
            priorJobs.remove(job.getName());
        }
    }
}
