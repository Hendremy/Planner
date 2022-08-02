package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.algo;

import java.util.HashSet;
import java.util.Set;

public class PertEdge {
    private final PertTask edgeType;
    private int level;
    private final Set<PertEdge> priorEdges;

    public PertEdge(PertTask edgeType){
        this.edgeType = edgeType;
        this.level = -1;
        priorEdges = new HashSet<>();
    }

    public void setLevel(int level){
        this.level = level;
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
