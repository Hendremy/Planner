package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * D�finit une �tape d'un graphe PERT.
 */
public class PertNode{
    private int position;
    private final Set<PertEdge> outgoingEdges;
    private final Set<PertEdge> incomingEdges;
    private int earliestTime;
    private int latestTime;

    /**
     * Initialise les collections d'ar�tes sortantes & incidentes et lui assigne sa position.
     * @param position la position de l'�tape
     */
    public PertNode(int position){
        this.position = position;
        outgoingEdges = new HashSet<>();
        incomingEdges = new HashSet<>();
        earliestTime = 0;
        latestTime = 0;
    }

    /**
     * Ajoute une ar�te sortante � l'�tape.
     * @param edge l'ar�te sortante
     */
    public void addOutGoingEdge(PertEdge edge){
        outgoingEdges.add(edge);
    }

    /**
     * Ajoute une ar�te incidente � l'�tape.
     * @param edge l'ar�te incidente
     */
    public void addIncomingEdge(PertEdge edge){
        incomingEdges.add(edge);
    }

    /**
     * Retourne la position de l'�tape.
     * @return la position de l'�tape
     */
    public int getPosition(){
        return position;
    }

    /**
     * Retourne l'ensemble des ar�tes incidentes � l'�tape.
     * @return l'ensemble des ar�tes incidentes � l'�tape.
     */
    public Collection<PertEdge> getIncomingEdges(){
        return new HashSet<>(incomingEdges);
    }

    /**
     * Retourne l'ensemble des ar�tes sortantes de l'�tape.
     * @return l'ensemble des ar�tes sortantes de l'�tape.
     */
    public Collection<PertEdge> getOutgoingEdges(){return new HashSet<>(outgoingEdges);}

    /**
     * Retourne vrai si l'�tape contient une ar�te sortante qui va jusqu'� l'�tape fournie en param�tre, sinon faux.
     * @param target l'�tape de destination
     * @return vrai si l'�tape contient une ar�te sortante qui va jusqu'� l'�tape fournie en param�tre, sinon faux
     */
    public boolean hasOutgoingEdge(int target){
        for(PertEdge edge : outgoingEdges){
            if(edge.getTarget() == target) return true;
        }
        return false;
    }

    /**
     * Retourne vrai si l'�tape a une seule ar�te sortante, sinon faux.
     * @return vrai si l'�tape a une seule ar�te sortante, sinon faux
     */
    public boolean hasOneOutgoingEdge(){
        return outgoingEdges.size() == 1;
    }

    /**
     * Retourne vrai si l'�tape a des ar�tes sortantes, sinon faux.
     * @return vrai si l'�tape a des ar�tes sortantes, sinon faux
     */
    public boolean hasOutgoingEdges(){return !outgoingEdges.isEmpty();}

    /**
     * Retire une ar�te sortante de l'�tape.
     * @param edge l'ar�te sortante � retirer
     */
    public void removeOutgoingEdge(PertEdge edge){
        outgoingEdges.remove(edge);
    }

    /**
     * Retire une ar�te incidente � l'�tape.
     * @param edge l'ar�te incidente � retirer
     */
    public void removeIncomingEdge(PertEdge edge){
        incomingEdges.remove(edge);
    }

    /**
     * Retourne l'ar�te sortante de l'�tape qui a pour num�ro de destination celui fournie en param�tre
     * ou nulle si aucune ne satisfait la condition.
     * @param target le num�ro de l'�tape destination
     * @return l'ar�te sortante de l'�tape qui a pour num�ro de destination celui fournie en param�tre ou null si aucune ne satisfait la condition
     */
    public PertEdge getOutGoingEdgeWithTarget(int target){
        for(PertEdge edge : outgoingEdges){
            if(edge.getTarget() == target) return edge;
        }
        return null;
    }

    /**
     * Retourne vrai si l'�tape n'a aucune ar�te incidente ou sortante, sinon faux.
     * @return vrai si l'�tape n'a aucune ar�te incidente ou sortante, sinon faux
     */
    public boolean isEmpty(){
        return incomingEdges.isEmpty() && outgoingEdges.isEmpty();
    }

    /**
     * D�finit la date de fin au plus t�t de l'�tape.
     * @param time la date de fin au plus t�t
     */
    public void setEarliestTime(int time){
        earliestTime = time;
    }

    /**
     * Retourne la date de fin au plus t�t de l'�tape.
     * @return la date de fin au plus t�t
     */
    public int getEarliestTime(){return earliestTime;}

    /**
     * D�finit la date de fin au plus tard de l'�tape.
     * @param time la date de fin au plus tard
     */
    public void setLatestTime(int time){
        latestTime = time;
    }

    /**
     * Retourne la date de fin au plus tard de l'�tape.
     * @return la date de fin au plus tard
     */
    public int getLatestTime(){ return latestTime;}
}
