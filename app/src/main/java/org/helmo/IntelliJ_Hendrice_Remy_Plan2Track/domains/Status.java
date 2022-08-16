package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

/**
 * D�finit le status d'une t�che plannifi�e
 */
public enum Status {
    TODO("� faire"),
    DONE("Termin�e"),
    DOING("En cours");

    private final String representation;

    /**
     * Initialise la repr�sentation en chaine de caract�re du statut
     * @param representation la repr�sentation du statut
     */
    Status(String representation){
        this.representation = representation;
    }

    /**
     * Retourne la repr�sentation en cha�ne de caract�re du statut.
     * @return la repr�sentation en cha�ne de caract�re du statut
     */
    public String getRepresentation(){
        return representation;
    }
}
