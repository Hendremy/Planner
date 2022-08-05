package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

public interface AddJob {
    void createJob(String name, String description, int duration);
    void addPriorJob(String name);
    void addJobToPlanning();
}
