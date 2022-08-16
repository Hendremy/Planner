package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;

/**
 * D�finit le mod�le de vue d'un chef d'�quipe.
 */
public class TechnicianViewModel {

    private final String firstName;
    private final String lastName;
    private final String code;

    /**
     * Initialise le mod�le de vue � parti d'un chef d'�quipe.
     * @param technician le chef d'�quipe
     */
    public TechnicianViewModel(Technician technician){
        this.firstName = technician.getFirstName();
        this.lastName = technician.getLastName();
        this.code = technician.getCode();
    }

    /**
     * Retourne le dode du chef d'�quipe.
     * @return le code du chef d'�quipe
     */
    public String getCode(){
        return code;
    }

    /**
     * Retourne le nom complet du chef d'�quipe
     * @return le nom complet du chef d'�quipe
     */
    public String getFullName(){
        return String.format("%s %s", firstName, lastName);
    }

    /**
     * Retourne la repr�sentation en chaine de caract�res du mod�le de vue.
     * @return la repr�sentation en chaine de caract�res du mod�le de vue
     */
    @Override
    public String toString(){
        return String.format("%s %s - %s", firstName, lastName, code);
    }
}
