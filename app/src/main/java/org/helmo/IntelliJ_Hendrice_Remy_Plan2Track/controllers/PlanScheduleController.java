package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertGraph;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertSchedulePlanner;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTask;

import java.util.List;

public class PlanScheduleController implements PlanSchedule{

    private final ManagePlanning manageController;
    private PertGraph graph;

    public PlanScheduleController(ManagePlanning manageController) {
        this.manageController = manageController;
    }

    private void buildGraph() throws PertException {
        PertSchedulePlanner planner = manageController.getSchedulePlanner();
        graph = planner.planSchedule(manageController.getPlanning());
    }

    @Override
    public List<PertTask> getCriticalPath() throws PertException {
        buildGraph();
        return getSchedulePlanner().findCriticalPath(graph);
    }

    @Override
    public int getEarliestEndDate() throws PertException {
        buildGraph();
        return getSchedulePlanner().findEarliestEndDate(graph);
    }

    @Override
    public String getPlanningName() {
        return manageController.getPlanning().getName();
    }

    private PertSchedulePlanner getSchedulePlanner(){
        return manageController.getSchedulePlanner();
    }
}
