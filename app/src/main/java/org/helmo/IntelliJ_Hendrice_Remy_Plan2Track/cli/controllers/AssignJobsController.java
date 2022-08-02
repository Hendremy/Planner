package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Console;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Presenter;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Technician;

public class AssignJobsController extends Controller{
    public AssignJobsController(Console console, Presenter presenter, PlanningRepository repository) {
        super(console, presenter, repository);
    }

    public void assignJobs(Planning planning){
        if(!planning.isEmpty()){
            Iterable<Technician> technicians = repository.getTechnicians();
            Job job = chooseJob(planning);
            Technician tech = chooseTech(technicians);
        }else{
            console.println("Aucune tâche à assigner");
        }
    }

    private Job chooseJob(Planning planning){
        displayJobs(planning);
        String jobName = console.askString("Nom de la tâche à assigner ou Enter pour arrêter ?");

        return null;
    }

    private void displayJobs(Planning planning){
        console.println(presenter.listJobs(planning.getJobs()));
    }

    private Technician chooseTech(Iterable<Technician> technicians){
        return null;
    }

    private void displayTechnicians(Iterable<Technician> technicians){

    }
}
