package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.algo;

import java.util.HashSet;
import java.util.Set;

public class PertEdge<T> {
    private final PertCandidate<T> edgeType;
    private int level;
    private final Set<PertEdge<T>> priorEdges;

    public PertEdge(PertCandidate<T> edgeType){
        this.edgeType = edgeType;
        this.level = -1;
        priorEdges = new HashSet<>();
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void addPriorEdge(PertEdge<T> edge){
        if(edge != null && this != edge){
            priorEdges.add(edge);
        }
    }

    public Set<PertEdge<T>> getPredecessors(){
        return this.priorEdges;
    }
}
