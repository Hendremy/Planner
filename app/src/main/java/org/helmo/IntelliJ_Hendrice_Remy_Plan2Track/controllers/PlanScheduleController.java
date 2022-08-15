package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.*;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertGraph;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertSchedulePlanner;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTask;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.PertTaskViewModel;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.ScheduleRowViewModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PlanScheduleController implements PlanSchedule{

    private final ManagePlanning manageController;
    private PertGraph graph;
    private Schedule schedule;

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
    public List<ScheduleRowViewModel> generateSchedule(LocalDate startDate){
        schedule = getScheduleGenerator().generate(graph, getPlanning(), startDate);
        List<ScheduleRowViewModel> scheduleRows = new LinkedList<>();
        for(PlannedJob job : schedule.getSchedule()){
            scheduleRows.add(new ScheduleRowViewModel(job));
        }
        return scheduleRows;
    }

    @Override
    public boolean planningIsEmpty(){
        return getPlanning().isEmpty();
    }

    @Override
    public boolean planningAllTasksAssigned(){
        return getPlanning().allTasksAssigned();
    }

    @Override
    public void saveSchedule() throws PlanningRepositoryException {
        if(schedule != null){
            getRepository().writeSchedule(schedule);
        }
    }

    private PertSchedulePlanner getSchedulePlanner(){
        return manageController.getSchedulePlanner();
    }

    private ScheduleGenerator getScheduleGenerator(){return manageController.getScheduleGenerator();}

    private Planning getPlanning(){ return manageController.getPlanning();}

    private PlanningRepository getRepository(){
        return manageController.getRepository();
    }
}
