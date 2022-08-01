package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Console;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Presenter;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Planning;

import java.util.ArrayList;
import java.util.List;

public class AddJobController extends Controller{
    public AddJobController(Console console, Presenter presenter) {
        super(console, presenter);
    }

    public void addJob(Planning planning){
        String name = console.askString("Nom de la nouvelle tâche ?");
        String description = console.askString("Descriptif éventuel de la nouvelle tâche ?");
        int duration = console.askPosInt("Durée (en jours) de la nouvelle tâche ?");
        List<Job> priorJobs = new ArrayList<>();
        getPriorJobs(planning, priorJobs);
        planning.addJob(new Job(name, description, duration, priorJobs));
    }

    private void getPriorJobs(Planning planning, List<Job> priorJobs){
        String name = null;
        while(name == null || !name.isBlank()){
            name = console.askString("Tâche antérieure (Enter si aucune) :");
            if(!name.isBlank()) addPriorJob(planning, priorJobs, name);
        }
    }

    private void addPriorJob(Planning planning, List<Job> priorJobs, String jobName){
        Job priorJob = planning.getJobByName(jobName);
        if(priorJob == null){
            priorJob = new Job(jobName);
            planning.addJob(priorJob);
        }
        priorJobs.add(priorJob);
    }
}
