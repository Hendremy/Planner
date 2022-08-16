package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;

import java.util.Collection;

/**
 * D�finit l'interface de s�rialiseur/d�s�rialiseur de chef d'�quipe
 */
public interface UserParser {
    /**
     * Reconstruit les chefs d'�quipe � partir d'une cha�ne de caract�res
     * @param usersString la cha�ne de caract�res
     * @return les chefs d'�quipe
     */
    Collection<Technician> parseArray(String usersString);
}
