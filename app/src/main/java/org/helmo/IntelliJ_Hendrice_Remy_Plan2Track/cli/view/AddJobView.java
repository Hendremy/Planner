package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.AddJob;

import java.util.HashSet;
import java.util.Set;

/**
 * Définit la vue CLI d'ajout d'une tâche au planning
 */
public class AddJobView extends CliView{

    private final AddJob controller;

    /**
     * Initialise la vue avec son controleur d'ajout d'une tâche au planning.
     * @param controller le controleur d'ajout d'une tâche au planning
     */
    public AddJobView (AddJob controller){
        this.controller = controller;
    }

    /**
     * Montre la vue, demande à l'utilisateur d'entrer les informations concernant la nouvelle tâche à créer
     * puis appelle le controleur pour ajoute la tâche au planning
     */
    public void show(){
        String name = console.askString("Nom de la nouvelle tâche ?");
        String description = console.askString("Descriptif éventuel de la nouvelle tâche ?");
        int duration = console.askPosInt("Durée (en jours) de la nouvelle tâche ?");
        Iterable<String> priorJobs = addPriorJobs();
        controller.addJobToPlanning(name, description, duration, priorJobs);
    }

    /**
     * Boucle tant que l'utilisateur souhaite ajouter des tâches antérieures à la tâche en cours de création.
     * @return les tâches antérieures de la tâche en cours de création
     */
    private Iterable<String> addPriorJobs(){
        Set<String> priorJobs = new HashSet<>();
        String name = null;
        while(name == null || !name.isBlank()){
            name = console.askString("Tâche antérieure (Enter si aucune) :");
            if(!name.isBlank()) priorJobs.add(name);
        }
        return priorJobs;
    }
}
