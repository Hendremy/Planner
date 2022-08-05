package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.AssignJobs;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;

import java.util.ArrayList;
import java.util.List;

public class AssignJobsView extends CliView{

    private final AssignJobs controller;

    public AssignJobsView(AssignJobs controller){
        this.controller = controller;
    }

    public void assignJobs(){
        Iterable<Job> jobs = controller.getJobs();
        if(jobs.iterator().hasNext()){
            String jobName = "blank";
            while(!jobName.isBlank()){
                displayJobs(jobs);
                jobName = console.askString("Nom de la tâche à assigner ou Enter pour arrêter ?");
                assignJob(jobName);
            }
        }else{
            console.println("Aucune tâche à assigner");
        }
    }

    private void displayJobs(Iterable<Job> jobs){
        console.println(presenter.displayAssignedJobs(jobs));
    }

    private void assignJob(String jobName){
        if(!jobName.isBlank()){
            if(!controller.jobExists(jobName))
            {
                console.println("Nom de tâche invalide");
            }
            else {
                int techPosition = chooseTech(getTechnicianList());
                if(techPosition != 0){
                    controller.assignJob(jobName, techPosition - 1);
                }
            }
        }
    }

    private int chooseTech(List<Technician> technicians){
        displayTechnicians(technicians);
        return findTechnician(technicians);
    }

    private void displayTechnicians(Iterable<Technician> technicians){
        console.println("Chefs d'équipe :");
        console.println(presenter.listTechnicians(technicians));
    }

    private int findTechnician(List<Technician> technicians){
        int choice = -1;
        while(!(0 <= choice && choice <= technicians.size())){
            choice = console.askPosInt("Numéro choisi (ou 0 pour annuler) ?");
        }
        return choice;
    }

    private List<Technician> getTechnicianList(){
        List<Technician> technicianList = new ArrayList<>();
        controller.getTechnicians().forEach(technicianList::add);
        return technicianList;
    }

}
