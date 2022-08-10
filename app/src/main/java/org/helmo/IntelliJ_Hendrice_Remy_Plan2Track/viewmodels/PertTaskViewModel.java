package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertTask;

public class PertTaskViewModel {

    private final String name;
    private final int duration;

    public PertTaskViewModel (PertTask task){
        this.name = task.getName();
        this.duration = task.getDuration();
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return String.format("%s - %d j", name, duration);
    }
}
