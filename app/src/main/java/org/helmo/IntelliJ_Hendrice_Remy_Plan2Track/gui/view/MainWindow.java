package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.LoadPlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;

public class MainWindow {

    private ManageWindow manageWindow;
    private LoadWindow loadWindow;

    private Tab manageTab = new Tab();

    private Tab loadTab = new Tab();

    public MainWindow(ManagePlanning managePlanning, LoadPlanning loadPlanning){
        manageWindow = new ManageWindow(managePlanning);
        loadWindow = new LoadWindow(loadPlanning);
    }

    private TabPane root = new TabPane();
    {
        manageTab.setContent(manageWindow.getParent());
        loadTab.setContent(loadWindow.getParent());
        root.getTabs().add(manageTab);
        root.getTabs().add(loadTab);
    }

    public Parent getParent(){
        return root;
    }
}
