package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;

import java.util.ArrayList;
import java.util.List;

public class AssignJobsController extends Controller implements AssignJobs {

    private final Planning planning;

    public AssignJobsController(PlanningRepository planningRepository, Planning planning) {
        super(planningRepository);
        this.planning = planning;
    }

    @Override
    public Iterable<Technician> getTechnicians(){
        return getRepository().getTechnicians();
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
    public void assignJob(String jobName, int techPosition){
        Job job = planning.getJobByName(jobName);
        List<Technician> technicianList = techniciansToList(getRepository().getTechnicians());
        Technician tech = technicianList.get(techPosition);

        if(job != null && tech != null){
            job.setTechnician(tech);
        }
    }

    private List<Technician> techniciansToList(Iterable<Technician> techs){
        List<Technician> techList = new ArrayList<>();
        techs.forEach(techList::add);
        return techList;
    }
}
