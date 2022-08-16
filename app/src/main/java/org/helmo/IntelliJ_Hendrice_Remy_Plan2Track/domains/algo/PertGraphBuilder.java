package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.*;

/**
 * D�finit la classe g�n�rant le graphe PERT.
 */
public class PertGraphBuilder {

    private final PertLevelCalculator calculator;
    private Set<PertTask> pruningCandidates;

    /**
     * Initialise le calculateur de niveaux des t�ches PERT
     */
    public PertGraphBuilder(){
        this.calculator = new PertLevelCalculator();
    }

    /**
     * G�n�re le graphe PERT � partir du r�seau de t�ches fourni en param�tres.
     * @param tasks le r�seau de t�ches
     * @return le graphe PERT correspondant � ce r�seau de t�ches
     * @throws CyclicGraphException survient lorsqu'un cycle est d�tect� dans le r�seau de t�ches
     */
    public PertGraph getGraph(Iterable<PertTask> tasks) throws CyclicGraphException {
        List<Set<PertTask>> levels = calcLevels(tasks);
        PertGraph graph = buildGraph(levels);
        pruneFakeTasks(graph);
        return graph;
    }

    /**
     * Regroupe les t�ches du r�seau par niveaux d'ant�riorit�.
     * @param tasks le r�seau de t�ches
     * @return les t�ches regroup�es en niveaux d'ant�riorit�
     */
    private List<Set<PertTask>> calcLevels(Iterable<PertTask> tasks){
        return calculator.calcLevels(tasks);
    }

    /**
     * Construit le graphe PERT � partir des t�ches regroup�es par niveaux d'ant�riorit�.
     * @param tasks les t�ches regroup�es par niveaux d'ant�riorit�
     * @return le graphe PERT du r�seau de t�ches
     * @throws CyclicGraphException survient lorsqu'un cycle est d�tect� dans le r�seau de t�ches
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
     * Ajoute une t�che au graphe PERT en la rattachant � son pr�d�cesseur de plus haut niveau d�j� pr�sent dans le graphe
     * et en rattachant ses autres pr�d�cesseurs par des ar�tes fictives
     * @param tasks les t�ches par niveaux d'ant�riorit�
     * @param task la t�che � ajouter au graphe
     * @param pertGraph le graphe en construction
     * @param nodePosition le num�ro de la prochaine �tape
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
     * Trouve le pr�d�cesseur de plus haut niveau de la t�che.
     * @param taskByLevel les t�ches regroup�es par niveaux d'ant�riorit�
     * @param task la t�che dont il faut trouver le pr�d�cesseur
     * @return le pr�d�cesseur de plus haut niveau de la t�che ou null si aucun pr�d�cesseur n'est trouv�
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
     * Retourne les pr�d�cesseurs d'une t�che sans celle de plus haut niveau.
     * @param priorTasks les pr�d�cesseur de la t�che
     * @param withoutTask la t�che de plus haut niveau � enlever
     * @return les pr�d�cesseurs d'une t�che sans celle de plus haut niveau
     */
    private Set<PertTask> getRemainingPredecessors(Set<PertTask> priorTasks, PertTask withoutTask){
        Set<PertTask> remaining = new HashSet<>(priorTasks);
        remaining.remove(withoutTask);
        return remaining;
    }

    /**
     * Elague les t�ches fictives pouvant �tre supprim�es sans corrompre le graphe.
     * @param graph le graphe � �laguer
     */
    private void pruneFakeTasks(PertGraph graph){
        for(PertTask fakeTask : pruningCandidates){
            graph.pruneFakeTask(fakeTask);
        }
    }
}
