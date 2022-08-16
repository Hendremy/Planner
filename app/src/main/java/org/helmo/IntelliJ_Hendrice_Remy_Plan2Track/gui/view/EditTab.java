package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.scene.control.Tab;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.EditPlanning;

/**
 * Définit l'onglet JavaFX de modification du montage.
 */
public class EditTab extends Tab{

    /**
     * Initialise son contenu et le controleur de modification de montage
     * @param controller le controleur de modification de montage
     */
    public EditTab(EditPlanning controller) {
        EditView manageView = new EditView(controller);
        setContent(manageView.getParent());
        setClosable(false);
        setText("Modification");
    }
}
