package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTask;

import java.util.Queue;

public interface PlanSchedule {

    Queue<PertTask> getCriticalPath();
}
