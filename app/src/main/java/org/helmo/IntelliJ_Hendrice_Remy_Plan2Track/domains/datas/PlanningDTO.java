package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PlanningDTO {
    private final String name;
    private final Set<JobDTO> jobs;

    public PlanningDTO(String name, Collection<JobDTO> jobs){
        this.name = name;
        this.jobs = new HashSet<>(jobs);
    }
}
