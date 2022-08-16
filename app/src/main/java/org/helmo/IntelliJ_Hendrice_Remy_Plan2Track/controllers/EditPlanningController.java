package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.JobViewModel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Définit le contrôleur de modification du montage.
 */
public class EditPlanningController implements EditPlanning {

    private final ManagePlanning manageController;

    /**
     * Initialise le contrôleur de modification du montage avec le contrleur de gestion de montage.
     * @param manageController le controleur de gestion de montage
     */
    public EditPlanningController(ManagePlanning manageController) {
        this.manageController = manageController;
    }

    @Override
    public Planning getPlanning(){
        return manageController.getPlanning();
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
        return manageController.getAddJobController();
    }

    @Override
    public RemoveJob getRemoveJobController(){
        return manageController.getRemoveJobController();
    }

    @Override
    public AssignJobs getAssignJobsController(){
        return manageController.getAssignJobsController();
    }
}
