package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

/**
 * Définit l'exception qui est lancée lorsqu'une tâche n'est pas trouvée.
 */
public class JobNotFoundException extends Exception{
    /**
     * Initialise le message d'exception.
     */
        public JobNotFoundException(){
            super("Ce nom de tâche n'existe pas");
        }
    }