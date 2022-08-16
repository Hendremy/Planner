package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.*;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view.ErrorMessageWindow;

/**
 * Définit la vue de modification du montage et de plannification du montage.
 */
public class EditView extends CliView{

    private final EditPlanning editController;
    private final PlanSchedule planController;
    private final int exitNum = 0;

    /**
     * Initialise la vue avec son controleur de modification du planning et son contrôleur de plannification de montage.
     * @param editController le controleur de modification du planning
     * @param planController le controleur de plannification du montage
     */
    public EditView (EditPlanning editController, PlanSchedule planController){
        this.editController = editController;
        this.planController = planController;
    }

    /**
     * Affiche le menu d'édition ou de plannification du montage tant que l'utilisateur ne souhaite pas quitter le menu.
     */
    public void loop(){
        int choice = -1;
        while(choice != exitNum){
            displayPlanning();
            choice = askMenu();
            handleChoice(choice);
        }
    }

    /**
     * Affiche le planning avec ses tâches.
     */
    private void displayPlanning(){
        var planning = getPlanning();
        if(planning != null){
            console.println(presenter.presentPlanning(planning));
        }
    }

    /**
     * Affiche le menu d'édition ou de plannification du montage et attend le choix d'action de l'utilisateur.
     * @return la réponse de l'utilisateur
     */
    private int askMenu(){
        return console.askPosInt(
                "\n1. Modifier le nom du montage"
                        + "\n2. Ajouter une nouvelle tâche"
                        + "\n3. Supprimer une tâche existante"
                        + "\n4. Assigner des tâches"
                        + "\n5. Trouver les tâches critiques"
                        + "\n6. Déterminer la date de fin au plus tôt"
                        + "\n0. Arrêter la modification"
        );
    }

    /**
     * Gère le choix d'action de l'utilisateur.
     * @param choice le choix d'action de l'utiliisateur.
     */
    private void handleChoice(int choice){
        switch(choice){
            case 1:
                editName();
                break;
            case 2:
                addJob();
                break;
            case 3:
                removeJob();
                break;
            case 4:
                assignJobs();
                break;
            case 5:
                findCriticalJobs();
                break;
            case 6:
                findEarliestEndTime();
            case exitNum:
                break;
            default:
                console.println("Choix invalide");
                break;
        }
    }

    /**
     * Retourne le planning en cours de modification.
     * @return le planning en cours de modification
     */
    private Planning getPlanning(){
        return editController.getPlanning();
    }

    /**
     * Demande à l'utilisateur d'encoder un nouveau nom pour le montage et le modifie si le nom entré n'est pas vide.
     */
    public void editName(){
        String name = console.askString("Nom du montage ?");
        if(name != null && !name.isBlank() && !name.isEmpty()){
            editController.editName(name);
        }
    }

    /**
     * Délègue l'ajout d'une tâche au montage à la vue d'ajout.
     */
    private void addJob(){
        AddJob addJobController = editController.getAddJobController();
        new AddJobView(addJobController).show();
    }

    /**
     * Délègue la suppression d'une tâche du montage à la vue de suppression.
     */
    private void removeJob(){
        RemoveJob removeJobController = editController.getRemoveJobController();
        new RemoveJobView(removeJobController).show();
    }

    /**
     * Délègue l'assignation des tâches du montage à la vue d'assignation.
     */
    private void assignJobs() {
        AssignJobs assignJobsController = editController.getAssignJobsController();
        try{
            new AssignJobsView(assignJobsController).show();
        }catch(PlanningRepositoryException ex){
            new ErrorMessageWindow(ex.getMessage());
        }
    }

    /**
     * Délègue la recherche du chemin critique à la vue de chemin critique.
     */
    private void findCriticalJobs() {
        new CriticalJobsView(planController).show();
    }

    /**
     * Délègue la recherche de la date de fin au plus tôt à la vue de date de fin au plus tôt.
     */
    private void findEarliestEndTime(){
        new EarliestEndTimeView(planController).show();
    }

}
