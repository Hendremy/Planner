package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.TechnicianViewModel;

import java.util.Collection;

public interface AssignJobs {

    Collection<TechnicianViewModel> getTechniciansViewModels() throws PlanningRepositoryException;

    Iterable<Job> getJobs();

    boolean jobExists(String name);

    void assignJob(String jobName, String code) throws PlanningRepositoryException;
}
