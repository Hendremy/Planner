package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTask;

/**
 * D�finit le mod�le de vue d'une t�che PERT.
 */
public class PertTaskViewModel {

    private final String name;
    private final int duration;

    /**
     * Initialise le mod�le de vue de la t�che PERT.
     * @param task la t�che PERT
     */
    public PertTaskViewModel (PertTask task){
        this.name = task.getName();
        this.duration = task.getDuration();
    }

    /**
     * Retourne le nom de la t�che.
     * @return le nom de la t�che
     */
    public String getName(){
        return name;
    }

    /**
     * Retourne la repr�sentation en chaine de caract�res de la t�che.
     * @return la repr�sentation en chaine de caract�res de la t�che
     */
    @Override
    public String toString(){
        return String.format("%s - %d j", name, duration);
    }
}
