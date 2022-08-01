package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Console;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Presenter;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Planning;

public class RemoveJobController extends Controller{
    public RemoveJobController(Console console, Presenter presenter) {
        super(console, presenter);
    }

    public void removeJob(Planning planning){
        String name = null;
        while(name == null || !name.isBlank()){
            name = console.askString("Nom de la tâche à modifier ? (Enter pour annuler)");
            deleteJob(planning, name);
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
        if(job != null){
            int occ = planning.countPriorJob(job);
            console.println(String.format("%s est requise pour %d autre(s) tâche(s)", job.getName(), occ));
            if(console.askYesNo("Voulez-vous vraiment la supprimer ?")){
                planning.removeJob(job);
            }
        }
        else{
            console.println("Ce nom de tâche n'existe pas");
        }
    }
}
