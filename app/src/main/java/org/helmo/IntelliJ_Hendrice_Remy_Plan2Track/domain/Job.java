package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Job {

    private final String name;
    private final String description;
    private final int duration;
    private final Map<String,Job> priorJobs;
    private Technician technician;

    public Job(String name, String description, int duration){
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.priorJobs = new HashMap<>();
        this.technician = null;
    }

    public Job(String name){
        this(name, "",1);
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getDuration(){
        return duration;
    }

    public String getTechnicianName(){
        return technician == null ? null : technician.getFullName();
    }

    public void setTechnician(Technician technician){
        this.technician = technician;
    }

    public Iterable<Job> getPriorJobs(){
        return new ArrayList<>(priorJobs.values());
    }

    public boolean hasPriorJobs(){
        return priorJobs.size() > 0;
    }

    public boolean hasPrior(String name){
        return priorJobs.containsKey(name);
    }

    public boolean hasPrior(Job job){
        return hasPrior(job.getName());
    }

    public void addPrior(Job job){
        if(job != null && (!hasPrior(job) || !job.getName().equals(this.name))){
            priorJobs.put(job.getName(), job);
        }
    }

    public void removePrior(Job job){
        if(job != null && hasPrior(job)){
            priorJobs.remove(job.getName());
        }
    }
}
