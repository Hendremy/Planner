package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainWindow {

    private ManageTab manageWindow;
    private LoadTab loadWindow;

    private TabPane root = new TabPane();
    {

    }

    public void addTab(Tab tab){
        root.getTabs().add(tab);
    }

    public Parent getParent(){
        return root;
    }
}
