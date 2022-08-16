package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.JobProgress;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningProgress;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.ConvertPlanningDTO;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningDTO;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.JobProgressViewModel;

import java.io.File;

/**
 * D�finit le contr�leur de consultation d'avancement de montage.
 */
public class SupervisePlanningController implements SupervisePlanning{
    private final PlanningRepository repository;
    private final ConvertPlanningDTO converter;

    /**
     * Initialise le controleur de consultation d'avancement de montage
     * @param repository l'objet de stockage de montage
     * @param converter le convertisseur d'objet de transfert de donn�es
     */
    public SupervisePlanningController(PlanningRepository repository, ConvertPlanningDTO converter){
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public PlanningProgress loadPlanning(File file) throws PlanningRepositoryException {
        PlanningDTO planningDTO = repository.loadSchedule(file.getAbsolutePath());
        return converter.toPlanningProgress(planningDTO);
    }

    @Override
    public File getPlanningFilesDirectory(){
        return repository.getPlanningFilesDirectory();
    }

    @Override
    public ObservableList<JobProgressViewModel> getJobProgressViewModels(PlanningProgress planningProgress){
        ObservableList<JobProgressViewModel> jobsVMs = FXCollections.observableArrayList();
        for(JobProgress jobP : planningProgress.getJobs()){
            jobsVMs.add(new JobProgressViewModel(jobP));
        }
        return jobsVMs;
    }
}
