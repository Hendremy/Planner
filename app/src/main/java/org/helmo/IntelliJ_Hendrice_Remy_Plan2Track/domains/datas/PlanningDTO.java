package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Définit l'objet de transfert de données d'un montage.
 */
public class PlanningDTO {
    private final String name;
    private final Set<JobDTO> jobs;

    /**
     * Initialise le nom et les avancements des tâches du montage.
     * @param name le nom
     * @param jobs les avancements des tâches
     */
    public PlanningDTO(String name, Collection<JobDTO> jobs){
        this.name = name;
        this.jobs = new HashSet<>(jobs);
    }

    /**
     * Retourne le nom du montage.
     * @return le nom du montage
     */
    public String getName(){
        return name;
    }

    /**
     * Retourne les avancements des tâches du montage.
     * @return les avancements des tâches du montage
     */
    public Collection<JobDTO> getJobs(){
        return new HashSet<>(jobs);
    }
}
