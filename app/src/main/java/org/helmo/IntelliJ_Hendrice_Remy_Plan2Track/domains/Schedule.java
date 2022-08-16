package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import java.time.LocalDate;
import java.util.*;

/**
 * Définit le programme d'un montage.
 */
public class Schedule {

    private final List<PlannedJob> schedule;
    private final String name;

    /**
     * Initialise le nom du programme.
     * @param name le nom du programme
     */
    public Schedule(String name){
        this.name = name;
        schedule = new LinkedList<>();
    }

    /**
     * Retourne le nom du programme.
     * @return le nom du programme
     */
    public String getName(){
        return name;
    }

    /**
     * Ajoute une tâche plannifiée au programme.
     * @param job la tâche
     * @param date la date de début de la tâche
     */
    public void add(Job job, LocalDate date){
        schedule.add(new PlannedJob(job, date));
    }

    /**
     * Retourne les tâches plannifiées du programme.
     * @return les tâches plannifiées du programme
     */
    public List<PlannedJob> getSchedule(){
        return new LinkedList<>(schedule);
    }
}
