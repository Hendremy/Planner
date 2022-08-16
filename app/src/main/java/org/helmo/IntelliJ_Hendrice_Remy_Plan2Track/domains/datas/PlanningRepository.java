package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Schedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Définit l'objet de stockage des montages
 */
public interface PlanningRepository {

    /**
     * Charge un montage en objet de transfert de données à partir d'un chemin de fichier.
     * @param filePath le chemin de fichier
     * @return le montage en objet de transfert de données
     * @throws PlanningRepositoryException survient lorsque le chargement du fichier ou la désérialisation en objet échoue.
     */
    PlanningDTO loadSchedule(String filePath) throws PlanningRepositoryException;

    /**
     * Ecrit le programme de montage dans un fichier.
     * @param schedule le programme de montage
     * @throws PlanningRepositoryException survient lorsque l'écriture du fichier ou la sérialisation en chaine de caractères échoue.
     */
    void writeSchedule(Schedule schedule) throws PlanningRepositoryException;

    /**
     * Retourne les chefs d'équipes chargés depuis un fichier.
     * @return les chefs d'équipes
     * @throws PlanningRepositoryException survient lorsque la lecture du fichier ou la désérialisation en objet échoue
     */
    Iterable<Technician> getTechnicians() throws PlanningRepositoryException;

    /**
     * Retourne le chemin du répertoire des fichiers de montages.
     * @return le chemin du répertoire des fichiers de montages
     */
    File getPlanningFilesDirectory();
}
