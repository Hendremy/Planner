package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTask;

import java.util.*;

/**
 * D�finit une t�che d'un montage
 */
public class Job implements PertTask {

    private final String name;
    private final String description;
    private final int duration;
    private final Set<Job> priorJobs;
    private Technician technician;

    /**
     * Initialise le nom, la description et la dur�e de la t�che.
     * @param name le nom de la t�che
     * @param description la description de la t�che
     * @param duration la dur�e de la t�che
     */
    public Job(String name, String description, int duration){
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.priorJobs = new HashSet<>();
        this.technician = null;
    }

    /**
     * Initialise une t�che par d�faut � partir d'un nom.
     * @param name le nom de la t�che
     */
    public Job(String name){
        this(name, "",1);
    }

    /**
     * Retourne la description de la t�che.
     * @return la description de la t�che
     */
    public String getDescription(){
        return description;
    }

    /**
     * Retourne le nom du chef d'�quipe assign� ou null si pas assign�.
     * @return le nom du chef d'�quipe assign� ou null si pas assign�
     */
    public String getTechnicianName(){
        return technician == null ? null : technician.getFullName();
    }

    /**
     * Retourne le code du chef d'�quipe assign� ou null si pas assign�.
     * @return le code du chef d'�quipe assign� ou null si pas assign�
     */
    public String getTechnicianCode(){
        return technician == null ? null : technician.getCode();
    }

    /**
     * D�finit le chef d'�quipe assign� � la t�che
     * @param technician le chef d'�quipe � assigner
     */
    public void setTechnician(Technician technician){
        this.technician = technician;
    }

    /**
     * Ajout une t�che aux pr�d�cesseurs de cette t�che
     * @param job la t�che � ajouter
     */
    public void addPredecessor(Job job){
        if(job != null && (!hasPredecessor(job) || !job.equals(this))){
            priorJobs.add(job);
        }
    }

    /**
     * Retire une t�che des pr�d�cesseurs de cette t�che si c'est un pr�d�cesseur
     * @param job la t�che pr�d�cesseur
     */
    public void removePredecessor(Job job){
        if(job != null && hasPredecessor(job)){
            priorJobs.remove(job);
        }
    }

    /**
     * Retourne les t�ches pr�d�cesseur de la t�che.
     * @return les t�ches pr�d�cesseur de la t�che
     */
    public Iterable<Job> getPriorJobs(){
        return new ArrayList<>(priorJobs);
    }

    /**
     * Retourne vrai si la t�che est assign�e � un chef d'�quipe, sinon faux.
     * @return vrai si la t�che est assign�e � un chef d'�quipe, sinon faux
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
