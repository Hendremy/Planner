package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
