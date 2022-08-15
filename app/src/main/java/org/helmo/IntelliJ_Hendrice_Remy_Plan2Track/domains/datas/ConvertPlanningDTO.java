package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningProgress;

public interface ConvertPlanningDTO {
    PlanningProgress toPlanningProgress(PlanningDTO planningDTO);
}
