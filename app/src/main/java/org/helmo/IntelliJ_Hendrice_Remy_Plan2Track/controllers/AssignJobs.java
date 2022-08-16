package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.TechnicianViewModel;

import java.util.Collection;

/**
 * Définit l'interface de contrôleur d'assignation des tâches.
 */
public interface AssignJobs {

    /**
     * Récupère les modèles de vues des chefs d'équipes.
     * @return les modèles de vues des chefs d'équipes.
     * @throws PlanningRepositoryException survient lors d'une erreur dans le chargement des chefs d'équipe dans le PlanningRepository
     */
    Collection<TechnicianViewModel> getTechniciansViewModels() throws PlanningRepositoryException;

    /**
     * Récupère les tâches à assigner.
     * @return les tâches à assigner
     */
    Iterable<Job> getJobs();

    /**
     * Retourne vrai si la tâche existe, sinon faux.
     * @param name le nom de la tâche dont l'existence doit être vérifiée
     * @return vrai si la tâche existe, sinon faux
     */
    boolean jobExists(String name);

    /**
     * Assigne une tâche à un chef d'équipe.
     * @param jobName le nom de la tâche à assigner
     * @param code le code du chef d'équipe
     * @throws PlanningRepositoryException survient lors d'une erreur dans le chargement des chefs d'équipe dans le PlanningRepository
     */
    void assignJob(String jobName, String code) throws PlanningRepositoryException;
}
