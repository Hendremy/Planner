package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import java.time.LocalDate;

/**
 * D�finit une p�riode.
 */
public class DaySpan {
    private final LocalDate start;
    private final LocalDate end;

    /**
     * Initialise la date de d�but et de fin de la p�riode.
     * @param start la date de d�but
     * @param end la date de fin
     */
    public DaySpan(LocalDate start, LocalDate end){
        this.start = start;
        this.end = end;
    }

    /**
     * Retourne la date de d�but de la p�riode.
     * @return la date de d�but de la p�riode
     */
    public LocalDate getStart(){
        return start;
    }

    /**
     * Retourne la date de fin de la p�riode.
     * @return la date de fin de la p�riode
     */
    public LocalDate getEnd(){
        return end;
    }
}
