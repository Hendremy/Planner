package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;


public class ManageTab {

    private Tab tab = new Tab("Modification");
    {
        tab.setClosable(false);
    }

    public ManageTab(ManagePlanning controller){
        ManageView manageView = new ManageView(controller);
        tab.setContent(manageView.getParent());
    }

    public Tab getTab() {
        return tab;
    }


}
