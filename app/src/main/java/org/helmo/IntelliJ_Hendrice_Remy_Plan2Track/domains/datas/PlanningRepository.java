package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Schedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;

import java.io.File;
import java.io.FileNotFoundException;

public interface PlanningRepository {

    PlanningDTO loadSchedule(String filePath) throws PlanningRepositoryException;
    void writeSchedule(Schedule schedule) throws PlanningRepositoryException;
    Iterable<Technician> getTechnicians() throws PlanningRepositoryException;
    File getPlanningFilesDirectory();
}
