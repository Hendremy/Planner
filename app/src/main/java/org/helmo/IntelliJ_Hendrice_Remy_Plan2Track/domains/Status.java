package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

public enum Status {
    TODO("� faire"),
    DONE("Termin�e"),
    DOING("En cours");

    private final String representation;

    Status(String representation){
        this.representation = representation;
    }

    public String getRepresentation(){
        return representation;
    }
}
