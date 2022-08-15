package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningProgress;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;

import java.io.File;

public interface SupervisePlanning {

    PlanningProgress loadPlanning(File file) throws PlanningRepositoryException;
}
