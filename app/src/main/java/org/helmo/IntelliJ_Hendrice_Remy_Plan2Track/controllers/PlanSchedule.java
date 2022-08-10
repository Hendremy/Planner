package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Schedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.PertTaskViewModel;

import java.util.Date;
import java.util.List;

public interface PlanSchedule {

    List<PertTaskViewModel> getCriticalPath() throws PertException;

    int getEarliestEndDate() throws PertException;

    String getPlanningName();

    Schedule generateSchedule(Date startDate);
}
