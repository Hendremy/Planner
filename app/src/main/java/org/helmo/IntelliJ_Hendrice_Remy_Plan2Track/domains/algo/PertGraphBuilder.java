package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.*;

/**
 * Définit la classe générant le graphe PERT.
 */
public class PertGraphBuilder {

    private final PertLevelCalculator calculator;
    private Set<PertTask> pruningCandidates;

    /**
     * Initialise le calculateur de niveaux des tâches PERT
     */
    public PertGraphBuilder(){
        this.calculator = new PertLevelCalculator();
    }

    /**
     * Génère le graphe PERT à partir du réseau de tâches fourni en paramètres.
     * @param tasks le réseau de tâches
     * @return le graphe PERT correspondant à ce réseau de tâches
     * @throws CyclicGraphException survient lorsqu'un cycle est détecté dans le réseau de tâches
     */
    public PertGraph getGraph(Iterable<PertTask> tasks) throws CyclicGraphException {
        List<Set<PertTask>> levels = calcLevels(tasks);
        PertGraph graph = buildGraph(levels);
        pruneFakeTasks(graph);
        return graph;
    }

    /**
     * Regroupe les tâches du réseau par niveaux d'antériorité.
     * @param tasks le réseau de tâches
     * @return les tâches regroupées en niveaux d'antériorité
     */
    private List<Set<PertTask>> calcLevels(Iterable<PertTask> tasks){
        return calculator.calcLevels(tasks);
    }

    /**
     * Construit le graphe PERT à partir des tâches regroupées par niveaux d'antériorité.
     * @param tasks les tâches regroupées par niveaux d'antériorité
     * @return le graphe PERT du réseau de tâches
     * @throws CyclicGraphException survient lorsqu'un cycle est détecté dans le réseau de tâches
     */
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

    /**
     * Ajoute une tâche au graphe PERT en la rattachant à son prédécesseur de plus haut niveau déjà présent dans le graphe
     * et en rattachant ses autres prédécesseurs par des arêtes fictives
     * @param tasks les tâches par niveaux d'antériorité
     * @param task la tâche à ajouter au graphe
     * @param pertGraph le graphe en construction
     * @param nodePosition le numéro de la prochaine étape
     * @throws CyclicGraphException
     */
    private void addHighestRankedPredecessor(List<Set<PertTask>> tasks, PertTask task, PertGraph pertGraph, int nodePosition) throws CyclicGraphException {
        PertTask highestPredecessor = findHighestRankedPredecessor(tasks, task);
        PertNode originStep = pertGraph.linkTaskToPredecessor(nodePosition, highestPredecessor, task);
        Set<PertTask> remainingPredecessors = getRemainingPredecessors(task.getPredecessors(), highestPredecessor);
        for(PertTask remainingP : remainingPredecessors){
            PertTask fakeTask = pertGraph.addFakeEdge(originStep, remainingP);
            pruningCandidates.add(fakeTask);
        }
    }

    /**
     * Trouve le prédécesseur de plus haut niveau de la tâche.
     * @param taskByLevel les tâches regroupées par niveaux d'antériorité
     * @param task la tâche dont il faut trouver le prédécesseur
     * @return le prédécesseur de plus haut niveau de la tâche ou null si aucun prédécesseur n'est trouvé
     */
    private PertTask findHighestRankedPredecessor(List<Set<PertTask>> taskByLevel, PertTask task){
        for(int i = taskByLevel.size() - 1 ; i >= 0 ; i--){
            Set<PertTask> level = taskByLevel.get(i);
            for (PertTask priorTask : task.getPredecessors()){
                if(level.contains(priorTask)) return priorTask;
            }
        }
        return null;
    }

    /**
     * Retourne les prédécesseurs d'une tâche sans celle de plus haut niveau.
     * @param priorTasks les prédécesseur de la tâche
     * @param withoutTask la tâche de plus haut niveau à enlever
     * @return les prédécesseurs d'une tâche sans celle de plus haut niveau
     */
    private Set<PertTask> getRemainingPredecessors(Set<PertTask> priorTasks, PertTask withoutTask){
        Set<PertTask> remaining = new HashSet<>(priorTasks);
        remaining.remove(withoutTask);
        return remaining;
    }

    /**
     * Elague les tâches fictives pouvant être supprimées sans corrompre le graphe.
     * @param graph le graphe à élaguer
     */
    private void pruneFakeTasks(PertGraph graph){
        for(PertTask fakeTask : pruningCandidates){
            graph.pruneFakeTask(fakeTask);
        }
    }
}
