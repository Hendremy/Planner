package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

public class JobNotFoundException extends Exception{
        public JobNotFoundException(){
            super("Ce nom de tâche n'existe pas");
        }
    }