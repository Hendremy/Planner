package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

/**
 * D�finit une exception lanc�e lorsqu'une action de l'objet de stockage des montages �choue.
 */
public class PlanningRepositoryException extends Exception{

    /**
     * Initialise le message d'exception.
     * @param message le message d'exception
     */
    public PlanningRepositoryException(String message){
        super(message);
    }
}
