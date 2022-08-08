package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PertNode{
    private int position;
    private final Set<PertEdge> outgoingEdges;
    private final Set<PertEdge> incomingEdges;

    public PertNode(int position){
        this.position = position;
        outgoingEdges = new HashSet<>();
        incomingEdges = new HashSet<>();
    }

    public void addOutGoingEdge(PertEdge edge){
        outgoingEdges.add(edge);
    }

    public void addIncomingEdge(PertEdge edge){
        incomingEdges.add(edge);
    }

    public int getPosition(){
        return position;
    }

    public Collection<PertEdge> getIncomingEdges(){
        return new HashSet<>(incomingEdges);
    }

    public boolean hasOutgoinEdge(int target){
        for(PertEdge edge : outgoingEdges){
            if(edge.getTarget() == target) return true;
        }
        return false;
    }

    public boolean hasOneOutgoingEdge(){
        return outgoingEdges.size() == 1;
    }

    public void removeOutgoingEdge(PertEdge edge){
        outgoingEdges.remove(edge);
    }

    public void removeIncomingEdge(PertEdge edge){
        incomingEdges.remove(edge);
    }

    public PertEdge getOutGoingEdgeWithTarget(int target){
        for(PertEdge edge : outgoingEdges){
            if(edge.getTarget() == target) return edge;
        }
        return null;
    }

    public boolean isEmpty(){
        return incomingEdges.isEmpty() && outgoingEdges.isEmpty();
    }

}
