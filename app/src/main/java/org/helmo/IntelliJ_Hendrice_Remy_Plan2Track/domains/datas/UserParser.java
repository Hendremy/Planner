package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;

import java.util.Collection;

/**
 * Définit l'interface de sérialiseur/désérialiseur de chef d'équipe
 */
public interface UserParser {
    /**
     * Reconstruit les chefs d'équipe à partir d'une chaîne de caractères
     * @param usersString la chaîne de caractères
     * @return les chefs d'équipe
     */
    Collection<Technician> parseArray(String usersString);
}
