package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.algo.PertTask;

import java.util.*;

public class Job implements PertTask {

    private final String name;
    private final String description;
    private final int duration;
    private final Set<Job> priorJobs;
    private Technician technician;

    public Job(String name, String description, int duration){
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.priorJobs = new HashSet<>();
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

    public String getTechnicianName(){
        return technician == null ? null : technician.getFullName();
    }

    public void setTechnician(Technician technician){
        this.technician = technician;
    }

    public void addPredecessor(Job job){
        if(job != null && (!hasPredecessor(job) || !job.equals(this))){
            priorJobs.add(job);
        }
    }

    public void removePredecessor(Job job){
        if(job != null && hasPredecessor(job)){
            priorJobs.remove(job);
        }
    }

    public Iterable<Job> getPriorJobs(){
        return new ArrayList<>(priorJobs);
    }

    @Override
    public Set<PertTask> getPredecessors(){
        return new HashSet<>(priorJobs);
    }

    @Override
    public boolean hasPredecessors(){
        return !getPredecessors().isEmpty();
    }

    @Override
    public int getDuration(){
        return duration;
    }

    @Override
    public boolean hasPredecessor(PertTask task){
        return getPredecessors().contains(task);
    }

}
