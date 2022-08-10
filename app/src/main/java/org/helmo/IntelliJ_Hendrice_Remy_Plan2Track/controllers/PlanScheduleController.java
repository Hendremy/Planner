package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Schedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.ScheduleGenerator;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertGraph;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertSchedulePlanner;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTask;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.PertTaskViewModel;

import java.util.ArrayList;
import java.util.Date;
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
    public List<PertTaskViewModel> getCriticalPath() throws PertException {
        buildGraph();
        List<PertTask> tasks =  getSchedulePlanner().findCriticalPath(graph);
        List<PertTaskViewModel> tasksVM = new ArrayList<>(tasks.size());
        tasks.forEach(t -> tasksVM.add(new PertTaskViewModel(t)));
        return tasksVM;
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

    @Override
    public Schedule generateSchedule(Date startDate){
        Schedule schedule = getScheduleGenerator().generate(graph, startDate);

        return schedule;
    }

    private PertSchedulePlanner getSchedulePlanner(){
        return manageController.getSchedulePlanner();
    }

    private ScheduleGenerator getScheduleGenerator(){return manageController.getScheduleGenerator();}
}
