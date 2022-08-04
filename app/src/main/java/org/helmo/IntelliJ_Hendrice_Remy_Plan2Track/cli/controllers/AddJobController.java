package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Console;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Presenter;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;

public class AddJobController extends Controller{
    public AddJobController(Console console, Presenter presenter, PlanningRepository repository) {
        super(console, presenter, repository);
    }

    public void addJob(Planning planning){
        String name = console.askString("Nom de la nouvelle tâche ?");
        String description = console.askString("Descriptif éventuel de la nouvelle tâche ?");
        int duration = console.askPosInt("Durée (en jours) de la nouvelle tâche ?");
        Job job = new Job(name, description, duration);
        addPriorJobs(planning, job);
        planning.addJob(job);
    }

    private void addPriorJobs(Planning planning, Job job){
        String name = null;
        while(name == null || !name.isBlank()){
            name = console.askString("Tâche antérieure (Enter si aucune) :");
            if(!name.isBlank()) addPriorJob(planning, job, name);
        }
    }

    private void addPriorJob(Planning planning, Job job, String jobName){
        Job priorJob = planning.getJobByName(jobName);
        if(priorJob == null){
            priorJob = new Job(jobName);
            planning.addJob(priorJob);
        }
        job.addPredecessor(priorJob);
    }
}
