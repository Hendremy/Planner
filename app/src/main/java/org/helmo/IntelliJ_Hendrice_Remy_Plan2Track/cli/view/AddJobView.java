package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.AddJob;

public class AddJobView extends CliView{

    private final AddJob controller;

    public AddJobView (AddJob controller){
        this.controller = controller;
    }

    public void addJob(){
        String name = console.askString("Nom de la nouvelle tâche ?");
        String description = console.askString("Descriptif éventuel de la nouvelle tâche ?");
        int duration = console.askPosInt("Durée (en jours) de la nouvelle tâche ?");
        controller.createJob(name, description, duration);
        addPriorJobs();
        controller.addJobToPlanning();
    }

    private void addPriorJobs(){
        String name = null;
        while(name == null || !name.isBlank()){
            name = console.askString("Tâche antérieure (Enter si aucune) :");
            if(!name.isBlank()) controller.addPriorJob(name);
        }
    }
}
