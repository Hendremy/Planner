package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.Map;
import java.util.Set;

/**
 * Définit le calculateur de date au plus tôt et au plus tard d'une étape PERT.
 */
public class PertTimeCalculator {

    /**
     * Calcule les dates au plus tôt et au plus tard des étapes d'un graphe PERT.
     * @param graph le graphe PERT
     */
    public void calcEarliestLatestTimes(PertGraph graph){
        calcStepsEarliestTime(graph);
        calcStepsLatestTime(graph);
    }

    /**
     * Calcule les dates de fin au plus tôt des étapes d'un graphe PERT.
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
     * Initialise la date de fin au plus tôt de la première étape du graphe à 0.
     * @param firstNode la première étape du graphe
     */
    private void initFirstNode(PertNode firstNode){
        firstNode.setEarliestTime(0);
    }

    /**
     * Trouve la plus grande somme "date au plus tôt + durée de l'arête" des prédécesseurs de l'étape.
     * @param nodes les étapes du graphes
     * @param node l'étape dont il faut trouver la date au plus tôt
     * @return la plus grande somme "date au plus tôt + durée de l'arête" des prédécesseurs de l'étape
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
     * Trouve la position de l'étape de fin du graphe.
     * @param stepNumbers les positions des étapes du graphe
     * @return la position de l'étape de fin du graphe
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
     * Initialise la date de fin au plus tard de la dernière étape.
     * @param finalNode la dernière étape
     */
    private void initFinalNode(PertNode finalNode){
        finalNode.setLatestTime(finalNode.getEarliestTime());
    }

    /**
     * Trouve la plus petite différence "date au plus tard - durée de l'arête" des successeur de l'étape.
     * @param nodes les étapes du graphe
     * @param node l'étape dont il faut trouver la date au plus tard
     * @return la plus petite différence "date au plus tard - durée de l'arête" des successeur de l'étape
     */
    private int findMinFollowingDuration(Map<Integer,PertNode> nodes, PertNode node){
        int minDuration = Integer.MAX_VALUE;
        for(PertEdge outgoing : node.getOutgoingEdges()){
            PertNode followingNode = nodes.get(outgoing.getTarget());
            if(outgoing.getDuration() == 0) ensureFollowingIsCalculated(nodes, followingNode);//Tache fictive peut relier j->i avec i < j donc il faut assurer que i soit calculé
            int totalDuration = followingNode.getLatestTime() - outgoing.getDuration();
            minDuration = Math.min(minDuration, totalDuration);
        }
        return minDuration;
    }

    /**
     * Assure que l'étape successeur a ses dates au plus tôt et au plus tard déjà calculées.
     * @param nodes les étapes du graphe
     * @param node l'étape à assurer
     */
    private void ensureFollowingIsCalculated(Map<Integer, PertNode> nodes, PertNode node){
        int duration = findMinFollowingDuration(nodes, node);
        node.setLatestTime(duration);
    }
}
