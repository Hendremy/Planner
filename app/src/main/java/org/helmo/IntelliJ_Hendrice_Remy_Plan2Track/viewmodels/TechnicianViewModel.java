package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;

public class TechnicianViewModel {

    private final String firstName;
    private final String lastName;
    private final String code;

    public TechnicianViewModel(Technician technician){
        this.firstName = technician.getFirstName();
        this.lastName = technician.getLastName();
        this.code = technician.getCode();
    }

    public String getCode(){
        return code;
    }

    public String getFullName(){
        return String.format("%s %s", firstName, lastName);
    }

    @Override
    public String toString(){
        return String.format("%s %s - %s", firstName, lastName, code);
    }
}
