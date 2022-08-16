package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.DaySpan;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.JobProgress;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningProgress;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.TimeReport;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Définit le convertisseur d'objets de transfert de données de montage en objets métiers.
 */
public class PlanningDTOConverter implements ConvertPlanningDTO{

    /**
     * Convertit un montage objet de transfert de données en un avancement de montage.
     * @param planningDTO le montage objet de transfert de données
     * @return l'avancement de montage
     */
    public PlanningProgress toPlanningProgress(PlanningDTO planningDTO){
        String name = planningDTO.getName();
        Collection<JobProgress> jobs = convertJobDTOs(planningDTO.getJobs());
        return new PlanningProgress(name, jobs);
    }

    /**
     * Convertit les tâches objets de transfert de données en avancements de tâche.
     * @param jobDTOs les tâches objets de transfert de données
     * @return les avancements de tâches
     */
    private Collection<JobProgress> convertJobDTOs(Iterable<JobDTO> jobDTOs){
        Set<JobProgress> jobProgresses = new HashSet<>();
        for (JobDTO job : jobDTOs) {
            jobProgresses.add(convertJobDTO(job));
        }
        return jobProgresses;
    }

    /**
     * Convertir une tâche objet de transfert de données en un avancement de tâche
     * @param job la tâche objet de tranfert de données
     * @return l'avancement de tâche
     */
    private JobProgress convertJobDTO(JobDTO job){
        String name = job.getName();
        DaySpan expected = new DaySpan(job.getExpStart(), job.getExpEnd());
        DaySpan actual = new DaySpan(job.getActStart(), job.getActEnd());
        TimeReport timeReport = new TimeReport(expected, actual);
        return new JobProgress(name, timeReport);
    }
}
