package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;

/**
 * Définit le modèle de vue d'un chef d'équipe.
 */
public class TechnicianViewModel {

    private final String firstName;
    private final String lastName;
    private final String code;

    /**
     * Initialise le modèle de vue à parti d'un chef d'équipe.
     * @param technician le chef d'équipe
     */
    public TechnicianViewModel(Technician technician){
        this.firstName = technician.getFirstName();
        this.lastName = technician.getLastName();
        this.code = technician.getCode();
    }

    /**
     * Retourne le dode du chef d'équipe.
     * @return le code du chef d'équipe
     */
    public String getCode(){
        return code;
    }

    /**
     * Retourne le nom complet du chef d'équipe
     * @return le nom complet du chef d'équipe
     */
    public String getFullName(){
        return String.format("%s %s", firstName, lastName);
    }

    /**
     * Retourne la représentation en chaine de caractères du modèle de vue.
     * @return la représentation en chaine de caractères du modèle de vue
     */
    @Override
    public String toString(){
        return String.format("%s %s - %s", firstName, lastName, code);
    }
}
