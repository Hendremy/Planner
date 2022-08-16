package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import java.time.LocalDate;

/**
 * D�finit le rapport des dates attendues & r�elles.
 */
public class TimeReport {
    private final DaySpan expected;
    private final DaySpan actual;

    /**
     * Initialise les p�riodes attendues & r�elles.
     * @param expected la p�riode attendue
     * @param actual la p�riode r�elle
     */
    public TimeReport(DaySpan expected, DaySpan actual){
        this.expected = expected;
        this.actual = actual;
    }

    /**
     * Retourne la date de d�but r�elle.
     * @return la date de d�but r�elle
     */
    public LocalDate getActualStart(){
        return actual.getStart();
    }

    /**
     * Retourne la date de fin r�elle.
     * @return la date de fin r�elle
     */
    public LocalDate getActualEnd(){
        return actual.getEnd();
    }

    /**
     * Retourne la date de d�but attendue.
     * @return la date de d�but attendue
     */
    public LocalDate getExpectedStart(){
        return expected.getStart();
    }

    /**
     * Retourne la date de fin attendue.
     * @return la date de fin attendue
     */
    public LocalDate getExpectedEnd(){
        return expected.getEnd();
    }

    /**
     * Retourne le statut de la t�che.
     * @return le statut de la t�che
     */
    public Status getStatus(){
        if(getActualStart() != null){
            if(getActualEnd() != null){
                return Status.DONE;
            }else{
                return Status.DOING;
            }
        }else{
            return Status.TODO;
        }
    }

    /**
     * Retourne vrai si la t�che est dans les temps, sinon faux.
     * @return vrai si la t�che est dans les temps, sinon faux
     */
    public boolean isOnTime(){
        Status status = getStatus();
        LocalDate expectedEnd = getExpectedEnd();
        if(status == Status.DONE){
            LocalDate actualEnd = getActualEnd();
            return expectedEnd.isEqual(actualEnd) || expectedEnd.isAfter(actualEnd);
        }else{
            LocalDate now = LocalDate.now();
            return expectedEnd.isEqual(now) || expectedEnd.isAfter(now);
        }
    }
}
