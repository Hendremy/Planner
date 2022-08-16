package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

/**
 * D�finit l'interface de r�seau de t�ches PERT � partir duquel il est possible de cr�er un graphe
 */
public interface PertNetwork {

    /**
     * Retourne l'ensemble des t�ches PERT du r�seau.
     * @return l'ensemble des t�ches PERT du r�seau
     */
    Iterable<PertTask> getTasks();
}
