package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

public class CyclicGraphException extends PertException {
    public CyclicGraphException(){
        super("Un cycle a �t� d�tect� dans la s�quence des t�ches");
    }
}
