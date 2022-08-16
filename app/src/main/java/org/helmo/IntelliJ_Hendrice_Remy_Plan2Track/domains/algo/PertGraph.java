package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.*;

/**
 * Définit un graphe PERT.
 */
public class PertGraph {
    private final Map<Integer, PertNode> nodes;
    private final Map<PertTask, PertEdge> edges;

    /*
     * Choix de la collection pour les étapes du graphe: Map
     *      - Je veux pouvoir accéder aux étapes d'un graphe sur base de leur numéro
     *          => List pourrait convenir mais l'élaguage des arêtes fictives pourrait supprimer des étapes
     *              et donc les numéros des étapes seraient chamboulés
     *          => Set ne convient pas
     *
     * => Implémentation : HashMap
     *      - Je n'ai pas besoin d'avoir mes éléments triés, j'accède directement à une étape
     *        grâce à son numéro, donc TreeMap pas utile
     *      - Accès, ajout et suppression d'un élément en O(1)
     *
     */

    /**
     * Initialise sa collection d'arêtes et d'étapes et initialise l'étape 1.
     */
    public PertGraph(){
        nodes = new HashMap<>();
        linkTaskToPredecessor(new PertNode(1));
        edges = new HashMap<>();
    }

    /**
     * Retourne les étapes du graphes.
     * @return les étapes du graphe
     */
    public Map<Integer, PertNode> getNodes(){
        return new HashMap<>(nodes);
    }

    /**
     * Retourne les arêtes du graphes.
     * @return les arêtes du graphe
     */
    public Set<PertEdge> getEdges(){return new HashSet<>(edges.values());}

    /**
     * Retourne l'étape correspondant à la position en paramètre ou null si elle n'existe pas
     * @param position le numéro de l'étape
     * @return l'étape correspondant à la position en paramètre ou null si elle n'existe pas
     */
    public PertNode getNode(int position){return nodes.get(position);}

    /**
     * Ajoute une étape au graphe.
     * @param node l'étape à ajouter
     */
    public void linkTaskToPredecessor(PertNode node) {
        nodes.put(node.getPosition(), node);
    }

    /**
     * Ajoute une arête entre deux étapes.
     * @param origin le numéro de l'étape d'origine
     * @param destination le numéro de l'étape de destination
     * @param task la tâche associée
     * @throws CyclicGraphException survient lorsqu'un cycle est détecté dans le réseau de tâches
     */
    public void addEdge(int origin, int destination, PertTask task) throws CyclicGraphException {
        PertNode originNode = getOrCreateNode(origin);
        PertNode targetNode = getOrCreateNode(destination);
        PertEdge edge = new PertEdge(origin, destination, task);
        edges.put(task, edge);
        originNode.addOutGoingEdge(edge);
        targetNode.addIncomingEdge(edge);
        insertEdge(originNode, targetNode, edge);
    }

    /**
     * Relie une tâche à son prédécesseur en créant une nouvelle étape et en ajoutant une arête
     * représentant cette tâche partant de l'étape de destination de son prédécesseur et arrivant à la nouvelle étape créée
     * puis retourne cette nouvelle étape
     * @param pos le numéro de la nouvelle étape
     * @param predecessor le prédécesseur de la tâche
     * @param task la tâche à relier à son prédécesseur
     * @return la nouvelle étape crée
     * @throws CyclicGraphException survient lorsqu'un cycle est détecté dans le réseau de tâches
     */
    public PertNode linkTaskToPredecessor(int pos, PertTask predecessor, PertTask task) throws CyclicGraphException {
        PertEdge priorEdge = getExistingEdge(predecessor);
        PertNode originNode = getOrCreateNode(priorEdge.getTarget());
        PertNode targetNode = getOrCreateNode(pos);
        PertEdge newEdge = new PertEdge(originNode.getPosition(), targetNode.getPosition(), task);
        edges.put(task, newEdge);
        insertEdge(originNode, targetNode, newEdge);
        return originNode;
    }

    /**
     * Ajoute une arête fictive depuis l'étape d'origine de la tâche originelle jusqu'à une étape cible.
     * @param targetNode l'étape cible
     * @param task la tâche originelle
     * @return l'arête fictive créée
     * @throws CyclicGraphException survient lorsqu'un cycle est détecté dans le réseau de tâches
     */
    public PertTask addFakeEdge(PertNode targetNode, PertTask task) throws CyclicGraphException {
        PertTask fakeTask = new FakeTask(task);
        PertEdge originalEdge = edges.get(task);
        PertNode originNode = nodes.get(originalEdge.getTarget());
        PertEdge fakeEdge = new PertEdge(originNode.getPosition(), targetNode.getPosition(), fakeTask);
        edges.put(fakeTask, fakeEdge);

        insertEdge(originNode, targetNode, fakeEdge);
        return fakeTask;
    }

    /**
     * Insère une arête entre deux étapes.
     * @param originNode l'étape d'origine
     * @param targetNode l'étape de destination
     * @param edge l'arête à insérer
     */
    private void insertEdge(PertNode originNode, PertNode targetNode, PertEdge edge){
        originNode.addOutGoingEdge(edge);
        targetNode.addIncomingEdge(edge);
    }

    /**
     * Retourne l'étape correspondant à la position en argument et la crée au besoin.
     * @param position le numéro de l'étape
     * @return l'étape correspondante à la position
     */
    private PertNode getOrCreateNode(int position){
        PertNode node;
        if(nodes.containsKey(position)){
            node = nodes.get(position);
        }else{
            node = new PertNode(position);
            linkTaskToPredecessor(node);
        }
        return node;
    }

    /**
     * Retourne l'arête correspondant à la tâche en argument ou null si elle n'existe pas.
     * @param task la tâche dont il faut retrouver l'arête
     * @return l'arête correspondant à la tâche en argument ou null si elle n'existe pas.
     */
    private PertEdge getExistingEdge(PertTask task){
        return edges.get(task);
    }

    /**
     * Elague la tâche fictive si elle peut être supprimée.
     * @param fakeTask la tâche fictive à élaguer
     */
    public void pruneFakeTask(PertTask fakeTask){
        PertEdge fakeEdge = edges.get(fakeTask);
        PertNode originNode = nodes.get(fakeEdge.getOrigin());
        PertNode targetNode = nodes.get(fakeEdge.getTarget());
        if(targetNode.hasOneOutgoingEdge() && originNode.hasOneOutgoingEdge()){
            PertNode newOrigin = findOriginToReattach(originNode, targetNode.getPosition());
            if(newOrigin != null){
                redirectEdge(originNode, targetNode, fakeEdge, newOrigin);
                edges.remove(fakeTask);
                if(originNode.isEmpty()) nodes.remove(originNode.getPosition());
            }
        }
    }

    /**
     * Redirige une arête prédécesseur d'une tâche fictive vers la destination de cette tâche fictive
     * @param fakeOrigin l'étape d'origine de la tâche fictive
     * @param targetNode l'étape destination de la tâche fictive
     * @param fakeEdge l'arête fictive
     * @param newOrigin l'origine de l'arête prédécesseur à rediriger
     */
    private void redirectEdge(PertNode fakeOrigin, PertNode targetNode, PertEdge fakeEdge, PertNode newOrigin){
        PertEdge edgeToRedirect = newOrigin.getOutGoingEdgeWithTarget(fakeOrigin.getPosition());
        detachEdge(fakeOrigin, targetNode, fakeEdge, edgeToRedirect);
        reattachEdge(edgeToRedirect, targetNode);
    }

    /**
     * Détache l'arête fictive de son étape de destination et d'origine
     * et détache l'arête à rediriger de l'étape de destination de la tâche fictive
     * @param originNode l'étape d'origine de la tâche fictive
     * @param targetNode l'étape de destination de la tache fictive
     * @param fakeEdge l'arête fictive
     * @param edgeToRedirect l'arête à rediriger
     */
    private void detachEdge(PertNode originNode, PertNode targetNode, PertEdge fakeEdge, PertEdge edgeToRedirect){
        originNode.removeOutgoingEdge(fakeEdge);
        originNode.removeIncomingEdge(edgeToRedirect);
        targetNode.removeIncomingEdge(fakeEdge);
    }

    /**
     * Rattache l'arête à l'étape de destination.
     * @param edge l'arête à rattacher
     * @param targetNode l'étape de destiation
     */
    private void reattachEdge(PertEdge edge, PertNode targetNode){
        edge.setTarget(targetNode.getPosition());
        targetNode.addIncomingEdge(edge);
    }

    /**
     * Cherche une étape permettant d'élaguer la tâche fictive pouvant
     * rattacher une de ses arêtes à l'étape de destination de la tâche fictive
     * @param fakeEdgeOrigin l'origine de la tâche fictive
     * @param target le numéro de l'étape de destination de la tâche fictive
     * @return l'étape permettant d'élaguer la tache fictive ou null si aucune ne satisfait cette condition
     */
    private PertNode findOriginToReattach(PertNode fakeEdgeOrigin, int target){
        for(PertEdge incomingEdges : fakeEdgeOrigin.getIncomingEdges()){
            PertNode originCandidate = nodes.get(incomingEdges.getOrigin());
            if(!originCandidate.hasOutgoingEdge(target)) return originCandidate;
        }
        return null;
    }
}
