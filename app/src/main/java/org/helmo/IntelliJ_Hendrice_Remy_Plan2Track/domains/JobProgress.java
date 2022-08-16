package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import java.time.LocalDate;

/**
 * D�finit l'avancement d'une t�che qui a �t� publi�e.
 */
public class JobProgress {
    private final String name;
    private final TimeReport timeReport;

    /**
     * Initialise le nom de la t�che et ses �ch�ances attendues & r�elles
     * @param name le nom de la t�che
     * @param timeReport les �ch�ances attendues & r�elles
     */
    public JobProgress(String name, TimeReport timeReport){
        this.name = name;
        this.timeReport = timeReport;
    }

    /**
     * Retourne le nom de la t�che
     * @return le nom de la t�che
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne la date de d�but pr�vue.
     * @return la date de d�but pr�vue
     */
    public LocalDate getExpectedStart(){
        return timeReport.getExpectedStart();
    }

    /**
     * Retourne la date de fin pr�vue.
     * @return la date de fin pr�vue.
     */
    public LocalDate getExpectedEnd(){
        return timeReport.getExpectedEnd();
    }

    /**
     * Retourne la date de d�but r�elle.
     * @return la date de d�but r�elle
     */
    public LocalDate getActualStart(){
        return timeReport.getActualStart();
    }

    /**
     * Retourne la date de fin r�elle.
     * @return la date de fin relle
     */
    public LocalDate getActualEnd(){
        return timeReport.getActualEnd();
    }

    /**
     * Retourne le statut de la t�che
     * @return le statut de la t�che
     */
    public Status getStatus(){
        return timeReport.getStatus();
    }

    /**
     * Retourne vrai si la t�che est dans les temps, sinon faux.
     * @return vrai si la t�che est dans les temps, sinon faux
     */
    public boolean isOnTime(){
        return timeReport.isOnTime();
    }
}
