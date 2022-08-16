package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * D�finit le mod�le de vue d'une t�che.
 */
public class JobViewModel {

    private final String name;
    private final String description;
    private final int duration;
    private final String techName;
    private final String techCode;
    private final Set<JobViewModel> priorJobs;

    /**
     * Initialise le mod�le de vue � partir d'une t�che.
     * @param job la t�che
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
     * Initialise le mod�le de vue d'une t�che ant�rieure.
     * @param name le nom de la t�che ant�rieure
     * @param description la description de la t�che ant�rieure
     * @param duration la dur�e de la t�che ant�rieure
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
     * Intialise les mod�les de vues des t�ches ant�rieures.
     * @param priorJobs les t�ches ant�rieures
     * @return les mod�les de vues des t�ches ant�rieures
     */
    private Set<JobViewModel> initPriorJobs(Iterable<Job> priorJobs){
        Set<JobViewModel> priorJobSet = new HashSet<>();
        for(Job job : priorJobs){
            priorJobSet.add(new JobViewModel(job.getName(), job.getDescription(), job.getDuration()));
        }
        return priorJobSet;
    }

    /**
     * Retourne le nom de la t�che.
     * @return le nom de la t�che
     */
    public String getName(){
        return name;
    }

    /**
     * Retourne la description de la t�che.
     * @return la description de la t�che
     */
    public String getDescription(){
        return description;
    }

    /**
     * Retourne la dur�e de la t�che.
     * @return la dur�e de la t�che
     */
    public String getDuration(){
        return Integer.toString(duration);
    }

    /**
     * Retourne le code du chef d'�quipe si assign�.
     * @return le code du chef d'�quipe si assign�
     */
    public String getTechCode(){
        return techCode == null ? "non assign�" : techCode;
    }

    /**
     * Retourne le nom du chef d'�quipe si assign�.
     * @return le nom du chef d'�quipe si assign�
     */
    private String getTechName(){
        return techName == null ? "non assign�" : techName;
    }

    /**
     * Retourne les mod�les de vues des t�ches ant�rieures.
     * @return les mod�les de vues des t�ches ant�rieures
     */
    public Collection<JobViewModel> getPriorJobs(){
        return new HashSet<>(priorJobs);
    }

    /**
     * Retourne la repr�sentation en cha�ne de caract�res du mod�le de vue.
     * @return la repr�sentation en cha�ne de caract�res du mod�le de vue
     */
    @Override
    public String toString(){
        return String.format("%s - %d j - %s", name, duration, getTechName());
    }
}
