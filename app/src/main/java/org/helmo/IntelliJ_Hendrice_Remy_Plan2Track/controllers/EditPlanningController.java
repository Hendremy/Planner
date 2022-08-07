package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.viewmodels.JobViewModel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class EditPlanningController extends Controller implements EditPlanning {

    private ManagePlanning mainCtrl;

    public EditPlanningController(PlanningRepository planningRepository, ManagePlanning mainCtrl) {
        super(planningRepository);
        this.mainCtrl = mainCtrl;
    }

    @Override
    public Planning getPlanning(){
        return mainCtrl.getPlanning();
    }

    @Override
    public JobViewModel getJobViewModel(String name){
        Job job = getPlanning().getJobByName(name);
        return job != null ? new JobViewModel(job) : null;
    }

    @Override
    public Collection<JobViewModel> getJobsViewModels(){
        Iterable<Job> jobsInPlanning = getPlanning().getJobs();
        Set<JobViewModel> jobsSet = new HashSet<>();
        jobsInPlanning.forEach(j -> jobsSet.add(new JobViewModel(j)));
        return jobsSet;
    }

    @Override
    public Collection<String> getJobsNames(){
        Iterable<Job> jobsInPlanning = getPlanning().getJobs();
        Set<String> jobsSet = new HashSet<>();
        jobsInPlanning.forEach(j -> jobsSet.add(j.getName()));
        return jobsSet;
    }

    @Override
    public void editName(String name) {
        getPlanning().setName(name);
    }

    @Override
    public AddJob getAddJobController(){
        return mainCtrl.getAddJobController();
    }

    @Override
    public RemoveJob getRemoveJobController(){
        return mainCtrl.getRemoveJobController();
    }

    @Override
    public AssignJobs getAssignJobsController(){
        return mainCtrl.getAssignJobsController();
    }
}
