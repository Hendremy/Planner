package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertNetwork;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTask;

import java.util.*;

public class Planning implements PertNetwork {
    //TODO : Isoler collection de Job dans une classe avec les méthodes associées

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

    public void addJob(String name, String description, int duration, Iterable<String> priorJobs){
        if(jobs.containsKey(name)) return;
        Job newJob = new Job(name, description, duration);
        for(String priorJob : priorJobs){
            addPriorToJob(priorJob, newJob);
        }
        addJob(newJob);
    }

    private void addJob(Job job){
        jobs.put(job.getName(), job);
    }

    private void addPriorToJob(String priorName, Job job){
        if(priorName == null || job == null) return;
        Job priorJob = getJobByName(priorName);
        if(priorJob == null){
            priorJob = new Job(priorName);
            addJob(priorJob);
        }
        job.addPredecessor(priorJob);
    }


    /*CTT de la suppression d'une tâche:
    *
    * O(n) : n étant le nombre de tâches dans le planning:
    *
    *   -   On doit parcourir les n tâches du planning pour
    *       faire un simple retrait dans un HashSet qui a une CTT constante
    *   => CTT = n * 1 = O(n)
    *
    *   -  Ensuite, on enlève la tâche de la HashMap du planning
    *      qui est un simple retrait qui a une CTT constante
    *   => CTT = 1 = O(n)
    *
    * En additionant ces deux étapes, on trouve la CTT:
    *
    *  n + 1 = O(n)
    *
    * */
    private void removeJob(Job job){
        if(job != null){
            for (Job otherJob : jobs.values()) {
                otherJob.removePredecessor(job);
            }
            jobs.remove(job.getName());
        }
    }

    public void removeJob(String name) throws JobNotFoundException {
        removeJob(findJob(name));
    }

    private int countPriorJob(Job prior){
        int occ = 0;
        for (Job job : this.jobs.values()) {
            if(job.hasPredecessor(prior)){
                occ++;
            }
        }
        return occ;
    }

    public int countPriorJob(String name) throws JobNotFoundException {
        return countPriorJob(findJob(name));
    }

    private Job findJob(String name) throws JobNotFoundException {
        var job = getJobByName(name);
        if(job == null) throw new JobNotFoundException();
        return job;
    }

    public boolean isEmpty(){
        return jobs.isEmpty();
    }

    public boolean allTasksAssigned(){
        for(Job job : jobs.values()){
            if(!job.isAssigned()) return false;
        }
        return true;
    }

    public boolean hasJob(String name){
        return jobs.containsKey(name);
    }

    @Override
    public Iterable<PertTask> getTasks() {
        return new HashSet<>(jobs.values());
    }
}
