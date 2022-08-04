package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Console;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Presenter;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;

import java.util.ArrayList;
import java.util.List;

public class AssignJobsController extends Controller{
    public AssignJobsController(Console console, Presenter presenter, PlanningRepository repository) {
        super(console, presenter, repository);
    }

    public void assignJobs(Planning planning){
        if(!planning.isEmpty()){
            String jobName = "blank";
            while(!jobName.isBlank()){
                displayJobs(planning);
                jobName = console.askString("Nom de la tâche à assigner ou Enter pour arrêter ?");
                assignJob(planning, jobName);
            }
        }else{
            console.println("Aucune tâche à assigner");
        }
    }

    private void displayJobs(Planning planning){
        console.println(presenter.displayAssignedJobs(planning.getJobs()));
    }

    private void assignJob(Planning planning, String jobName){
        if(!jobName.isBlank()){
            Job job = planning.getJobByName(jobName);
            if(job == null)
            {
                console.println("Nom de tâche invalide");
            }
            else {
                var tech = chooseTech(getTechnicianList());
                if(tech != null){
                    job.setTechnician(tech);
                }
            }
        }
    }

    private Technician chooseTech(List<Technician> technicians){
        displayTechnicians(technicians);
        return findTechnician(technicians);
    }

    private void displayTechnicians(Iterable<Technician> technicians){
        console.println("Chefs d'équipe :");
        console.println(presenter.listTechnicians(technicians));
    }

    private Technician findTechnician(List<Technician> technicians){
        int choice = -1;
        while(!(0 <= choice && choice <= technicians.size())){
            choice = console.askPosInt("Numéro choisi (ou 0 pour annuler) ?");
        }
        return choice == 0 ? null : technicians.get(choice - 1);
    }

    private List<Technician> getTechnicianList(){
        List<Technician> technicianList = new ArrayList<>();
        repository.getTechnicians().forEach(technicianList::add);
        return technicianList;
    }

}
