package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

/**
 * Définit l'interface de réseau de tâches PERT à partir duquel il est possible de créer un graphe
 */
public interface PertNetwork {

    /**
     * Retourne l'ensemble des tâches PERT du réseau.
     * @return l'ensemble des tâches PERT du réseau
     */
    Iterable<PertTask> getTasks();
}
