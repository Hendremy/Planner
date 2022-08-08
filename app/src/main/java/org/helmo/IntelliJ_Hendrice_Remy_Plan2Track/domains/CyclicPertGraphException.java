package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

public class CyclicPertGraphException extends Exception{
    public CyclicPertGraphException(){
        super("Un cycle a été détecté dans la séquence des tâches");
    }
}
