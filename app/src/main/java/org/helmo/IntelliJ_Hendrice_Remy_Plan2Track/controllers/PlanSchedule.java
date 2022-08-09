package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTask;

import java.util.List;

public interface PlanSchedule {

    List<PertTask> getCriticalPath() throws PertException;

    int getEarliestEndDate() throws PertException;
}
