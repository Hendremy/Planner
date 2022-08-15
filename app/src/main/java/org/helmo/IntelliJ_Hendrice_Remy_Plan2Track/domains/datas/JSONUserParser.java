package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class JSONUserParser implements UserParser {

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

    private Technician readJsonUser(JSONObject userJson){
        String code = userJson.get("Code").toString();
        String firstName = userJson.get("FirstName").toString();
        String lastName = userJson.get("LastName").toString();
        return new Technician(firstName, lastName, code);
    }
}
