package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * D�finit l'objet de transfert de donn�es d'un montage.
 */
public class PlanningDTO {
    private final String name;
    private final Set<JobDTO> jobs;

    /**
     * Initialise le nom et les avancements des t�ches du montage.
     * @param name le nom
     * @param jobs les avancements des t�ches
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
     * Retourne les avancements des t�ches du montage.
     * @return les avancements des t�ches du montage
     */
    public Collection<JobDTO> getJobs(){
        return new HashSet<>(jobs);
    }
}
