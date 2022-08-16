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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Définit l'objet de stockage des montages au format JSON.
 */
public class JSONPlanningRepository implements PlanningRepository{

    private final UserParser userParser;
    private final PlanningSerializer planningSerializer;
    private final String jsonDir;
    private final String usersFile;
    private final String planningDir;
    private List<Technician> technicianList;

    /**
     * Initialise les sérialiseur JSON et les chemins vers les fichiers JSON.
     * @param userParser le sérialiseur de chef d'équipe
     * @param planningSerializer le sérialiseur de montage
     * @param jsonDir le répertoire JSON
     * @param usersFile le fichier JSON des chefs d'équipe
     * @param planningDir le répertoire des montages JSON
     */
    public JSONPlanningRepository(UserParser userParser, PlanningSerializer planningSerializer,
                                  String jsonDir, String usersFile, String planningDir) {
        this.userParser = userParser;
        this.planningSerializer = planningSerializer;
        this.jsonDir = jsonDir;
        this.usersFile = usersFile;
        this.planningDir = planningDir;
    }

    @Override
    public PlanningDTO loadSchedule(String filePathString) throws PlanningRepositoryException{
        if(!filePathString.endsWith(".json")) throw new PlanningRepositoryException("Fichier invalide : fichier .json seulement accepté");
        try{
            Path filePath = Paths.get(filePathString).toAbsolutePath();
            String planningJson = Files.readString(filePath);
            return planningSerializer.deserialize(planningJson);
        }catch(IOException ex){
            throw new PlanningRepositoryException(String.format("Impossible de lire le fichier %s", filePathString));
        }catch(JSONException ex){
            throw new PlanningRepositoryException(String.format("Fichier \"%s\" corrompu", filePathString));
        }catch(IllegalArgumentException ex){
            throw new PlanningRepositoryException(String.format("Chemin invalide : %s",  filePathString));
        }catch(OutOfMemoryError ex){
            throw new PlanningRepositoryException(String.format("Fichier %s trop volumineux", filePathString));
        }
    }

    @Override
    public void writeSchedule(Schedule schedule) throws PlanningRepositoryException{
        String planningsDirLocation = getPathInJsonDir(planningDir);
        String fileName = formatFileName(schedule.getName());
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

    /**
     * Ecrit le montage JSON dans le fichier spécifié.
     * @param filePath le chemin vers le fichier
     * @param planningJson le montage JSON
     * @throws IOException survient lorsque le fichier spécifié n'est pas accessible
     */
    private void writeFile(Path filePath, String planningJson) throws IOException {
        Stream<String> jsonLines = planningJson.lines();
        Iterable<String> iterableJsonLines = jsonLines.collect(Collectors.toUnmodifiableList());
        Files.write(filePath, iterableJsonLines, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
    }

    /**
     * Formatte le nom du montage en un nom de fichier valide.
     * @param name le nom du montage
     * @return un nom de fichier valide pour le montage
     */
    private String formatFileName(String name){
        name = name.replaceAll("[^a-zA-Z0-9]","");
        return String.format("%s-%d.json",name, getRandomNum());
    }

    /**
     * Retourne un nombre aléatoire de 5 chiffres.
     * @return un nombre aléatoire de 5 chiffres
     */
    private int getRandomNum(){
        Random random = new Random();
        return (int) (random.nextDouble() * 100000);
    }

    @Override
    public Iterable<Technician> getTechnicians() throws PlanningRepositoryException {
        if(technicianList == null){
            technicianList = new ArrayList<>();
            Iterable<Technician> technicians = loadTechnicians();
            technicians.forEach(technicianList::add);
        }
        return technicianList;
    }

    /**
     * Charge les chefs d'équipes depuis le fichiers JSON spécifié.
     * @return les chefs d'équipes
     * @throws PlanningRepositoryException survient quand une erreur intervient lors de l'accès au fichier ou lors de la sérialization.
     */
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
        }catch(OutOfMemoryError ex){
            throw new PlanningRepositoryException(String.format("Fichier %s trop volumineux", usersFile));
        }
    }

    /**
     * Retourne le chemin relatif d'un fichier ou répertoire dans le répertoire des montages.
     * @param name le nom du fichier ou répertoire
     * @return le chemin relatif d'un fichier ou répertoire dans le répertoire des montages
     */
    private String getPathInPlanningDir(String name){
        return getPathInJsonDir(String.format("%s/%s", planningDir, name));
    }

    /**
     * Retourne le chemin relatif d'un fichier ou répertoire dans le répertoire JSON.
     * @param name le nom du fichier ou répertoire
     * @return le chemin relatif d'un fichier ou répertoire dans le répertoire JSON
     */
    private String getPathInJsonDir(String name){
        return String.format("%s/%s", jsonDir, name);
    }

    /**
     * Retourne le chemin absolu du répertoire des montages.
     * @return le chemin absolu du répertoire des montages
     */
    @Override
    public File getPlanningFilesDirectory(){
        Path jsonDirPath = Paths.get(getPathInPlanningDir("")).toAbsolutePath();
        return new File(jsonDirPath.toUri());
    }
}
