package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import javafx.collections.ObservableList;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningProgress;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.JobProgressViewModel;

import java.io.File;

/**
 * D�finit l'interface du controleur de consultation d'avancement de montage.
 */
public interface SupervisePlanning {

    /**
     * Charge un montage plannifi� depuis un fichier.
     * @param file le fichier du montage
     * @return l'avancement du montage contenu dans le fichier
     * @throws PlanningRepositoryException survient lors d'une erreur de lecture du fichier de montage
     */
    PlanningProgress loadPlanning(File file) throws PlanningRepositoryException;

    /**
     * Retourne le chemin d'acc�s du r�pertoire des fichiers de montage.
     * @return le chemin d'acc�s du r�pertoire des fichiers de montage.
     */
    File getPlanningFilesDirectory();

    /**
     * Retourne une liste observable de mod�les de vues des t�ches de l'avancement de montage.
     * @param planningProgress l'avancement de montage
     * @return une liste observable de mod�les de vues des t�ches de l'avancement de montage
     */
    ObservableList<JobProgressViewModel> getJobProgressViewModels(PlanningProgress planningProgress);

}