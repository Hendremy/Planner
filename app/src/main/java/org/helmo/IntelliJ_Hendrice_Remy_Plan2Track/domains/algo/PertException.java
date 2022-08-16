package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

/**
 * Définit une exception lancée lors d'une erreur dans la conception du graphe PERT
 */
public class PertException extends Exception{
    public PertException(String message){
        super(message);
    }
}
