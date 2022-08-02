package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.algo;

import java.util.HashSet;
import java.util.Set;

public class PertGraph {
    private final Set<PertEdge> edges;
    private final Set<PertNode> nodes;

    public PertGraph(Set<PertEdge> edges, Set<PertNode> nodes){
        this.edges = edges;
        this.nodes = nodes;
    }
}
