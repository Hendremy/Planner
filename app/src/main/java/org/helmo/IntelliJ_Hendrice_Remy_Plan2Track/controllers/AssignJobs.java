package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;

public interface AssignJobs {

    Iterable<Technician> getTechnicians();

    Iterable<Job> getJobs();

    boolean jobExists(String name);

    void assignJob(String jobName, int techPosition);
}
