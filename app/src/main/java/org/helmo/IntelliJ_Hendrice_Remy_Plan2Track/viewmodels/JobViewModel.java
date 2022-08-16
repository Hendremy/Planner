package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Définit le modèle de vue d'une tâche.
 */
public class JobViewModel {

    private final String name;
    private final String description;
    private final int duration;
    private final String techName;
    private final String techCode;
    private final Set<JobViewModel> priorJobs;

    /**
     * Initialise le modèle de vue à partir d'une tâche.
     * @param job la tâche
     */
    public JobViewModel(Job job){
        this.name = job.getName();
        this.description = job.getDescription();
        this.duration = job.getDuration();
        this.techName = job.getTechnicianName();
        this.techCode = job.getTechnicianCode();
        this.priorJobs = initPriorJobs(job.getPriorJobs());
    }

    /**
     * Initialise le modèle de vue d'une tâche antérieure.
     * @param name le nom de la tâche antérieure
     * @param description la description de la tâche antérieure
     * @param duration la durée de la tâche antérieure
     */
    private JobViewModel(String name, String description, int duration){
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.techName = null;
        this.techCode = null;
        this.priorJobs = null;
    }

    /**
     * Intialise les modèles de vues des tâches antérieures.
     * @param priorJobs les tâches antérieures
     * @return les modèles de vues des tâches antérieures
     */
    private Set<JobViewModel> initPriorJobs(Iterable<Job> priorJobs){
        Set<JobViewModel> priorJobSet = new HashSet<>();
        for(Job job : priorJobs){
            priorJobSet.add(new JobViewModel(job.getName(), job.getDescription(), job.getDuration()));
        }
        return priorJobSet;
    }

    /**
     * Retourne le nom de la tâche.
     * @return le nom de la tâche
     */
    public String getName(){
        return name;
    }

    /**
     * Retourne la description de la tâche.
     * @return la description de la tâche
     */
    public String getDescription(){
        return description;
    }

    /**
     * Retourne la durée de la tâche.
     * @return la durée de la tâche
     */
    public String getDuration(){
        return Integer.toString(duration);
    }

    /**
     * Retourne le code du chef d'équipe si assigné.
     * @return le code du chef d'équipe si assigné
     */
    public String getTechCode(){
        return techCode == null ? "non assigné" : techCode;
    }

    /**
     * Retourne le nom du chef d'équipe si assigné.
     * @return le nom du chef d'équipe si assigné
     */
    private String getTechName(){
        return techName == null ? "non assigné" : techName;
    }

    /**
     * Retourne les modèles de vues des tâches antérieures.
     * @return les modèles de vues des tâches antérieures
     */
    public Collection<JobViewModel> getPriorJobs(){
        return new HashSet<>(priorJobs);
    }

    /**
     * Retourne la représentation en chaîne de caractères du modèle de vue.
     * @return la représentation en chaîne de caractères du modèle de vue
     */
    @Override
    public String toString(){
        return String.format("%s - %d j - %s", name, duration, getTechName());
    }
}
