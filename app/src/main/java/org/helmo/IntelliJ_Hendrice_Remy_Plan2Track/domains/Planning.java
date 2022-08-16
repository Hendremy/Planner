package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertNetwork;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTask;

import java.util.*;

/**
 * Définit un montage décomposé en tâches.
 */
public class Planning implements PertNetwork {

    private String name;
    private final Map<String,Job> jobs;

    /**
     * Initialise le nom du montage.
     * @param name le nom du montage
     */
    public Planning(String name){
        this.name = name;
        this.jobs = new HashMap<>();
    }

    /**
     * Retourne le nom du montage.
     * @return le nom du montage
     */
    public String getName(){
        return name;
    }

    /**
     * Définit le nom du montage.
     * @param name le nom du montage
     */
    public void setName(String name){ this.name = name;}

    /**
     * Retourne les tâches du montages.
     * @return les tâches du montage
     */
    public Iterable<Job> getJobs() {
        return jobs.values();
    }

    /**
     * Retourne la tâche correspondant au nom en paramètre ou null si elle n'est pas trouvée.
     * @param name le nom de la tâche
     * @return la tâche correspondant au nom en paramètre ou null si elle n'est pas trouvée
     */
    public Job getJobByName(String name){
        return jobs.get(name);
    }

    /**
     * Crée et ajoute une tâche au montage ainsi que ses prédécesseurs au besoin.
     * @param name le nom de la tâche
     * @param description la description de la tâche
     * @param duration la durée de la tâche
     * @param priorJobs les noms des prédécesseurs de la tâche
     */
    public void addJob(String name, String description, int duration, Iterable<String> priorJobs){
        if(jobs.containsKey(name)) return;
        Job newJob = new Job(name, description, duration);
        for(String priorJob : priorJobs){
            addPriorToJob(priorJob, newJob);
        }
        addJob(newJob);
    }

    /**
     * Ajoute une tâche au montage.
     * @param job la tâche à ajouter
     */
    private void addJob(Job job){
        jobs.put(job.getName(), job);
    }

    /**
     * Ajoute et crée au besoin une tâche en tant que prédécesseur d'une tâche déjà existante.
     * @param priorName le nom de la tâche prédécesseur
     * @param job la tâche à laquelle il faut ajouter le prédécesseur
     */
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

    /**
     * Retire une tâche du montage et des prédécesseurs des autres tâches si elle s'y trouve.
     * @param job la tâche à retirer
     */
    private void removeJob(Job job){
        if(job != null){
            for (Job otherJob : jobs.values()) {
                otherJob.removePredecessor(job);
            }
            jobs.remove(job.getName());
        }
    }

    /**
     * Retire une tâche sur base de son nom.
     * @param name le nom de la tâche à retirer
     * @throws JobNotFoundException survient si le nom de la tâche n'est pas trouvé
     */
    public void removeJob(String name) throws JobNotFoundException {
        removeJob(findJob(name));
    }

    /**
     * Compte le nombre de fois que la tâche est prédécesseur d'une autre.
     * @param prior la tâche à évaluer
     * @return le nombre de fois que la tâche est prédécesseur d'une autre
     */
    private int countPriorJob(Job prior){
        int occ = 0;
        for (Job job : this.jobs.values()) {
            if(job.hasPredecessor(prior)){
                occ++;
            }
        }
        return occ;
    }

    /**
     * Compte le nombre de fois que la tâche est prédécesseur d'une autre à partir de son nom.
     * @param name le nom de la tâche à évaluer
     * @return le nombre de fois que la tâche est prédécesseur d'une autre
     * @throws JobNotFoundException survient si le nom de la tâche n'est pas trouvé
     */
    public int countPriorJob(String name) throws JobNotFoundException {
        return countPriorJob(findJob(name));
    }

    /**
     * Trouve une tâche sur base de son nom ou lance une exception si elle n'est pas trouvée.
     * @param name le nom de la tâche
     * @return la tâche correspondant au nom
     * @throws JobNotFoundException survient si le nom de la tâche n'est pas trouvé
     */
    private Job findJob(String name) throws JobNotFoundException {
        var job = getJobByName(name);
        if(job == null) throw new JobNotFoundException();
        return job;
    }

    /**
     * Retourne vrai si le montage n'a pas de tâches, sinon faux.
     * @return vrai si le montage n'a pas de tâches, sinon faux
     */
    public boolean isEmpty(){
        return jobs.isEmpty();
    }

    /**
     * Retourne vrai si toutes les tâches sont assignées, sinon faux.
     * @return vrai si toutes les tâches sont assignées, sinon faux.
     */
    public boolean allTasksAssigned(){
        for(Job job : jobs.values()){
            if(!job.isAssigned()) return false;
        }
        return true;
    }

    /**
     * Retourne vrai si le montage contient une tâche correspondant au nom, sinon faux.
     * @param name le nom de la tâche
     * @return vrai si le montage contient une tâche correspondant au nom, sinon faux
     */
    public boolean hasJob(String name){
        return jobs.containsKey(name);
    }

    @Override
    public Iterable<PertTask> getTasks() {
        return new HashSet<>(jobs.values());
    }
}
