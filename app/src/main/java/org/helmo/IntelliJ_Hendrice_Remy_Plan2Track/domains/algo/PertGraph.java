package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.*;

/**
 * D�finit un graphe PERT.
 */
public class PertGraph {
    private final Map<Integer, PertNode> nodes;
    private final Map<PertTask, PertEdge> edges;

    /*
     * Choix de la collection pour les �tapes du graphe: Map
     *      - Je veux pouvoir acc�der aux �tapes d'un graphe sur base de leur num�ro
     *          => List pourrait convenir mais l'�laguage des ar�tes fictives pourrait supprimer des �tapes
     *              et donc les num�ros des �tapes seraient chamboul�s
     *          => Set ne convient pas
     *
     * => Impl�mentation : HashMap
     *      - Je n'ai pas besoin d'avoir mes �l�ments tri�s, j'acc�de directement � une �tape
     *        gr�ce � son num�ro, donc TreeMap pas utile
     *      - Acc�s, ajout et suppression d'un �l�ment en O(1)
     *
     */

    /**
     * Initialise sa collection d'ar�tes et d'�tapes et initialise l'�tape 1.
     */
    public PertGraph(){
        nodes = new HashMap<>();
        linkTaskToPredecessor(new PertNode(1));
        edges = new HashMap<>();
    }

    /**
     * Retourne les �tapes du graphes.
     * @return les �tapes du graphe
     */
    public Map<Integer, PertNode> getNodes(){
        return new HashMap<>(nodes);
    }

    /**
     * Retourne les ar�tes du graphes.
     * @return les ar�tes du graphe
     */
    public Set<PertEdge> getEdges(){return new HashSet<>(edges.values());}

    /**
     * Retourne l'�tape correspondant � la position en param�tre ou null si elle n'existe pas
     * @param position le num�ro de l'�tape
     * @return l'�tape correspondant � la position en param�tre ou null si elle n'existe pas
     */
    public PertNode getNode(int position){return nodes.get(position);}

    /**
     * Ajoute une �tape au graphe.
     * @param node l'�tape � ajouter
     */
    public void linkTaskToPredecessor(PertNode node) {
        nodes.put(node.getPosition(), node);
    }

    /**
     * Ajoute une ar�te entre deux �tapes.
     * @param origin le num�ro de l'�tape d'origine
     * @param destination le num�ro de l'�tape de destination
     * @param task la t�che associ�e
     * @throws CyclicGraphException survient lorsqu'un cycle est d�tect� dans le r�seau de t�ches
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
     * Relie une t�che � son pr�d�cesseur en cr�ant une nouvelle �tape et en ajoutant une ar�te
     * repr�sentant cette t�che partant de l'�tape de destination de son pr�d�cesseur et arrivant � la nouvelle �tape cr��e
     * puis retourne cette nouvelle �tape
     * @param pos le num�ro de la nouvelle �tape
     * @param predecessor le pr�d�cesseur de la t�che
     * @param task la t�che � relier � son pr�d�cesseur
     * @return la nouvelle �tape cr�e
     * @throws CyclicGraphException survient lorsqu'un cycle est d�tect� dans le r�seau de t�ches
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
     * Ajoute une ar�te fictive depuis l'�tape d'origine de la t�che originelle jusqu'� une �tape cible.
     * @param targetNode l'�tape cible
     * @param task la t�che originelle
     * @return l'ar�te fictive cr��e
     * @throws CyclicGraphException survient lorsqu'un cycle est d�tect� dans le r�seau de t�ches
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
     * Ins�re une ar�te entre deux �tapes.
     * @param originNode l'�tape d'origine
     * @param targetNode l'�tape de destination
     * @param edge l'ar�te � ins�rer
     */
    private void insertEdge(PertNode originNode, PertNode targetNode, PertEdge edge){
        originNode.addOutGoingEdge(edge);
        targetNode.addIncomingEdge(edge);
    }

    /**
     * Retourne l'�tape correspondant � la position en argument et la cr�e au besoin.
     * @param position le num�ro de l'�tape
     * @return l'�tape correspondante � la position
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
     * Retourne l'ar�te correspondant � la t�che en argument ou null si elle n'existe pas.
     * @param task la t�che dont il faut retrouver l'ar�te
     * @return l'ar�te correspondant � la t�che en argument ou null si elle n'existe pas.
     */
    private PertEdge getExistingEdge(PertTask task){
        return edges.get(task);
    }

    /**
     * Elague la t�che fictive si elle peut �tre supprim�e.
     * @param fakeTask la t�che fictive � �laguer
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
     * Redirige une ar�te pr�d�cesseur d'une t�che fictive vers la destination de cette t�che fictive
     * @param fakeOrigin l'�tape d'origine de la t�che fictive
     * @param targetNode l'�tape destination de la t�che fictive
     * @param fakeEdge l'ar�te fictive
     * @param newOrigin l'origine de l'ar�te pr�d�cesseur � rediriger
     */
    private void redirectEdge(PertNode fakeOrigin, PertNode targetNode, PertEdge fakeEdge, PertNode newOrigin){
        PertEdge edgeToRedirect = newOrigin.getOutGoingEdgeWithTarget(fakeOrigin.getPosition());
        detachEdge(fakeOrigin, targetNode, fakeEdge, edgeToRedirect);
        reattachEdge(edgeToRedirect, targetNode);
    }

    /**
     * D�tache l'ar�te fictive de son �tape de destination et d'origine
     * et d�tache l'ar�te � rediriger de l'�tape de destination de la t�che fictive
     * @param originNode l'�tape d'origine de la t�che fictive
     * @param targetNode l'�tape de destination de la tache fictive
     * @param fakeEdge l'ar�te fictive
     * @param edgeToRedirect l'ar�te � rediriger
     */
    private void detachEdge(PertNode originNode, PertNode targetNode, PertEdge fakeEdge, PertEdge edgeToRedirect){
        originNode.removeOutgoingEdge(fakeEdge);
        originNode.removeIncomingEdge(edgeToRedirect);
        targetNode.removeIncomingEdge(fakeEdge);
    }

    /**
     * Rattache l'ar�te � l'�tape de destination.
     * @param edge l'ar�te � rattacher
     * @param targetNode l'�tape de destiation
     */
    private void reattachEdge(PertEdge edge, PertNode targetNode){
        edge.setTarget(targetNode.getPosition());
        targetNode.addIncomingEdge(edge);
    }

    /**
     * Cherche une �tape permettant d'�laguer la t�che fictive pouvant
     * rattacher une de ses ar�tes � l'�tape de destination de la t�che fictive
     * @param fakeEdgeOrigin l'origine de la t�che fictive
     * @param target le num�ro de l'�tape de destination de la t�che fictive
     * @return l'�tape permettant d'�laguer la tache fictive ou null si aucune ne satisfait cette condition
     */
    private PertNode findOriginToReattach(PertNode fakeEdgeOrigin, int target){
        for(PertEdge incomingEdges : fakeEdgeOrigin.getIncomingEdges()){
            PertNode originCandidate = nodes.get(incomingEdges.getOrigin());
            if(!originCandidate.hasOutgoingEdge(target)) return originCandidate;
        }
        return null;
    }
}
