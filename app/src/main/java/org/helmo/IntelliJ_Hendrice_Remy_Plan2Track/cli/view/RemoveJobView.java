package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.RemoveJob;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.JobNotFoundException;

/**
 * Définit la vue CLI de suppression d'une tâche du montage.
 */
public class RemoveJobView extends CliView{

    private final RemoveJob controller;

    /**
     * Initialise la vue avec son contrôleur de suppression de tâches.
     * @param controller le controleur de suppression de tâches
     */
    public RemoveJobView(RemoveJob controller){
        this.controller = controller;
    }

    /**
     * Demande à l'utilisateur le nom de la tâche qu'il souhaite supprimer tant qu'il veut en supprimer.
     */
    public void show(){
        String question = "Nom de la tâche à supprimer ? (Enter pour annuler)";
        String name = console.askString(question);
        while(name == null || !name.isBlank()){
            deleteJob(name);
            name = console.askString(question);
        }
    }

    /**
     * Supprime la tâche choisie par l'utilisateur si elle est trouvée.
     * @param name le nom de la tâche choisie
     */
    private void deleteJob(String name){
        try{
            int occ = controller.findJobOccurences(name);
            confirmDelete(name, occ);
        }catch(JobNotFoundException ex){
            console.println(ex.getMessage());
        }
    }

    /**
     * Affiche le nombre de fois que la tâche à supprimer est antérieure à d'autres
     * et demande à l'utilisateur s'il confirme la suppression
     * @param name le nom de la tâche à supprimer
     * @param occ le nombre de fois que la tâche à supprimer est antérieure à d'autres
     */
    private void confirmDelete(String name, int occ){
        console.println(String.format("%s est requise pour %d autre(s) tâche(s)", name, occ));
        if(console.askYesNo("Voulez-vous vraiment la supprimer ?")){
            try{
                controller.removeJob(name);
            }catch(JobNotFoundException ex){
                console.println(ex.getMessage());
            }
        }
    }
}
