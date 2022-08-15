package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import java.time.LocalDate;

public class JobDTO {
    private final String name;
    private final String description;
    private final String technician;
    private final LocalDate expStart;
    private final LocalDate expEnd;
    private final LocalDate actStart;
    private final LocalDate actEnd;

    public JobDTO(String name, String description, String technician,
                  LocalDate expStart, LocalDate expEnd,
                  LocalDate actStart, LocalDate actEnd){
        this.name = name;
        this.description = description;
        this.technician = technician;
        this.expStart = expStart;
        this.expEnd = expEnd;
        this.actStart = actStart;
        this.actEnd = actEnd;
    }

}
