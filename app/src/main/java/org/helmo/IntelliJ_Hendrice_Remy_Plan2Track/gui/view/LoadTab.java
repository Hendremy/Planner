package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.LoadPlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;

public class LoadTab {

    private Tab tab = new Tab("Avancement");
    {
        tab.setClosable(false);
    }

    public LoadTab(LoadPlanning controller){
        LoadView loadView = new LoadView(controller);
        tab.setContent(loadView.getParent());
    }

    public Tab getTab() {
        return tab;
    }
}
