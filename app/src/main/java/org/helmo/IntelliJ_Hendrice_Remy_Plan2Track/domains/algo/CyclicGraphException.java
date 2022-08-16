package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

/**
 * Définit une exception lancée lorsqu'un cycle est détecté dans le graphe PERT.
 */
public class CyclicGraphException extends PertException {
    public CyclicGraphException(){
        super("Un cycle a été détecté dans la séquence des tâches");
    }
}
