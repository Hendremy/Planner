package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * D�finit l'avancement d'un montage publi�.
 */
public class PlanningProgress {
    private final String name;
    private final Set<JobProgress> jobs;

    /**
     * Initialise le nom et les avancements des t�ches du montage.
     * @param name le nom du montage
     * @param jobs les avancements des t�ches du montage
     */
    public PlanningProgress(String name, Collection<JobProgress> jobs){
        this.name = name;
        this.jobs = new HashSet<>(jobs);
    }

    /**
     * Retourne les avancements des t�ches du montage.
     * @return les avancements des t�ches du montage
     */
    public Collection<JobProgress> getJobs(){
        return new HashSet<>(jobs);
    }

    /**
     * Retourne le nom du montage.
     * @return le nom du montage
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne le retard du montage en jours.
     * @return le retard du montage en jours
     */
    public long getDelay(){
        LocalDate latestExpectedEnd = getLatestExpectedEnd();
        if(latestExpectedEnd.isEqual(LocalDate.MIN)){
            return 0;
        }else{
            return ChronoUnit.DAYS.between(latestExpectedEnd, LocalDate.now());
        }
    }

    /**
     * Retourne la date de fin au plus t�t attendue du montage.
     * @return la date de fin au plus t�t attendue du montage
     */
    private LocalDate getLatestExpectedEnd(){
        LocalDate date = LocalDate.MIN;
        for(JobProgress job : jobs){
            if(!job.isOnTime()){
                date = maxDate(date, job.getExpectedEnd());
            }
        }
        return date;
    }

    /**
     * Retourne la plus grande des deux dates.
     * @param dateA la premi�re date
     * @param dateB la deuxi�me date
     * @return la plus grande des deux dates
     */
    private LocalDate maxDate(LocalDate dateA, LocalDate dateB){
        return dateA.isAfter(dateB) ? dateA : dateB;
    }

}
