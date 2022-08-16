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

/**
 * Définit le contrôleur de plannification du montage.
 */
public class PlanScheduleController implements PlanSchedule{

    private final ManagePlanning manageController;
    private PertGraph graph;
    private Schedule schedule;

    /**
     * Initialise le contrôleur de plannification du montage avec le controleur de gestion de montage.
     * @param manageController le controleur de gestion du montage
     */
    public PlanScheduleController(ManagePlanning manageController) {
        this.manageController = manageController;
    }

    /**
     * Construit le graphe PERT du montage.
     * @throws PertException survient lors d'une erreur dans la création du graphe PERT
     */
    private void buildGraph() throws PertException {
        PertSchedulePlanner planner = getSchedulePlanner();
        graph = planner.planSchedule(getPlanning());
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
        return getPlanning().getName();
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

    /**
     * Retourne le plannificateur de montage.
     * @return le plannificateur de montage
     */
    private PertSchedulePlanner getSchedulePlanner(){
        return manageController.getSchedulePlanner();
    }

    /**
     * Retourne le générateur de programme de montage.
     * @return le générateur de programme de montage
     */
    private ScheduleGenerator getScheduleGenerator(){return manageController.getScheduleGenerator();}

    /**
     * Retourne le planning en cours.
     * @return le planning en cours
     */
    private Planning getPlanning(){ return manageController.getPlanning();}

    /**
     * Retourne l'objet de stockage de montage.
     * @return l'objet de stockage de montage.
     */
    private PlanningRepository getRepository(){
        return manageController.getRepository();
    }
}
