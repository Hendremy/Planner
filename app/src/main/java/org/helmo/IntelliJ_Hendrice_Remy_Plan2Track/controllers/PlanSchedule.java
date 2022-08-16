package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.PertTaskViewModel;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.ScheduleRowViewModel;

import java.time.LocalDate;
import java.util.List;

/**
 * D�finit l'interface de contr�leur de plannification de montage
 */
public interface PlanSchedule {

    /**
     * Retourne le chemin critique du montage.
     * @return le chemin critique du montage
     * @throws PertException survient lors d'une erreur dans le calcul du chemin critique.
     */
    List<PertTaskViewModel> getCriticalPath() throws PertException;

    /**
     * Retourne la date de d�but au plus t�t du montage.
     * @return la date de d�but au plus t�t du montage
     * @throws PertException survient lors d'une erreur dans le calcul du chemin critique.
     */
    int getEarliestEndDate() throws PertException;

    /**
     * Retourne le nom du montage en cours.
     * @return le nom du montage en cours
     */
    String getPlanningName();

    /**
     * G�n�re un programme pour le montage en cours � partir d'une date de d�but
     * @param startDate la date de d�but
     * @return le programme pour le montage � partir de la date de d�but
     */
    List<ScheduleRowViewModel> generateSchedule(LocalDate startDate);

    /**
     * Sauvegarde le programme du montage dans un fichier.
     * @throws PlanningRepositoryException survient lors d'une erreur lors de la sauvegarde du montage
     */
    void saveSchedule() throws PlanningRepositoryException;

    /**
     * Retourne vrai si le montage ne contient pas de t�ches, sinon faux.
     * @return vrai si le montage ne contient pas de t�ches, sinon faux.
     */
    boolean planningIsEmpty();

    /**
     * Retourne vrai si toutes les t�ches du montage sont assign�es, sinon faux.
     * @return vrai si toutes les t�ches du montage sont assign�es, sinon faux
     */
    boolean planningAllTasksAssigned();
}
