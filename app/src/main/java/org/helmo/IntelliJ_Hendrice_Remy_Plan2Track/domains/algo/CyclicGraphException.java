package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

/**
 * D�finit une exception lanc�e lorsqu'un cycle est d�tect� dans le graphe PERT.
 */
public class CyclicGraphException extends PertException {
    /**
     * Initialise le message d'exception
     */
    public CyclicGraphException(){
        super("Un cycle a �t� d�tect� dans la s�quence des t�ches");
    }
}
