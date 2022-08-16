package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.PertTaskViewModel;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.ScheduleRowViewModel;

import java.time.LocalDate;
import java.util.List;

/**
 * Définit l'interface de contrôleur de plannification de montage
 */
public interface PlanSchedule {

    /**
     * Retourne le chemin critique du montage.
     * @return le chemin critique du montage
     * @throws PertException survient lors d'une erreur dans le calcul du chemin critique.
     */
    List<PertTaskViewModel> getCriticalPath() throws PertException;

    /**
     * Retourne la date de début au plus tôt du montage.
     * @return la date de début au plus tôt du montage
     * @throws PertException survient lors d'une erreur dans le calcul du chemin critique.
     */
    int getEarliestEndDate() throws PertException;

    /**
     * Retourne le nom du montage en cours.
     * @return le nom du montage en cours
     */
    String getPlanningName();

    /**
     * Génère un programme pour le montage en cours à partir d'une date de début
     * @param startDate la date de début
     * @return le programme pour le montage à partir de la date de début
     */
    List<ScheduleRowViewModel> generateSchedule(LocalDate startDate);

    /**
     * Sauvegarde le programme du montage dans un fichier.
     * @throws PlanningRepositoryException survient lors d'une erreur lors de la sauvegarde du montage
     */
    void saveSchedule() throws PlanningRepositoryException;

    /**
     * Retourne vrai si le montage ne contient pas de tâches, sinon faux.
     * @return vrai si le montage ne contient pas de tâches, sinon faux.
     */
    boolean planningIsEmpty();

    /**
     * Retourne vrai si toutes les tâches du montage sont assignées, sinon faux.
     * @return vrai si toutes les tâches du montage sont assignées, sinon faux
     */
    boolean planningAllTasksAssigned();
}
