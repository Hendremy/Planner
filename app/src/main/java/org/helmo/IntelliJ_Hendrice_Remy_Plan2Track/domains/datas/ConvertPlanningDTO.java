package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningProgress;

/**
 * D�finit l'interface de convertion d'objet de transfert de donn�es.
 */
public interface ConvertPlanningDTO {
    PlanningProgress toPlanningProgress(PlanningDTO planningDTO);
}
