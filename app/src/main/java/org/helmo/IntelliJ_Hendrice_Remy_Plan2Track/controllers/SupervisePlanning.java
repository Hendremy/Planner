package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import javafx.collections.ObservableList;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningProgress;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.JobProgressViewModel;

import java.io.File;

public interface SupervisePlanning {

    PlanningProgress loadPlanning(File file) throws PlanningRepositoryException;

    File getPlanningFilesDirectory();

    ObservableList<JobProgressViewModel> getJobProgressViewModels(PlanningProgress planningProgress);

}