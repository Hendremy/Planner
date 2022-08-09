package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.Collection;

public class PertMarginCalculator {

    public void calcTaskMargins(PertGraph graph){
        Collection<PertEdge> edges = graph.getEdges();
        for(PertEdge edge : edges){
            PertNode origin = graph.getNode(edge.getOrigin());
            PertNode target = graph.getNode(edge.getTarget());
            edge.setFreeMargin(calcFreeMargin(origin, target, edge));
            edge.setTotalMargin(calcTotalMargin(origin,target, edge));
        }
    }

    private int calcFreeMargin(PertNode origin, PertNode target, PertEdge edge){
        return target.getEarliestTime() - origin.getEarliestTime() - edge.getDuration();
    }

    private int calcTotalMargin(PertNode origin, PertNode target, PertEdge edge){
        return target.getLatestTime() - origin.getEarliestTime() - edge.getDuration();
    }
}
