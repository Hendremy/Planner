package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Définit le sérialiseur de chef d'équipe JSON en objet métier.
 */
public class JSONUserParser implements UserParser {

    /**
     * Reconstruit les chefs d'équipes JSON en chefs d'équipe métiers.
     * @param usersJson les chefs d'équipes JSON
     * @return les chefs d'équipes métiers
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
     * Convertir un chef d'équipe JSON en un chef d'équipe métier.
     * @param userJson le chef d'équipe JSON
     * @return le chef d'équipe métier
     */
    private Technician readJsonUser(JSONObject userJson){
        String code = userJson.getString("Code");
        String firstName = userJson.getString("FirstName");
        String lastName = userJson.getString("LastName");
        return new Technician(firstName, lastName, code);
    }
}
