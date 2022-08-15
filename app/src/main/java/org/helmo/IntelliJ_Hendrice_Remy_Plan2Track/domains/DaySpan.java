package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import java.time.LocalDate;

public class DaySpan {
    private final LocalDate start;
    private final LocalDate end;

    public DaySpan(LocalDate start, LocalDate end){
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart(){
        return start;
    }

    public LocalDate getEnd(){
        return end;
    }
}
