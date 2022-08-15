package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;


import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Schedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;
import org.json.JSONException;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.Normalizer;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JSONPlanningRepository implements PlanningRepository{

    private final UserParser userParser;
    private final PlanningSerializer planningSerializer;
    private final String jsonDir;
    private final String usersFile;
    private final String planningDir;
    private Set<Technician> technicianSet;

    public JSONPlanningRepository(UserParser userParser, PlanningSerializer planningParser,
                                  String jsonDir, String usersFile, String planningDir) {
        this.userParser = userParser;
        this.planningSerializer = planningParser;
        this.jsonDir = jsonDir;
        this.usersFile = usersFile;
        this.planningDir = planningDir;
        //TODO: Replacer loadTechnicians dans getTechnicians (pas à la création de l'objet)
    }

    @Override
    public PlanningDTO loadSchedule(String filePath) throws PlanningRepositoryException{
        return null;
    }

    @Override
    public void writeSchedule(Schedule schedule) throws PlanningRepositoryException{
        String planningsDirLocation = getPathInJsonDir(planningDir);
        String fileName = normalizeName(schedule.getName());
        try{
            String planningJson = planningSerializer.serialize(schedule);
            String filePathString = getPathInPlanningDir(fileName);
            Path filePath = Paths.get(filePathString).toAbsolutePath();
            writeFile(filePath, planningJson);
        }catch(IOException ex){
            throw new PlanningRepositoryException(String.format("Impossible de créer ou d'écraser le fichier %s", fileName));
        }catch(JSONException ex){
            throw new PlanningRepositoryException(String.format("Le planning \"%s\" n'a pas pû être publié", schedule.getName()));
        }catch(IllegalArgumentException ex){
            throw new PlanningRepositoryException(String.format("Chemin invalide - %s",  planningsDirLocation));
        }
    }

    private void writeFile(Path filePath, String planningJson) throws IOException {
        Stream<String> jsonLines = planningJson.lines();
        Iterable<String> iterableJsonLines = jsonLines.collect(Collectors.toUnmodifiableList());
        Files.write(filePath, iterableJsonLines, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
    }

    private String normalizeName(String name){
        return Normalizer.normalize(name, Normalizer.Form.NFKD);
    }

    @Override
    public Iterable<Technician> getTechnicians() throws PlanningRepositoryException {
        return loadTechnicians();
    }

    private Iterable<Technician> loadTechnicians() throws PlanningRepositoryException {
        String userFileLocation = getPathInJsonDir(usersFile);
        try{
            Path path = Paths.get(userFileLocation).toAbsolutePath();
            String usersJson = Files.readString(path);
            return userParser.parseArray(usersJson);
        }catch(IOException ex){
            throw new PlanningRepositoryException(String.format("Impossible d'accéder au fichier %s", usersFile));
        }catch(JSONException ex){
            throw new PlanningRepositoryException(String.format("Fichier %s corrompu", usersFile));
        }catch(IllegalArgumentException ex){
            throw new PlanningRepositoryException(String.format("Chemin invalide - %s",  userFileLocation));
        }
    }

    private String getPathInPlanningDir(String name){
        return getPathInJsonDir(String.format("%s/%s", planningDir, name));
    }
    private String getPathInJsonDir(String name){
        return String.format("%s/%s", jsonDir, name);
    }
}
