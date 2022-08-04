package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain;

import java.util.*;

public class Planning {

    private String name;
    private final Map<String,Job> jobs;

    public Planning(String name){
        this.name = name;
        this.jobs = new HashMap<>();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){ this.name = name;}

    public Iterable<Job> getJobs() {
        return jobs.values();
    }

    public Job getJobByName(String name){
        return jobs.get(name);
    }

    public void addJob(Job job){
        jobs.put(job.getName(), job);
    }

    public void removeJob(Job job){
        if(job != null){
            for (Job otherJob : jobs.values()) {
                otherJob.removePredecessor(job);
            }
            jobs.remove(job.getName());
        }
    }

    public int countPriorJob(Job prior){
        int occ = 0;
        for (Job job : this.jobs.values()) {
            if(job.hasPredecessor(prior)){
                occ++;
            }
        }
        return occ;
    }

    public boolean isEmpty(){
        return jobs.isEmpty();
    }
}
