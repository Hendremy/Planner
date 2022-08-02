package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.algo;

import java.util.Set;

public class PertNode<T>{
    private int order;
    private Set<PertEdge<T>> incomingEdges;
    private PertEdge<T> outGoingEdges;
}
