package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

/**
 * Définit l'interface de la vue parent de gestion de montage.
 */
public interface ManageJobsView {
    /**
     * Définit l'action réalisée quand une tâche est ajoutée.
     */
    void onJobAdded();
    /**
     * Définit l'action réalisée quand une tâche est supprimée.
     */
    void onJobRemoved(String name);

    /**
     * Montre la vue d'ajout d'une tâche.
     */
    void showAddJob();

    /**
     * Montre la vue de suppresion d'une tâche.
     * @param name le nom de la tâche à supprimer
     */
    void showRemoveJob(String name);

    /**
     * Montre la vue d'assignation d'une tâche.
     * @param name la tâche à assigner
     */
    void showAssignJob(String name);
}
