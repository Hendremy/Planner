package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.*;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;


public class EditView extends CliView{

    private final EditPlanning editController;
    private final PlanSchedule planController;
    private final int exitNum = 0;

    public EditView (EditPlanning editController, PlanSchedule planController){
        this.editController = editController;
        this.planController = planController;
    }

    public void loop(){
        int choice = -1;
        while(choice != exitNum){
            displayPlanning();
            choice = askMenu();
            handleChoice(choice);
        }
    }

    private void displayPlanning(){
        var planning = getPlanning();
        if(planning != null){
            console.println(presenter.presentPlanning(planning));
        }
    }

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

    private Planning getPlanning(){
        return editController.getPlanning();
    }

    public void editName(){
        String name = console.askString("Nom du montage ?");
        if(name != null && !name.isBlank() && !name.isEmpty()){
            editController.editName(name);
        }
    }

    private void addJob(){
        AddJob addJobController = editController.getAddJobController();
        new AddJobView(addJobController).show();
    }

    private void removeJob(){
        RemoveJob removeJobController = editController.getRemoveJobController();
        new RemoveJobView(removeJobController).show();
    }

    private void assignJobs() {
        AssignJobs assignJobsController = editController.getAssignJobsController();
        new AssignJobsView(assignJobsController).show();
    }

    private void findCriticalJobs() {
        new CriticalJobsView(planController).show();
    }

    private void findEarliestEndTime(){
        new EarliestEndTimeView(planController).show();
    }

}
