package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

public interface ManageJobsView {
    void jobAdded();
    void jobRemoved(String name);
    void showAddJob();
    void showRemoveJob(String name);
    void showAssignJob(String name);
}
