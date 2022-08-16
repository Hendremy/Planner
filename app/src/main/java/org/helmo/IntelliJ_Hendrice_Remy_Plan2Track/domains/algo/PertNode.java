package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Définit une étape d'un graphe PERT.
 */
public class PertNode{
    private int position;
    private final Set<PertEdge> outgoingEdges;
    private final Set<PertEdge> incomingEdges;
    private int earliestTime;
    private int latestTime;

    /**
     * Initialise les collections d'arêtes sortantes & incidentes et lui assigne sa position.
     * @param position la position de l'étape
     */
    public PertNode(int position){
        this.position = position;
        outgoingEdges = new HashSet<>();
        incomingEdges = new HashSet<>();
        earliestTime = 0;
        latestTime = 0;
    }

    /**
     * Ajoute une arête sortante à l'étape.
     * @param edge l'arête sortante
     */
    public void addOutGoingEdge(PertEdge edge){
        outgoingEdges.add(edge);
    }

    /**
     * Ajoute une arête incidente à l'étape.
     * @param edge l'arête incidente
     */
    public void addIncomingEdge(PertEdge edge){
        incomingEdges.add(edge);
    }

    /**
     * Retourne la position de l'étape.
     * @return la position de l'étape
     */
    public int getPosition(){
        return position;
    }

    /**
     * Retourne l'ensemble des arêtes incidentes à l'étape.
     * @return l'ensemble des arêtes incidentes à l'étape.
     */
    public Collection<PertEdge> getIncomingEdges(){
        return new HashSet<>(incomingEdges);
    }

    /**
     * Retourne l'ensemble des arêtes sortantes de l'étape.
     * @return l'ensemble des arêtes sortantes de l'étape.
     */
    public Collection<PertEdge> getOutgoingEdges(){return new HashSet<>(outgoingEdges);}

    /**
     * Retourne vrai si l'étape contient une arête sortante qui va jusqu'à l'étape fournie en paramètre, sinon faux.
     * @param target l'étape de destination
     * @return vrai si l'étape contient une arête sortante qui va jusqu'à l'étape fournie en paramètre, sinon faux
     */
    public boolean hasOutgoingEdge(int target){
        for(PertEdge edge : outgoingEdges){
            if(edge.getTarget() == target) return true;
        }
        return false;
    }

    /**
     * Retourne vrai si l'étape a une seule arête sortante, sinon faux.
     * @return vrai si l'étape a une seule arête sortante, sinon faux
     */
    public boolean hasOneOutgoingEdge(){
        return outgoingEdges.size() == 1;
    }

    /**
     * Retourne vrai si l'étape a des arêtes sortantes, sinon faux.
     * @return vrai si l'étape a des arêtes sortantes, sinon faux
     */
    public boolean hasOutgoingEdges(){return !outgoingEdges.isEmpty();}

    /**
     * Retire une arête sortante de l'étape.
     * @param edge l'arête sortante à retirer
     */
    public void removeOutgoingEdge(PertEdge edge){
        outgoingEdges.remove(edge);
    }

    /**
     * Retire une arête incidente à l'étape.
     * @param edge l'arête incidente à retirer
     */
    public void removeIncomingEdge(PertEdge edge){
        incomingEdges.remove(edge);
    }

    /**
     * Retourne l'arête sortante de l'étape qui a pour numéro de destination celui fournie en paramètre
     * ou nulle si aucune ne satisfait la condition.
     * @param target le numéro de l'étape destination
     * @return l'arête sortante de l'étape qui a pour numéro de destination celui fournie en paramètre ou null si aucune ne satisfait la condition
     */
    public PertEdge getOutGoingEdgeWithTarget(int target){
        for(PertEdge edge : outgoingEdges){
            if(edge.getTarget() == target) return edge;
        }
        return null;
    }

    /**
     * Retourne vrai si l'étape n'a aucune arête incidente ou sortante, sinon faux.
     * @return vrai si l'étape n'a aucune arête incidente ou sortante, sinon faux
     */
    public boolean isEmpty(){
        return incomingEdges.isEmpty() && outgoingEdges.isEmpty();
    }

    /**
     * Définit la date de fin au plus tôt de l'étape.
     * @param time la date de fin au plus tôt
     */
    public void setEarliestTime(int time){
        earliestTime = time;
    }

    /**
     * Retourne la date de fin au plus tôt de l'étape.
     * @return la date de fin au plus tôt
     */
    public int getEarliestTime(){return earliestTime;}

    /**
     * Définit la date de fin au plus tard de l'étape.
     * @param time la date de fin au plus tard
     */
    public void setLatestTime(int time){
        latestTime = time;
    }

    /**
     * Retourne la date de fin au plus tard de l'étape.
     * @return la date de fin au plus tard
     */
    public int getLatestTime(){ return latestTime;}
}
