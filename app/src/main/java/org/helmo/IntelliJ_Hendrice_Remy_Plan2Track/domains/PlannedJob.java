package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import java.time.LocalDate;

/**
 * Définit une tâche associée à une date de début.
 */
public class PlannedJob {
    private final Job job;
    private final LocalDate date;

    /**
     * Initialise la tâche et la date de début.
     * @param job la tâche
     * @param date la date de début
     */
    public PlannedJob(Job job, LocalDate date){
        this.job = job;
        this.date = date;
    }

    /**
     * Retourne le nom de la tâche
     * @return le nom de la tâche
     */
    public String getName(){ return job.getName(); }

    /**
     * Retourne la date de début.
     * @return la date de début
     */
    public LocalDate getStartDate(){
        return date;
    }

    /**
     * Retourne la date de fin.
     * @return la date de fin
     */
    public LocalDate getEndDate(){
        return getStartDate().plusDays(job.getDuration());
    }

    /**
     * Retourne le nom du chef d'équipe assigné.
     * @return le nom du chef d'équipe assigné
     */
    public String getTechName(){return job.getTechnicianName();}

    /**
     * Retourne le code du chef d'équipe assigné
     * @return le code du chef d'équipe assigné
     */
    public String getTechCode(){ return job.getTechnicianCode();}

    /**
     * Retourne la description de la tâche.
     * @return la description de la tâche
     */
    public String getDescription(){
        return job.getDescription();
    }
}
