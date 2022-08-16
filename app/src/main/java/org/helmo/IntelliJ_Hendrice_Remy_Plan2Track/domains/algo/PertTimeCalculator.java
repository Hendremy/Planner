package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.Map;
import java.util.Set;

/**
 * D�finit le calculateur de date au plus t�t et au plus tard d'une �tape PERT.
 */
public class PertTimeCalculator {

    /**
     * Calcule les dates au plus t�t et au plus tard des �tapes d'un graphe PERT.
     * @param graph le graphe PERT
     */
    public void calcEarliestLatestTimes(PertGraph graph){
        calcStepsEarliestTime(graph);
        calcStepsLatestTime(graph);
    }

    /**
     * Calcule les dates de fin au plus t�t des �tapes d'un graphe PERT.
     * @param graph le graphe PERT
     */
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

    /**
     * Initialise la date de fin au plus t�t de la premi�re �tape du graphe � 0.
     * @param firstNode la premi�re �tape du graphe
     */
    private void initFirstNode(PertNode firstNode){
        firstNode.setEarliestTime(0);
    }

    /**
     * Trouve la plus grande somme "date au plus t�t + dur�e de l'ar�te" des pr�d�cesseurs de l'�tape.
     * @param nodes les �tapes du graphes
     * @param node l'�tape dont il faut trouver la date au plus t�t
     * @return la plus grande somme "date au plus t�t + dur�e de l'ar�te" des pr�d�cesseurs de l'�tape
     */
    private int findMaxPreviousDuration(Map<Integer, PertNode> nodes, PertNode node){
        int maxDuration = 0;
        for(PertEdge incoming : node.getIncomingEdges()){
            PertNode previousNode = nodes.get(incoming.getOrigin());
            int totalDuration = previousNode.getEarliestTime() + incoming.getDuration();
            maxDuration = Math.max(maxDuration, totalDuration);
        }
        return maxDuration;
    }

    /**
     * Trouve la position de l'�tape de fin du graphe.
     * @param stepNumbers les positions des �tapes du graphe
     * @return la position de l'�tape de fin du graphe
     */
    private int findHighestStep(Set<Integer> stepNumbers){
        int max = 1;
        for(int position : stepNumbers){
            max = Math.max(max, position);
        }
        return max;
    }

    /**
     * Calcule les dates de fin au plus tard d'un graphe PERT.
     * @param graph le graphe PERT
     */
    private void calcStepsLatestTime(PertGraph graph){
        Map<Integer, PertNode> nodes = graph.getNodes();
        int highestStep = findHighestStep(nodes.keySet());
        initFinalNode(nodes.get(highestStep));

        for(int i = highestStep - 1; i >= 1; --i){
            PertNode node = nodes.get(i);
            if(node != null){
                int duration = findMinFollowingDuration(nodes, node);
                node.setLatestTime(duration);
            }
        }
    }

    /**
     * Initialise la date de fin au plus tard de la derni�re �tape.
     * @param finalNode la derni�re �tape
     */
    private void initFinalNode(PertNode finalNode){
        finalNode.setLatestTime(finalNode.getEarliestTime());
    }

    /**
     * Trouve la plus petite diff�rence "date au plus tard - dur�e de l'ar�te" des successeur de l'�tape.
     * @param nodes les �tapes du graphe
     * @param node l'�tape dont il faut trouver la date au plus tard
     * @return la plus petite diff�rence "date au plus tard - dur�e de l'ar�te" des successeur de l'�tape
     */
    private int findMinFollowingDuration(Map<Integer,PertNode> nodes, PertNode node){
        int minDuration = Integer.MAX_VALUE;
        for(PertEdge outgoing : node.getOutgoingEdges()){
            PertNode followingNode = nodes.get(outgoing.getTarget());
            if(outgoing.getDuration() == 0) ensureFollowingIsCalculated(nodes, followingNode);//Tache fictive peut relier j->i avec i < j donc il faut assurer que i soit calcul�
            int totalDuration = followingNode.getLatestTime() - outgoing.getDuration();
            minDuration = Math.min(minDuration, totalDuration);
        }
        return minDuration;
    }

    /**
     * Assure que l'�tape successeur a ses dates au plus t�t et au plus tard d�j� calcul�es.
     * @param nodes les �tapes du graphe
     * @param node l'�tape � assurer
     */
    private void ensureFollowingIsCalculated(Map<Integer, PertNode> nodes, PertNode node){
        int duration = findMinFollowingDuration(nodes, node);
        node.setLatestTime(duration);
    }
}
