package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.*;

public class PertGraphBuilder {

    private final PertLevelCalculator calculator;
    private Set<PertTask> pruningCandidates;

    public PertGraphBuilder(){
        this.calculator = new PertLevelCalculator();
    }

    public PertGraph getGraph(Iterable<PertTask> tasks) throws CyclicGraphException {
        List<Set<PertTask>> levels = calcLevels(tasks);
        PertGraph graph = buildGraph(levels);
        pruneFakeTasks(graph);
        return graph;
    }

    private List<Set<PertTask>> calcLevels(Iterable<PertTask> tasks){
        return calculator.calcLevels(tasks);
    }

    private PertGraph buildGraph(List<Set<PertTask>> tasks) throws CyclicGraphException {
        PertGraph pertGraph = new PertGraph();
        int nodePosition = 1;
        pruningCandidates = new HashSet<>();

        for(Set<PertTask> level : tasks){
            for(PertTask task : level){
                if(!task.hasPredecessors()){
                    pertGraph.addEdge(1, ++nodePosition, task);
                }else{
                    addHighestRankedPredecessor(tasks, task, pertGraph, ++nodePosition);
                }
            }
        }
        return pertGraph;
    }

    private void addHighestRankedPredecessor(List<Set<PertTask>> tasks, PertTask task, PertGraph pertGraph, int nodePosition) throws CyclicGraphException {
        PertTask highestPredecessor = findHighestRankedPredecessor(tasks, task);
        PertNode originStep = pertGraph.addNode(nodePosition, highestPredecessor, task);
        Set<PertTask> remainingPredecessors = getRemainingPredecessors(task.getPredecessors(), highestPredecessor);
        for(PertTask remainingP : remainingPredecessors){
            PertTask fakeTask = pertGraph.addFake(originStep, remainingP);
            pruningCandidates.add(fakeTask);
        }
    }

    private PertTask findHighestRankedPredecessor(List<Set<PertTask>> taskByLevel, PertTask task){
        for(int i = taskByLevel.size() - 1 ; i >= 0 ; i--){
            Set<PertTask> level = taskByLevel.get(i);
            for (PertTask priorTask : task.getPredecessors()){
                if(level.contains(priorTask)) return priorTask;
            }
        }
        return null;
    }

    private Set<PertTask> getRemainingPredecessors(Set<PertTask> priorTasks, PertTask withoutTask){
        Set<PertTask> remaining = new HashSet<>(priorTasks);
        remaining.remove(withoutTask);
        return remaining;
    }

    private void pruneFakeTasks(PertGraph graph){
        for(PertTask fakeTask : pruningCandidates){
            graph.pruneFakeTask(fakeTask);
        }
    }
}
