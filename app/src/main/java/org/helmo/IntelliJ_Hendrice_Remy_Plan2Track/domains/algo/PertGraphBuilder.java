package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.*;

public class PertGraphBuilder {

    private final PertLevelCalculator calculator;

    public PertGraphBuilder(){
        this.calculator = new PertLevelCalculator();
    }

    public PertGraph getGraph(Iterable<PertTask> tasks){
        var levels = calcLevels(tasks);
        return new PertGraph();
    }

    private List<Set<PertTask>> calcLevels(Iterable<PertTask> tasks){
        return calculator.calcLevels(tasks);
    }

    private void buildGraph(List<Set<PertTask>> tasks){
        int stepNum = 1;
        PertNode first = new PertNode(stepNum);
        stepNum++;
        for(var level : tasks){
            for(var task : level){

            }
        }
    }


    private void buildEdges(Set<PertEdge> edges){

    }

    private void buildNodes(Set<PertEdge> edges){

    }
}
