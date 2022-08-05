package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.JobNotFoundException;

public interface RemoveJob {

    int findJobOccurences(String name) throws JobNotFoundException;

    void removeJob(String name) throws JobNotFoundException;
}
