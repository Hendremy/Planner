package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain.Planning;

import java.util.Collection;

public interface PlanningRepository {
    public Collection<Planning> load();
}
