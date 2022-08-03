package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.algo;

import java.util.HashSet;
import java.util.Set;

public class PertEdge {
    private final PertTask task;
    private final Set<PertEdge> priorEdges;

    public PertEdge(PertTask task){
        this.task = task;
        priorEdges = new HashSet<>();
    }

    public void addPriorEdge(PertEdge edge){
        if(edge != null && this != edge){
            priorEdges.add(edge);
        }
    }

    public Set<PertEdge> getPredecessors(){
        return this.priorEdges;
    }
}
