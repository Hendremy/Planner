package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import java.time.LocalDate;

/**
 * Définit une période.
 */
public class DaySpan {
    private final LocalDate start;
    private final LocalDate end;

    /**
     * Initialise la date de début et de fin de la période.
     * @param start la date de début
     * @param end la date de fin
     */
    public DaySpan(LocalDate start, LocalDate end){
        this.start = start;
        this.end = end;
    }

    /**
     * Retourne la date de début de la période.
     * @return la date de début de la période
     */
    public LocalDate getStart(){
        return start;
    }

    /**
     * Retourne la date de fin de la période.
     * @return la date de fin de la période
     */
    public LocalDate getEnd(){
        return end;
    }
}
