package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.algo;

import java.util.HashSet;
import java.util.Set;

public class PertEdge {
    private final PertTask task;
    private final int from;
    private final int to;

    public PertEdge(int from, int to, PertTask task){
        this.task = task;
        this.from = from;
        this.to = to;
    }

}
