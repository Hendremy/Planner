package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

/**
 * D�finit l'interface de la vue parent de gestion de montage.
 */
public interface ManageJobsView {
    /**
     * D�finit l'action r�alis�e quand une t�che est ajout�e.
     */
    void onJobAdded();
    /**
     * D�finit l'action r�alis�e quand une t�che est supprim�e.
     */
    void onJobRemoved(String name);

    /**
     * Montre la vue d'ajout d'une t�che.
     */
    void showAddJob();

    /**
     * Montre la vue de suppresion d'une t�che.
     * @param name le nom de la t�che � supprimer
     */
    void showRemoveJob(String name);

    /**
     * Montre la vue d'assignation d'une t�che.
     * @param name la t�che � assigner
     */
    void showAssignJob(String name);
}
