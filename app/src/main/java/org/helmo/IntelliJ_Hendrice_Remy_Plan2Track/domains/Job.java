package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTask;

import java.util.*;

/**
 * Définit une tâche d'un montage
 */
public class Job implements PertTask {

    private final String name;
    private final String description;
    private final int duration;
    private final Set<Job> priorJobs;
    private Technician technician;

    /**
     * Initialise le nom, la description et la durée de la tâche.
     * @param name le nom de la tâche
     * @param description la description de la tâche
     * @param duration la durée de la tâche
     */
    public Job(String name, String description, int duration){
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.priorJobs = new HashSet<>();
        this.technician = null;
    }

    /**
     * Initialise une tâche par défaut à partir d'un nom.
     * @param name le nom de la tâche
     */
    public Job(String name){
        this(name, "",1);
    }

    /**
     * Retourne la description de la tâche.
     * @return la description de la tâche
     */
    public String getDescription(){
        return description;
    }

    /**
     * Retourne le nom du chef d'équipe assigné ou null si pas assigné.
     * @return le nom du chef d'équipe assigné ou null si pas assigné
     */
    public String getTechnicianName(){
        return technician == null ? null : technician.getFullName();
    }

    /**
     * Retourne le code du chef d'équipe assigné ou null si pas assigné.
     * @return le code du chef d'équipe assigné ou null si pas assigné
     */
    public String getTechnicianCode(){
        return technician == null ? null : technician.getCode();
    }

    /**
     * Définit le chef d'équipe assigné à la tâche
     * @param technician le chef d'équipe à assigner
     */
    public void setTechnician(Technician technician){
        this.technician = technician;
    }

    /**
     * Ajout une tâche aux prédécesseurs de cette tâche
     * @param job la tâche à ajouter
     */
    public void addPredecessor(Job job){
        if(job != null && (!hasPredecessor(job) || !job.equals(this))){
            priorJobs.add(job);
        }
    }

    /**
     * Retire une tâche des prédécesseurs de cette tâche si c'est un prédécesseur
     * @param job la tâche prédécesseur
     */
    public void removePredecessor(Job job){
        if(job != null && hasPredecessor(job)){
            priorJobs.remove(job);
        }
    }

    /**
     * Retourne les tâches prédécesseur de la tâche.
     * @return les tâches prédécesseur de la tâche
     */
    public Iterable<Job> getPriorJobs(){
        return new ArrayList<>(priorJobs);
    }

    /**
     * Retourne vrai si la tâche est assignée à un chef d'équipe, sinon faux.
     * @return vrai si la tâche est assignée à un chef d'équipe, sinon faux
     */
    public boolean isAssigned(){
        return technician != null;
    }

    @Override
    public String getName(){
        return name;
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
