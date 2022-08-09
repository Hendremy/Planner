package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class PertTimeCalculator {

    public void calcStepsEarliestTime(PertGraph graph){
        Map<Integer, PertNode> nodes = graph.getNodes();
        int highestStep = findHighestStep(nodes.keySet());

        PertNode node = nodes.get(1);
        node.setEarliestTime(0);
        for(int i = 2; i <= highestStep; ++i){
            node = nodes.get(i);
            if(node != null){

            }
        }
    }

    private int findHighestStep(Set<Integer> stepNumbers){
        int max = 1;
        for(int position : stepNumbers){
            if(position > max) max = position;
        }
        return max;
    }
}
