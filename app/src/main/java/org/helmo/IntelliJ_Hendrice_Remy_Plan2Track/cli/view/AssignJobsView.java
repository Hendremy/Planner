package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.AssignJobs;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view.ErrorMessageWindow;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.TechnicianViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Définit la vue d'assignation des tâches aux chefs d'équipes.
 */
public class AssignJobsView extends CliView{

    private final AssignJobs controller;
    private final List<TechnicianViewModel> techs;

    /**
     * Initialise la vue avec son contrôleur d'assignation des tâches et les chefs d'équipes disponibles.
     * @param controller le contrôleur d'assignation des tâches aux chefs d'équipes
     * @throws PlanningRepositoryException survient si il y a eu une erreur de chargement des chefs d'équipe dans le PlanningRepository
     */
    public AssignJobsView(AssignJobs controller) throws PlanningRepositoryException {
        this.controller = controller;
        this.techs = new ArrayList<>(controller.getTechniciansViewModels());
    }

    /**
     * Montre la vue d'assignation des tâches tant que l'utilisateur souhaite assigner les tâches
     */
    public void show(){
        Iterable<Job> jobs = controller.getJobs();
        if(jobs.iterator().hasNext()){
            String jobName = "blank";
            while(!jobName.isBlank()){
                displayJobs(jobs);
                jobName = console.askString("Nom de la tâche à assigner ou Enter pour arrêter ?");
                tryAssignJob(jobName);
            }
        }else{
            console.println("Aucune tâche à assigner");
        }
    }

    /**
     * Affiche les tâches à assigner et leur chefs assignés si elles en ont un.
     * @param jobs les tâches à assigner
     */
    private void displayJobs(Iterable<Job> jobs){
        console.println(presenter.formatAssignJobs(jobs));
    }

    /**
     * Tente d'assigner une tâche à un chef d'équipe.
     * @param jobName le nom de la tâche à assigner
     */
    private void tryAssignJob(String jobName){
        try{
            assignJob(jobName);
        }catch(PlanningRepositoryException ex){
            new ErrorMessageWindow(ex.getMessage());
        }
    }

    /**
     * Retrouve une tâche par son nom et tente de l'assigner.
     * @param jobName le nom de la tâche à assigner
     * @throws PlanningRepositoryException survient si il y a eu une erreur de chargement des chefs d'équipe dans le PlanningRepository
     */
    private void assignJob(String jobName) throws PlanningRepositoryException{
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

    /**
     * Affiche la liste des chefs d'équipe assignables et demande à l'utilisateur de choisir un chef d'équipe.
     * @return le numéro du chef d'équipe choisi dans la liste
     */
    private int chooseTech(){
        displayTechnicians(techs);
        return findTechnician(techs);
    }

    /**
     * Affiche les chefs d'équipe assignables
     * @param technicians les chefs d'équipes assignables
      */
    private void displayTechnicians(List<TechnicianViewModel> technicians){
        console.println("Chefs d'équipe :");
        console.println(presenter.formatTechnicians(technicians));
    }

    /**
     * Demande à l'utilisateur de choisir un chef d'équipe.
     * @param technicians la liste des chefs d'équipe
     * @return le numéro du chef d'équipe choisi dans la liste
     */
    private int findTechnician(List<TechnicianViewModel> technicians){
        int choice = -1;
        while(!(0 <= choice && choice <= technicians.size())){
            choice = console.askPosInt("Numéro choisi (ou 0 pour annuler) ?");
        }
        return choice;
    }
}
