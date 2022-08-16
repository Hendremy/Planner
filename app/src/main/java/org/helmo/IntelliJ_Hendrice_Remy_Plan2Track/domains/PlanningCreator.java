package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;


/**
 * D�finit le cr�ateur de montage.
 */
public class PlanningCreator{

    /**
     * Cr�e un montage � partir de son nom.
     * @param name le nom du montage
     * @return le montage cr��
     */
    public Planning create(String name){
        return new Planning(name);
    }
}
