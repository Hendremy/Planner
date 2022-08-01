package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain;

public class Technician {
    private final String firstName;
    private final String lastName;
    private final String code;

    public Technician(String firstName, String lastName, String code){
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getCode(){
        return code;
    }
}
