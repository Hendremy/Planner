package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.algo;

import java.util.*;

public class PertGraphBuilder {

    private final PertLevelCalculator calculator;

    public PertGraphBuilder(){
        this.calculator = new PertLevelCalculator();
    }

    public PertGraph getGraph(Iterable<PertTask> tasks){
        var levels = calcLevels(tasks);
        return new PertGraph(null, null);
    }

    private List<Set<PertTask>> calcLevels(Iterable<PertTask> tasks){
        return calculator.calcLevels(tasks);
    }


    private void buildGraph(List<Set<PertTask>> tasks){

    }

    private void buildEdges(Set<PertEdge> edges){

    }

    private void buildNodes(Set<PertEdge> edges){

    }
}
