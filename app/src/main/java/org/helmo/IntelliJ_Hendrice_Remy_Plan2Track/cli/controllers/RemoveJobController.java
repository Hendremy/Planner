package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Console;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Presenter;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Planning;

public class RemoveJobController extends Controller{
    public RemoveJobController(Console console, Presenter presenter, PlanningRepository repository) {
        super(console, presenter, repository);
    }

    public void removeJob(Planning planning){
        String name = console.askString("Nom de la tâche à modifier ? (Enter pour annuler)");
        while(name == null || !name.isBlank()){
            deleteJob(planning, name);
            name = console.askString("Nom de la tâche à modifier ? (Enter pour annuler)");
        }
    }

    private void deleteJob(Planning planning, String name){
        Job job = planning.getJobByName(name);
        if(job != null){
            confirmDelete(planning, job);
        }
        else{
            console.println("Ce nom de tâche n'existe pas");
        }
    }

    private void confirmDelete(Planning planning, Job job){
        int occ = planning.countPriorJob(job);
        console.println(String.format("%s est requise pour %d autre(s) tâche(s)", job.getName(), occ));
        if(console.askYesNo("Voulez-vous vraiment la supprimer ?")){
            planning.removeJob(job);
        }
    }
}
