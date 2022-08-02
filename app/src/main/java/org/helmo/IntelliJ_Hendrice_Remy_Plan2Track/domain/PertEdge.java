package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain;

import java.util.HashSet;
import java.util.Set;

public class PertEdge {
    private Job job;
    private int weight;
    private int level;
    private Set<PertEdge> priorEdges;

    public PertEdge(Job job){
        this.job = job;
        weight = job.getDuration();
        priorEdges = new HashSet<>();
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void addPriorEdge(PertEdge edge){
        priorEdges.add(edge);
    }
}
