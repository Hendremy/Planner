package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningProgress;

/**
 * Définit l'interface de convertion d'objet de transfert de données.
 */
public interface ConvertPlanningDTO {
    PlanningProgress toPlanningProgress(PlanningDTO planningDTO);
}
