package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;

public abstract class Controller {

    private final PlanningRepository repository;

    public Controller(PlanningRepository planningRepository){
        this.repository = planningRepository;
    }

    public PlanningRepository getRepository(){
        return repository;
    }
}
