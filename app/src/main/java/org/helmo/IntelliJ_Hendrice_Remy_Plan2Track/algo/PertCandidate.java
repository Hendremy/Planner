package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.algo;

public interface PertCandidate<T> {
    Iterable<T> getPredecessors();

    void addPredecessor(T predecessor);

    void removePredecessor(T predecessor);

    boolean hasPredecessor(T predecessor);

    boolean hasPredecessors();
}
