package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Technician;

public interface PlanningRepository {

    void load();
    void loadPlannings();
    void loadTechnicians();
    void writePlannings(Iterable<Planning> plannings);
    Iterable<Planning> getPlannings();
    Iterable<Technician> getTechnicians();
}
