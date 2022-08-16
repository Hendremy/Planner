package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Schedule;

/**
 * Définit l'interface de sérialisation/désérialisation d'un montage
 */
public interface PlanningSerializer {
    /**
     * Désérialise un montage en un objet de transfert de données
     * @param planningString la chaîne de caractères à désérializer
     * @return le montage en objet de transfert de données
     */
    PlanningDTO deserialize(String planningString);

    /**
     * Sérialise un programme de montage en une chaîne de caractères.
     * @param schedule le programme de montage à sérialiser
     * @return le programme sérialisé
     */
    String serialize(Schedule schedule);
}
