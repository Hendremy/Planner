package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

public class PertEdge {
    private final PertTask task;
    private final int origin;
    private int target;

    private int freeMargin;
    private int totalMargin;

    public PertEdge(int origin, int target, PertTask task) throws CyclicGraphException {
        if(origin < target || task.getDuration() == 0){
            this.task = task;
            this.origin = origin;
            this.target = target;
            freeMargin = -1;
            totalMargin = -1;
        }else{
            throw new CyclicGraphException();
        }
    }

    public int getOrigin(){return origin;}

    public int getTarget(){
        return target;
    }

    public void setTarget(int target){
        this.target = target;
    }

    public int getDuration(){return task.getDuration();}

    public PertTask getTask(){
        return task;
    }

    public int getFreeMargin(){
        return freeMargin;
    }

    public void setFreeMargin(int freeMargin){
        this.freeMargin = freeMargin;
    }

    public int getTotalMargin(){
        return totalMargin;
    }

    public void setTotalMargin(int totalMargin){
        this.totalMargin = totalMargin;
    }
}
