package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

public interface AddJob {
    void addJobToPlanning(String name, String description, int duration, Iterable<String> priorJobs);
}
