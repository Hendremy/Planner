package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.*;

public class PertSchedulePlanner {

    private final PertGraphBuilder builder;
    private final PertTimeCalculator timeCalculator;
    private final PertMarginCalculator marginCalculator;

    public PertSchedulePlanner(PertGraphBuilder builder, PertTimeCalculator timeCalculator, PertMarginCalculator marginCalculator){
        this.builder = builder;
        this.timeCalculator = timeCalculator;
        this.marginCalculator = marginCalculator;
    }

    public PertGraph planSchedule(PertNetwork planning) throws PertException {
        PertGraph graph = builder.getGraph(planning.getTasks());
        timeCalculator.calcEarliestLatestTimes(graph);
        marginCalculator.calcTaskMargins(graph);
        return graph;
    }

    public List<PertTask> findCriticalPath(PertGraph graph) throws PertException {
        List<PertTask> criticalPath = new LinkedList<>();
        PertNode node = graph.getNode(1);
        while(node.hasOutgoingEdges()){
            PertEdge criticalEdge = findCriticalOutgoingEdge(node);
            if(criticalEdge != null){
                criticalPath.add(criticalEdge.getTask());
                node = graph.getNode(criticalEdge.getTarget());
            }else{
                throw new AlgorithmException("Error while calculating critical path");
            }
        }
        return criticalPath;
    }

    private PertEdge findCriticalOutgoingEdge(PertNode node){
        for (PertEdge edge : node.getOutgoingEdges()) {
            if(edge.getTotalMargin() == 0) return edge;
        }
        return null;
    }

    public int findEarliestEndDate(PertGraph graph){
        Set<Integer> nodePositions = new HashSet<>(graph.getNodes().keySet());
        int max = Collections.max(nodePositions);
        return graph.getNode(max).getLatestTime();
    }
}
