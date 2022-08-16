package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.Set;

/**
 * Définit une tâche factice.
 */
public class FakeTask implements PertTask{

    private final PertTask originalTask;

    /**
     * Initialise la tâche factice à partir de la tâche originelle
     * @param task la tâche originelle
     */
    public FakeTask(PertTask task){
        this.originalTask = task;
    }

    @Override
    public Set<PertTask> getPredecessors() {
        return originalTask.getPredecessors();
    }

    @Override
    public String getName() {
        return String.format("%s (fake)", originalTask.getName());
    }

    @Override
    public boolean hasPredecessor(PertTask predecessor) {
        return originalTask.hasPredecessor(predecessor);
    }

    @Override
    public boolean hasPredecessors() {
        return originalTask.hasPredecessors();
    }

    @Override
    public int getDuration() {
        return 0;
    }
}
