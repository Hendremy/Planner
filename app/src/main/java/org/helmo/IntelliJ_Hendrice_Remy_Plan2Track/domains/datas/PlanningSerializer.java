package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Schedule;

/**
 * D�finit l'interface de s�rialisation/d�s�rialisation d'un montage
 */
public interface PlanningSerializer {
    /**
     * D�s�rialise un montage en un objet de transfert de donn�es
     * @param planningString la cha�ne de caract�res � d�s�rializer
     * @return le montage en objet de transfert de donn�es
     */
    PlanningDTO deserialize(String planningString);

    /**
     * S�rialise un programme de montage en une cha�ne de caract�res.
     * @param schedule le programme de montage � s�rialiser
     * @return le programme s�rialis�
     */
    String serialize(Schedule schedule);
}
