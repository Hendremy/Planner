package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.Set;

/**
 * D�finit l'interface d'une t�che PERT
 */
public interface PertTask {

    /**
     * Retourne les pr�d�cesseurs de la t�che
     * @return les pr�d�cesseurs de la t�che
     */
    Set<PertTask> getPredecessors();

    /**
     * Retourne le nom de la t�che
     * @return le nom de la t�che
     */
    String getName();

    /**
     * Retourne vrai si la t�che a pour pr�d�cesseur la t�che en param�tre, sinon faux
     * @param predecessor la t�che potentiellement pr�d�cesseur
     * @return vrai si la t�che a pour pr�d�cesseur la t�che en param�tre, sinon faux
     */
    boolean hasPredecessor(PertTask predecessor);

    /**
     * Retourne vrai si la t�che a des pr�d�cesseurs, sinon faux.
     * @return vrai si la t�che a des pr�d�cesseurs, sinon faux.
     */
    boolean hasPredecessors();

    /**
     * Retourne la dur�e de la t�che.
     * @return la dur�e de la t�che
     */
    int getDuration();
}
