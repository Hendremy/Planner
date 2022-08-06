package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.AddJob;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class AddJobView extends CliView{

    private final AddJob controller;

    public AddJobView (AddJob controller){
        this.controller = controller;
    }

    public void addJob(){
        String name = console.askString("Nom de la nouvelle tâche ?");
        String description = console.askString("Descriptif éventuel de la nouvelle tâche ?");
        int duration = console.askPosInt("Durée (en jours) de la nouvelle tâche ?");
        Iterable<String> priorJobs = addPriorJobs();
        controller.addJobToPlanning(name, description, duration, priorJobs);
    }

    private Iterable<String> addPriorJobs(){
        Set<String> priorJobs = new HashSet<>();
        String name = null;
        while(name == null || !name.isBlank()){
            name = console.askString("Tâche antérieure (Enter si aucune) :");
            if(!name.isBlank()) priorJobs.add(name);
        }
        return priorJobs;
    }
}
