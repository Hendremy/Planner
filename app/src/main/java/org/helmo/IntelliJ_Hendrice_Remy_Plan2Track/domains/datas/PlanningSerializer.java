package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Schedule;

public interface PlanningSerializer {
    PlanningDTO deserialize(String planningJson);
    String serialize(Schedule schedule);
}
