package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;


import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Schedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class JsonPlanningRepository implements PlanningRepository{


    private final String usersFileName;
    private final String planningDirPath;
    private Set<Technician> technicianSet;

    public JsonPlanningRepository() throws PlanningRepositoryException {
        usersFileName = "users.json";
        planningDirPath = "plannings";
        loadTechnicians();
    }

    @Override
    public void writeSchedule(Schedule schedule) {

    }

    @Override
    public Iterable<Technician> getTechnicians() {
        return technicianSet;
    }

    private void loadTechnicians() throws PlanningRepositoryException {
        technicianSet = new HashSet<>();
        String userFileLocation = getPathInJsonDir(usersFileName);
        Path path = Paths.get(userFileLocation).toAbsolutePath();
        try{
            String jsonFile = Files.readString(path);
            JSONArray usersArray = new JSONArray(jsonFile);
            for(Object obj : usersArray){
                JSONObject user = (JSONObject) obj;
                Technician tech = readJsonUser(user);
                technicianSet.add(tech);
            }
        }catch(IOException ex){
            throw new PlanningRepositoryException(String.format("Impossible d'accéder au fichier %s", usersFileName));
        }catch(JSONException ex){
            throw new PlanningRepositoryException(String.format("Fichier %s corrompu", usersFileName));
        }
    }

    private Technician readJsonUser(JSONObject userJson){
        String code = userJson.get("Code").toString();
        String firstName = userJson.get("FirstName").toString();
        String lastName = userJson.get("LastName").toString();
        return new Technician(firstName, lastName, code);
    }

    private String getPathInJsonDir(String name){
        return String.format("../json/%s", name);
    }
}
