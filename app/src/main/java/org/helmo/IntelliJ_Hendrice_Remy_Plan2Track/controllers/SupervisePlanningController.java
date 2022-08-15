package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningProgress;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.ConvertPlanningDTO;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningDTO;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;

import java.io.File;

public class SupervisePlanningController implements SupervisePlanning{
    private final PlanningRepository repository;
    private final ConvertPlanningDTO converter;

    public SupervisePlanningController(PlanningRepository repository, ConvertPlanningDTO converter){
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public PlanningProgress loadPlanning(File file) throws PlanningRepositoryException {
        PlanningDTO planningDTO = repository.loadSchedule(file.getAbsolutePath());
        return converter.toPlanningProgress(planningDTO);
    }
}
