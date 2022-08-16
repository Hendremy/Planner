package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import java.time.LocalDate;

/**
 * Définit le rapport des dates attendues & réelles.
 */
public class TimeReport {
    private final DaySpan expected;
    private final DaySpan actual;

    /**
     * Initialise les périodes attendues & réelles.
     * @param expected la période attendue
     * @param actual la période réelle
     */
    public TimeReport(DaySpan expected, DaySpan actual){
        this.expected = expected;
        this.actual = actual;
    }

    /**
     * Retourne la date de début réelle.
     * @return la date de début réelle
     */
    public LocalDate getActualStart(){
        return actual.getStart();
    }

    /**
     * Retourne la date de fin réelle.
     * @return la date de fin réelle
     */
    public LocalDate getActualEnd(){
        return actual.getEnd();
    }

    /**
     * Retourne la date de début attendue.
     * @return la date de début attendue
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
     * Retourne le statut de la tâche.
     * @return le statut de la tâche
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
     * Retourne vrai si la tâche est dans les temps, sinon faux.
     * @return vrai si la tâche est dans les temps, sinon faux
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
