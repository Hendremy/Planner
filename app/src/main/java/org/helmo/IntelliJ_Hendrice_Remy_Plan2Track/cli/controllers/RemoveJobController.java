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
        String name = console.askString("Nom de la tâche à modifier ?");
        Job job = planning.getJobByName(name);
        if(job != null){

        }
        else{
            console.println("Ce nom de tâche n'existe pas");
        }

    }
}
