package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

/**
 * Définit un chef d'équipe.
 */
public class Technician {
    private final String firstName;
    private final String lastName;
    private final String code;

    /**
     * Initialise le prénom, le nom et le code du chef d'équipe.
     * @param firstName le prénom
     * @param lastName le nom
     * @param code le code
     */
    public Technician(String firstName, String lastName, String code){
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = code;
    }

    /**
     * Retourne le prénom du chef d'équipe.
     * @return le prénom du chef d'équipe
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retourne le nom du chef d'équipe
     * @return le nom du chef d'équipe
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * Retourne le nom complet du chef d'équipe.
     * @return le nom complet du chef d'équipe
     */
    public String getFullName(){
        return String.format("%s %s",firstName, lastName);
    }

    /**
     * Retourne le code du chef d'équipe
     * @return le code du chef d'équipe
     */
    public String getCode(){
        return code;
    }
}
