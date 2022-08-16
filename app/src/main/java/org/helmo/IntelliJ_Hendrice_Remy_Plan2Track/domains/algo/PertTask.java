package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.Set;

/**
 * Définit l'interface d'une tâche PERT
 */
public interface PertTask {

    /**
     * Retourne les prédécesseurs de la tâche
     * @return les prédécesseurs de la tâche
     */
    Set<PertTask> getPredecessors();

    /**
     * Retourne le nom de la tâche
     * @return le nom de la tâche
     */
    String getName();

    /**
     * Retourne vrai si la tâche a pour prédécesseur la tâche en paramètre, sinon faux
     * @param predecessor la tâche potentiellement prédécesseur
     * @return vrai si la tâche a pour prédécesseur la tâche en paramètre, sinon faux
     */
    boolean hasPredecessor(PertTask predecessor);

    /**
     * Retourne vrai si la tâche a des prédécesseurs, sinon faux.
     * @return vrai si la tâche a des prédécesseurs, sinon faux.
     */
    boolean hasPredecessors();

    /**
     * Retourne la durée de la tâche.
     * @return la durée de la tâche
     */
    int getDuration();
}
