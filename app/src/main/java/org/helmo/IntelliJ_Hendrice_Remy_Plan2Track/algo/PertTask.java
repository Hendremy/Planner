package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.algo;

import java.util.Set;

public interface PertTask {
    Set<PertTask> getPredecessors();

    String getName();

    boolean hasPredecessor(PertTask predecessor);

    boolean hasPredecessors();

    int getDuration();
}
