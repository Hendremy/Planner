package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * D�finit le s�rialiseur de chef d'�quipe JSON en objet m�tier.
 */
public class JSONUserParser implements UserParser {

    /**
     * Reconstruit les chefs d'�quipes JSON en chefs d'�quipe m�tiers.
     * @param usersJson les chefs d'�quipes JSON
     * @return les chefs d'�quipes m�tiers
     */
    @Override
    public Collection<Technician> parseArray(String usersJson) {
        Set<Technician> technicianSet = new HashSet<>();
        JSONArray usersArray = new JSONArray(usersJson);
        for(Object obj : usersArray){
            JSONObject user = (JSONObject) obj;
            Technician tech = readJsonUser(user);
            technicianSet.add(tech);
        }
        return technicianSet;
    }

    /**
     * Convertir un chef d'�quipe JSON en un chef d'�quipe m�tier.
     * @param userJson le chef d'�quipe JSON
     * @return le chef d'�quipe m�tier
     */
    private Technician readJsonUser(JSONObject userJson){
        String code = userJson.getString("Code");
        String firstName = userJson.getString("FirstName");
        String lastName = userJson.getString("LastName");
        return new Technician(firstName, lastName, code);
    }
}
