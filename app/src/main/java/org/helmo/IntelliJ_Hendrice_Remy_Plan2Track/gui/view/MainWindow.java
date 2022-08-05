package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.LoadPlanning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers.ManagePlanning;

public class MainWindow {

    private ManageWindow manageWindow;
    private LoadWindow loadWindow;

    public MainWindow(ManagePlanning manageControl, LoadPlanning loadControl){
        this.manageWindow = new ManageWindow(manageControl);
        this.loadWindow = new LoadWindow(loadControl);
    }

    private Tab manageTab = new Tab();
    {
        manageTab.setContent(manageWindow.getParent());
    }

    private Tab loadTab = new Tab();
    {
        loadTab.setContent(manageWindow.getParent());
    }


    private TabPane root = new TabPane();
    {
        root.getTabs().add(manageTab);
        root.getTabs().add(loadTab);
    }

    public Parent getParent(){
        return root;
    }
}
