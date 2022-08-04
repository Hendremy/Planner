package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.HashSet;
import java.util.Set;

public class PertNode{
    private int order;
    private final Set<PertEdge> outgoingEdges;
    private final Set<PertEdge> incomingEdges;

    public PertNode(int order){
        this.order = order;
        outgoingEdges = new HashSet<>();
        incomingEdges = new HashSet<>();
    }

    public void addOutGoingEdge(PertEdge edge){
        outgoingEdges.add(edge);
    }

    public void addIncomingEdge(PertEdge edge){
        incomingEdges.add(edge);
    }

}
