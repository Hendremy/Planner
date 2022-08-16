package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

/**
 * D�finit un chef d'�quipe.
 */
public class Technician {
    private final String firstName;
    private final String lastName;
    private final String code;

    /**
     * Initialise le pr�nom, le nom et le code du chef d'�quipe.
     * @param firstName le pr�nom
     * @param lastName le nom
     * @param code le code
     */
    public Technician(String firstName, String lastName, String code){
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = code;
    }

    /**
     * Retourne le pr�nom du chef d'�quipe.
     * @return le pr�nom du chef d'�quipe
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retourne le nom du chef d'�quipe
     * @return le nom du chef d'�quipe
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * Retourne le nom complet du chef d'�quipe.
     * @return le nom complet du chef d'�quipe
     */
    public String getFullName(){
        return String.format("%s %s",firstName, lastName);
    }

    /**
     * Retourne le code du chef d'�quipe
     * @return le code du chef d'�quipe
     */
    public String getCode(){
        return code;
    }
}
