package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.TechnicianViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AssignJobsController implements AssignJobs {

    private final ManagePlanning manageController;

    public AssignJobsController(ManagePlanning manageController) {
        this.manageController = manageController;
    }

    private Iterable<Technician> getTechnicians() throws PlanningRepositoryException {
        return getRepository().getTechnicians();
    }

    @Override
    public Collection<TechnicianViewModel> getTechniciansViewModels() throws PlanningRepositoryException {
        List<TechnicianViewModel> techVMs = new ArrayList<>();
        Iterable<Technician> techs = getTechnicians();
        techs.forEach(t -> techVMs.add(new TechnicianViewModel(t)));
        return techVMs;
    }

    @Override
    public Iterable<Job> getJobs() {
        return getPlanning().getJobs();
    }

    @Override
    public boolean jobExists(String name){
        return getPlanning().hasJob(name);
    }

    @Override
    public void assignJob(String jobName, String code) throws PlanningRepositoryException {
        Job job = getPlanning().getJobByName(jobName);
        Technician tech = findTechnician(code);
        if(job != null && tech != null){
            job.setTechnician(tech);
        }
    }

    private Technician findTechnician(String code) throws PlanningRepositoryException {
        Iterable<Technician> techs = getTechnicians();
        for(Technician tech : techs){
            if(tech.getCode().equals(code)){
                return tech;
            }
        }
        return null;
    }

    private PlanningRepository getRepository() { return manageController.getRepository();}
    private Planning getPlanning(){
        return manageController.getPlanning();
    }
}
