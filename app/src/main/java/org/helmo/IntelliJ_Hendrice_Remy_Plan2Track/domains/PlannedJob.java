package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import java.time.LocalDate;

/**
 * D�finit une t�che associ�e � une date de d�but.
 */
public class PlannedJob {
    private final Job job;
    private final LocalDate date;

    /**
     * Initialise la t�che et la date de d�but.
     * @param job la t�che
     * @param date la date de d�but
     */
    public PlannedJob(Job job, LocalDate date){
        this.job = job;
        this.date = date;
    }

    /**
     * Retourne le nom de la t�che
     * @return le nom de la t�che
     */
    public String getName(){ return job.getName(); }

    /**
     * Retourne la date de d�but.
     * @return la date de d�but
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
     * Retourne le nom du chef d'�quipe assign�.
     * @return le nom du chef d'�quipe assign�
     */
    public String getTechName(){return job.getTechnicianName();}

    /**
     * Retourne le code du chef d'�quipe assign�
     * @return le code du chef d'�quipe assign�
     */
    public String getTechCode(){ return job.getTechnicianCode();}

    /**
     * Retourne la description de la t�che.
     * @return la description de la t�che
     */
    public String getDescription(){
        return job.getDescription();
    }
}
