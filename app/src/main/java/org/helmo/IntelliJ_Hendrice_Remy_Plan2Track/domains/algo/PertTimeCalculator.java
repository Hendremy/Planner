package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.Map;
import java.util.Set;

public class PertTimeCalculator {

    public void calcEarliestLatestTimes(PertGraph graph){
        calcStepsEarliestTime(graph);
        calcStepsLatestTime(graph);
    }

    private void calcStepsEarliestTime(PertGraph graph){
        Map<Integer, PertNode> nodes = graph.getNodes();
        int highestStep = findHighestStep(nodes.keySet());
        initFirstNode(nodes.get(1));

        for(int i = 2; i <= highestStep; ++i){
            PertNode node = nodes.get(i);
            if(node != null){
                int duration = findMaxPreviousDuration(nodes, node);
                node.setEarliestTime(duration);
            }
        }
    }

    private void initFirstNode(PertNode firstNode){
        firstNode.setEarliestTime(0);
    }

    private int findMaxPreviousDuration(Map<Integer, PertNode> nodes, PertNode node){
        int maxDuration = 0;
        for(PertEdge incoming : node.getIncomingEdges()){
            PertNode previousNode = nodes.get(incoming.getOrigin());
            int totalDuration = previousNode.getEarliestTime() + incoming.getDuration();
            maxDuration = Math.max(maxDuration, totalDuration);
        }
        return maxDuration;
    }

    private int findHighestStep(Set<Integer> stepNumbers){
        int max = 1;
        for(int position : stepNumbers){
            max = Math.max(max, position);
        }
        return max;
    }

    private void calcStepsLatestTime(PertGraph graph){
        Map<Integer, PertNode> nodes = graph.getNodes();
        int highestStep = findHighestStep(nodes.keySet());
        initFinalNode(nodes.get(highestStep));

        for(int i = highestStep; i >= 1; --i){
            PertNode node = nodes.get(i);
            if(node != null){
                int duration = findMinFollowingDuration(nodes, node);
                node.setLatestTime(duration);
            }
        }
    }

    private void initFinalNode(PertNode finalNode){
        finalNode.setLatestTime(finalNode.getEarliestTime());
    }

    private int findMinFollowingDuration(Map<Integer,PertNode> nodes, PertNode node){
        int minDuration = 0;
        for(PertEdge outgoing : node.getOutgoingEdges()){
            PertNode followingNode = nodes.get(outgoing.getTarget());
            int totalDuration = followingNode.getLatestTime() - outgoing.getDuration();
            minDuration = Math.min(minDuration, totalDuration);
        }
        return minDuration;
    }
}
