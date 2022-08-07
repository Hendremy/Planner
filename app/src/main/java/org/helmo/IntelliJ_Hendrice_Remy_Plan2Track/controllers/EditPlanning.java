package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.viewmodels.JobViewModel;

import java.util.Collection;

public interface EditPlanning {

    Planning getPlanning();

    Collection<JobViewModel> getJobsViewModels();

    Collection<String> getJobsNames();

    JobViewModel getJobViewModel(String name);

    void editName(String name);

    AddJob getAddJobController();

    RemoveJob getRemoveJobController();

    AssignJobs getAssignJobsController();
}
