package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

/**
 * Définit une exception lancée lors d'une erreur dans l'algorithme de PERT.
 */
public class AlgorithmException extends PertException{

    /**
     * Initialise le message d'exception.
     * @param message le message d'exception
     */
    AlgorithmException(String message) {
        super(message);
    }
}
