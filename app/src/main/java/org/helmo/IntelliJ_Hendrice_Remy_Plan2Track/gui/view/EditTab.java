package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.scene.control.Tab;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.EditPlanning;


public class EditTab {

    private Tab tab = new Tab("Modification");
    {
        tab.setClosable(false);
    }

    public EditTab(EditPlanning controller){
        EditView manageView = new EditView(controller);
        tab.setContent(manageView.getParent());
    }

    public Tab getTab() {
        return tab;
    }


}
