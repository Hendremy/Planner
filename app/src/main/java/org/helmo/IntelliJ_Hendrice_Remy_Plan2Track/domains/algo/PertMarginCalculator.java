package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.Collection;

/**
 * D�finit le calculateur de marges d'une ar�te PERT
 */
public class PertMarginCalculator {

    /**
     * Calcule les marges des ar�tes d'un graphe PERT
     * @param graph le graphe PERT
     */
    public void calcTaskMargins(PertGraph graph){
        Collection<PertEdge> edges = graph.getEdges();
        for(PertEdge edge : edges){
            PertNode origin = graph.getNode(edge.getOrigin());
            PertNode target = graph.getNode(edge.getTarget());
            edge.setFreeMargin(calcFreeMargin(origin, target, edge));
            edge.setTotalMargin(calcTotalMargin(origin,target, edge));
        }
    }

    /**
     * Calcule la marge libre d'une ar�te.
     * @param origin l'�tape d'origine de l'ar�te
     * @param target l'�tape de destination de l'ar�te
     * @param edge l'ar�te
     * @return la marge libre de l'ar�te
     */
    private int calcFreeMargin(PertNode origin, PertNode target, PertEdge edge){
        return target.getEarliestTime() - origin.getEarliestTime() - edge.getDuration();
    }

    /**
     * Calcule la marge totale d'une ar�te.
     * @param origin l'�tape d'origine de l'ar�te
     * @param target l'�tape de destination de l'ar�te
     * @param edge l'ar�te
     * @return la marge totale de l'ar�te
     */
    private int calcTotalMargin(PertNode origin, PertNode target, PertEdge edge){
        return target.getLatestTime() - origin.getEarliestTime() - edge.getDuration();
    }
}
