package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlannedJob;

/**
 * D�finit le mod�le de vue d'une rang�e du programme de motnage.
 */
public class ScheduleRowViewModel {

    private final PlannedJob plannedJob;

    /**
     * Initialise le mod�le de vue � partir d'une t�che plannifi�e.
     * @param plannedJob la t�che plannifi�e
     */
    public ScheduleRowViewModel(PlannedJob plannedJob){
        this.plannedJob = plannedJob;
    }

    /**
     * Retourne le nom de la t�che.
     * @return le nom de la t�che
     */
    public String getTaskName(){
        return plannedJob.getName();
    }

    /**
     * Retourne le nom du chef d'�quipe assign�.
     * @return le nom du chef d'�quipe assign�
     */
    public String getTechName(){
        return plannedJob.getTechName();
    }

    /**
     * Retourne la date de d�but de la t�che.
     * @return la date de d�but de la t�che
     */
    public String getStartDate(){
        return plannedJob.getStartDate().toString();
    }
}
