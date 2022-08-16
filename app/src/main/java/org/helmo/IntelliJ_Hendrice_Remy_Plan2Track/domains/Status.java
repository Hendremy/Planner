package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

/**
 * Définit le status d'une tâche plannifiée
 */
public enum Status {
    TODO("À faire"),
    DONE("Terminée"),
    DOING("En cours");

    private final String representation;

    /**
     * Initialise la représentation en chaine de caractère du statut
     * @param representation la représentation du statut
     */
    Status(String representation){
        this.representation = representation;
    }

    /**
     * Retourne la représentation en chaîne de caractère du statut.
     * @return la représentation en chaîne de caractère du statut
     */
    public String getRepresentation(){
        return representation;
    }
}
