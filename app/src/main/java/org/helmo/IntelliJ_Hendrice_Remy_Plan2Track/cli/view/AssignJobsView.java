package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.AssignJobs;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.viewmodels.TechnicianViewModel;

import java.util.ArrayList;
import java.util.List;

public class AssignJobsView extends CliView{

    private final AssignJobs controller;
    private final List<TechnicianViewModel> techs;

    public AssignJobsView(AssignJobs controller){
        this.controller = controller;
        this.techs = new ArrayList<>(controller.getTechniciansViewModels());
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
                int techPosition = chooseTech();
                if(0 < techPosition && techPosition <= techs.size()){
                    TechnicianViewModel tech = techs.get(techPosition - 1);
                    controller.assignJob(jobName, tech.getCode());
                }
            }
        }
    }

    private int chooseTech(){
        displayTechnicians(techs);
        return findTechnician(techs);
    }

    private void displayTechnicians(List<TechnicianViewModel> technicians){
        console.println("Chefs d'équipe :");
        console.println(presenter.listTechnicians(technicians));
    }

    private int findTechnician(List<TechnicianViewModel> technicians){
        int choice = -1;
        while(!(0 <= choice && choice <= technicians.size())){
            choice = console.askPosInt("Numéro choisi (ou 0 pour annuler) ?");
        }
        return choice;
    }
}
