package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import javafx.collections.ObservableList;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningProgress;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.JobProgressViewModel;

import java.io.File;

/**
 * Définit l'interface du controleur de consultation d'avancement de montage.
 */
public interface SupervisePlanning {

    /**
     * Charge un montage plannifié depuis un fichier.
     * @param file le fichier du montage
     * @return l'avancement du montage contenu dans le fichier
     * @throws PlanningRepositoryException survient lors d'une erreur de lecture du fichier de montage
     */
    PlanningProgress loadPlanning(File file) throws PlanningRepositoryException;

    /**
     * Retourne le chemin d'accès du répertoire des fichiers de montage.
     * @return le chemin d'accès du répertoire des fichiers de montage.
     */
    File getPlanningFilesDirectory();

    /**
     * Retourne une liste observable de modèles de vues des tâches de l'avancement de montage.
     * @param planningProgress l'avancement de montage
     * @return une liste observable de modèles de vues des tâches de l'avancement de montage
     */
    ObservableList<JobProgressViewModel> getJobProgressViewModels(PlanningProgress planningProgress);

}