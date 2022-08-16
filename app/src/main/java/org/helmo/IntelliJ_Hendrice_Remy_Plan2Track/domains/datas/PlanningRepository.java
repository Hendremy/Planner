package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Schedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * D�finit l'objet de stockage des montages
 */
public interface PlanningRepository {

    /**
     * Charge un montage en objet de transfert de donn�es � partir d'un chemin de fichier.
     * @param filePath le chemin de fichier
     * @return le montage en objet de transfert de donn�es
     * @throws PlanningRepositoryException survient lorsque le chargement du fichier ou la d�s�rialisation en objet �choue.
     */
    PlanningDTO loadSchedule(String filePath) throws PlanningRepositoryException;

    /**
     * Ecrit le programme de montage dans un fichier.
     * @param schedule le programme de montage
     * @throws PlanningRepositoryException survient lorsque l'�criture du fichier ou la s�rialisation en chaine de caract�res �choue.
     */
    void writeSchedule(Schedule schedule) throws PlanningRepositoryException;

    /**
     * Retourne les chefs d'�quipes charg�s depuis un fichier.
     * @return les chefs d'�quipes
     * @throws PlanningRepositoryException survient lorsque la lecture du fichier ou la d�s�rialisation en objet �choue
     */
    Iterable<Technician> getTechnicians() throws PlanningRepositoryException;

    /**
     * Retourne le chemin du r�pertoire des fichiers de montages.
     * @return le chemin du r�pertoire des fichiers de montages
     */
    File getPlanningFilesDirectory();
}
