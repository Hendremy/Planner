package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Console;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.Presenter;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Planning;

public class EditController extends Controller{

    private final int exitNum = 0;
    private final EditNameController editNameController = new EditNameController(console, presenter, repository);
    private final AddJobController addJobController = new AddJobController(console, presenter, repository);
    private final RemoveJobController removeJobController = new RemoveJobController(console, presenter, repository);
    private final AssignJobsController assignJobsController = new AssignJobsController(console, presenter, repository);
    private Planning planning;

    public EditController(Console console, Presenter presenter, PlanningRepository repository) {
        super(console, presenter, repository);
    }

    public void edit(Planning planning){
        if(planning != null){
            this.planning = planning;
            loop();
        }else{
            console.println("Erreur - Aucun montage en cours");
        }
    }

    private void loop(){
        int choice = -1;
        while(choice != exitNum){
            displayPlanning();
            choice = askMenu();
            handleChoice(choice);
        }
    }

    private void displayPlanning(){
        if(planning != null){
            console.println(presenter.presentPlanning(planning));
        }
    }

    private int askMenu(){
        return console.askPosInt(
                    "1. Modifier le nom du montage"
                + "\n2. Ajouter une nouvelle tâche"
                + "\n3. Supprimer une tâche existante"
                + "\n4. Assigner des tâches"
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
            case exitNum:
                break;
            default:
                console.println("Choix invalide");
                break;
        }
    }

    private void editName(){
        editNameController.editName(planning);
    }

    private void addJob(){
        addJobController.addJob(planning);
    }

    private void removeJob(){
        removeJobController.removeJob(planning);
    }

    private void assignJobs() {assignJobsController.assignJobs(planning);}
}
