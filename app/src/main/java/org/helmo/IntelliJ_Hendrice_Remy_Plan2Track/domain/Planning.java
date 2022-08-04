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
