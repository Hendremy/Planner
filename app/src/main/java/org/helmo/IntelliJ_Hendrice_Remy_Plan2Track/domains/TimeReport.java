package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import java.time.LocalDate;

public class TimeReport {
    private final DaySpan expected;
    private final DaySpan actual;

    public TimeReport(DaySpan expected, DaySpan actual){
        this.expected = expected;
        this.actual = actual;
    }

    public LocalDate getActualStart(){
        return actual.getStart();
    }

    public LocalDate getActualEnd(){
        return actual.getEnd();
    }

    public LocalDate getExpectedStart(){
        return expected.getStart();
    }

    public LocalDate getExpectedEnd(){
        return expected.getEnd();
    }

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
