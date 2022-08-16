package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

/**
 * D�finit une exception lanc�e lorsqu'une action de l'objet de stockage des montages �choue.
 */
public class PlanningRepositoryException extends Exception{

    public PlanningRepositoryException(String message){
        super(message);
    }
}
