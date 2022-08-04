package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;

public interface PlanningRepository {

    void load();
    void loadPlannings();
    void loadTechnicians();
    void writePlannings(Iterable<Planning> plannings);
    Iterable<Planning> getPlannings();
    Iterable<Technician> getTechnicians();
}
