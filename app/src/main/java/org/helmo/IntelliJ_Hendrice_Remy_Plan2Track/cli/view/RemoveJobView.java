package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.RemoveJob;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.JobNotFoundException;

public class RemoveJobView extends CliView{

    private final RemoveJob controller;

    public RemoveJobView(RemoveJob controller){
        this.controller = controller;
    }

    public void removeJob(){
        String question = "Nom de la tâche à supprimer ? (Enter pour annuler)";
        String name = console.askString(question);
        while(name == null || !name.isBlank()){
            deleteJob(name);
            name = console.askString(question);
        }
    }

    private void deleteJob(String name){
        try{
            int occ = controller.findJobOccurences(name);
            confirmDelete(name, occ);
        }catch(JobNotFoundException ex){
            console.println(ex.getMessage());
        }
    }

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
