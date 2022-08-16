package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;


import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.EditPlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.PlanSchedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;

import java.io.IOException;

/**
 * Définit la vue principale de l'application. Gère la création du montage.
 */
public class MainView extends CliView {
    private final int exitNum = 0;
    private final ManagePlanning mainController;

    /**
     * Initialise la vue avec son contrôleur de gestion de montage.
     * @param mainController le contrôleur de gestion de montage
     */
    public MainView (ManagePlanning mainController){
        this.mainController = mainController;
    }

    /**
     * Démarre la vue et boucle tant que l'utilisateur ne souhaite pas quitter l'application.
     */
    public void start(){
        sayHello();
        loop();
        close();
    }

    /**
     * Affiche le montage en cours s'il y en a un et le menu principal de l'application tant que
     * l'utilisateur ne souhaite pas quitter l'application
     */
    private void loop(){
        int choice = -1;
        while(choice != exitNum){
            displayPlanning();
            choice = askMenu();
            handleChoice(choice);
        }
    }

    /**
     * Affiche le montage en cours s'il y en a un
     */
    private void displayPlanning(){
        if(getPlanning() != null){
            console.println(presenter.presentPlanning(getPlanning()));
        }
    }

    /**
     * Affiche le menu principal de l'appplication et demande le choix d'action de l'utilisateur.
     * @return le choix d'action de l'utilisateur
     */
    private int askMenu(){
        return console.askPosInt(
                "1. Créer un montage"
                        + "\n2. Modifier le montage"
                        + "\n0. Quitter"
        );
    }

    /**
     * Gère le choix d'action de l'utilisateur.
     * @param choice le choix d'action de l'utilisateur.
     */
    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                createPlanning();
                break;
            case 2:
                editPlanning();
                break;
            case exitNum:
                break;
            default:
                console.print("Choix invalide");
                break;
        }
    }

    /**
     * Crée un nouveau montage, si il y en a déjà un en cours, demande à l'utilisateur s'il veut l'écraser.
     */
    private void createPlanning(){
        if((getPlanning() != null && wantsOverride()) || getPlanning() == null){
            createNewPlanning();
        }
    }

    /**
     * Demande à l'utilisateur s'il souhaite écraser le montage en cours.
     * @return vrai si l'utilisateur souhaite écraser le montage en cours, sinon faux
     */
    private boolean wantsOverride(){
        return console.askYesNo("Un planning est en cours de création, voulez-vous l'écraser ?");
    }

    /**
     * Demande à l'utilisateur le nom du nouveau montage et le crée si le nom n'est pas vide.
     */
    private void createNewPlanning(){
        String name = console.askString("Nom du montage ?");
        if(!name.isBlank()){
            mainController.createPlanning(name);
        }
    }

    /**
     * Délègue la modification du montage à la vue de modification si un montage est en cours.
     */
    private void editPlanning(){
        if(mainController.getPlanning() != null){
            EditPlanning editController = mainController.getEditPlanningController();
            PlanSchedule planController = mainController.getPlanScheduleController();
            new EditView(editController, planController).loop();
        }else{
            console.println("Erreur - Aucun montage en cours");
        }
    }

    /**
     * Retourne le planning en cours de création s'il y en a un.
     * @return le planning en cours de création
     */
    private Planning getPlanning() {
        return mainController.getPlanning();
    }

    /**
     * Affiche le message de démarrage de l'application
     */
    private void sayHello(){
        console.println("IN-B2-UE11-Java : Planner\n" +
                "=======================\n");
    }

    /**
     * Affiche le message de fermeture de l'application et ferme la console.
     */
    private void close(){
        console.println("Exiting ... \n Goodbye !");
        try{
            console.close();
        }catch(IOException ex) {
            System.out.println("Console failed to close");
        }
    }
}
