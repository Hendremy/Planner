package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.TechnicianViewModel;

import java.util.Collection;

/**
 * D�finit l'interface de contr�leur d'assignation des t�ches.
 */
public interface AssignJobs {

    /**
     * R�cup�re les mod�les de vues des chefs d'�quipes.
     * @return les mod�les de vues des chefs d'�quipes.
     * @throws PlanningRepositoryException survient lors d'une erreur dans le chargement des chefs d'�quipe dans le PlanningRepository
     */
    Collection<TechnicianViewModel> getTechniciansViewModels() throws PlanningRepositoryException;

    /**
     * R�cup�re les t�ches � assigner.
     * @return les t�ches � assigner
     */
    Iterable<Job> getJobs();

    /**
     * Retourne vrai si la t�che existe, sinon faux.
     * @param name le nom de la t�che dont l'existence doit �tre v�rifi�e
     * @return vrai si la t�che existe, sinon faux
     */
    boolean jobExists(String name);

    /**
     * Assigne une t�che � un chef d'�quipe.
     * @param jobName le nom de la t�che � assigner
     * @param code le code du chef d'�quipe
     * @throws PlanningRepositoryException survient lors d'une erreur dans le chargement des chefs d'�quipe dans le PlanningRepository
     */
    void assignJob(String jobName, String code) throws PlanningRepositoryException;
}
