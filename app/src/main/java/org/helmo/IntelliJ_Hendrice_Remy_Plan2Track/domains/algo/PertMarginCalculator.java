package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.Collection;

/**
 * Définit le calculateur de marges d'une arête PERT
 */
public class PertMarginCalculator {

    /**
     * Calcule les marges des arêtes d'un graphe PERT
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
     * Calcule la marge libre d'une arête.
     * @param origin l'étape d'origine de l'arête
     * @param target l'étape de destination de l'arête
     * @param edge l'arête
     * @return la marge libre de l'arête
     */
    private int calcFreeMargin(PertNode origin, PertNode target, PertEdge edge){
        return target.getEarliestTime() - origin.getEarliestTime() - edge.getDuration();
    }

    /**
     * Calcule la marge totale d'une arête.
     * @param origin l'étape d'origine de l'arête
     * @param target l'étape de destination de l'arête
     * @param edge l'arête
     * @return la marge totale de l'arête
     */
    private int calcTotalMargin(PertNode origin, PertNode target, PertEdge edge){
        return target.getLatestTime() - origin.getEarliestTime() - edge.getDuration();
    }
}
