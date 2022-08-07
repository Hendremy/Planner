package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.JobViewModel;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.TechnicianViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AssignJobsController extends Controller implements AssignJobs {

    private final Planning planning;

    public AssignJobsController(PlanningRepository planningRepository, Planning planning) {
        super(planningRepository);
        this.planning = planning;
    }

    private Iterable<Technician> getTechnicians(){
        return getRepository().getTechnicians();
    }

    @Override
    public Collection<TechnicianViewModel> getTechniciansViewModels(){
        List<TechnicianViewModel> techVMs = new ArrayList<>();
        Iterable<Technician> techs = getTechnicians();
        techs.forEach(t -> techVMs.add(new TechnicianViewModel(t)));
        return techVMs;
    }

    @Override
    public Iterable<Job> getJobs() {
        return planning.getJobs();
    }

    @Override
    public boolean jobExists(String name){
        return planning.hasJob(name);
    }

    @Override
    public void assignJob(String jobName, String code){
        Job job = planning.getJobByName(jobName);
        Technician tech = findTechnician(code);
        if(job != null && tech != null){
            job.setTechnician(tech);
        }
    }

    private Technician findTechnician(String code){
        Iterable<Technician> techs = getTechnicians();
        for(Technician tech : techs){
            if(tech.getCode().equals(code)){
                return tech;
            }
        }
        return null;
    }
}
