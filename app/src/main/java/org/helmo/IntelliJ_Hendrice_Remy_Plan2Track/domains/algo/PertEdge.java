package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.CyclicPertGraphException;

public class PertEdge {
    private final PertTask task;
    private final int origin;
    private int target;

    public PertEdge(int origin, int target, PertTask task) throws CyclicPertGraphException {
        if(origin < target){
            this.task = task;
            this.origin = origin;
            this.target = target;
        }else{
            throw new CyclicPertGraphException();
        }
    }

    public int getOrigin(){return origin;}

    public int getTarget(){
        return target;
    }

    public void setTarget(int target){
        this.target = target;
    }

}
