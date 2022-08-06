package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.gui.view;

public interface ManageJobsView {
    void addJob(String name, String description, int duration, Iterable<String> priorJobs);
    void showAddJob();
    void removeJob(String name);
    void assignJob(String name);
}
