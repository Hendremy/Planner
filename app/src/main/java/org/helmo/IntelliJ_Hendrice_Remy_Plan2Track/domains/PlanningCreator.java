package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;


/**
 * Définit le créateur de montage.
 */
public class PlanningCreator{

    /**
     * Crée un montage à partir de son nom.
     * @param name le nom du montage
     * @return le montage créé
     */
    public Planning create(String name){
        return new Planning(name);
    }
}
