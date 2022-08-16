package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

/**
 * Définit une exception lancée lorsqu'une action de l'objet de stockage des montages échoue.
 */
public class PlanningRepositoryException extends Exception{

    public PlanningRepositoryException(String message){
        super(message);
    }
}
