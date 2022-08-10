package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.scene.control.Tab;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.EditPlanning;


public class EditTab extends Tab{

    public EditTab(EditPlanning controller) {
        EditView manageView = new EditView(controller);
        setContent(manageView.getParent());
        setClosable(false);
        setText("Modification");
    }
}
