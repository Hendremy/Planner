package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.algo;

import java.util.*;

public class PertGraphBuilder {

    private final PertLevelCalculator calculator;

    public PertGraphBuilder(PertLevelCalculator calculator){
        this.calculator = calculator;
    }

    public PertGraph buildGraph(Iterable<PertTask> tasks){
        List<PertTask> taskList = tasksToList(tasks);
        Set<PertNode> nodes = new HashSet<>();
        Set<PertEdge> edges = new HashSet<>(taskList.size());

        buildEdges(taskList, edges);
        buildNodes(edges);
        return new PertGraph(edges, nodes);
    }

    private List<PertTask> tasksToList(Iterable<PertTask> tasks){
        List<PertTask> taskList = new ArrayList<>();
        tasks.forEach(taskList::add);
        return taskList;
    }

    private void buildEdges(List<PertTask> taskList, Set<PertEdge> edges){
        taskList.forEach(task -> edges.add(new PertEdge(task)));
        calculator.calcLevels(taskList,edges);
    }

    private void buildNodes(Set<PertEdge> edges){

    }
}
