package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

public class CyclicPertGraphException extends Exception{
    public CyclicPertGraphException(){
        super("Un cycle a �t� d�tect� dans la s�quence des t�ches");
    }
}
