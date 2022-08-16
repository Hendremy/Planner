package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.DaySpan;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.JobProgress;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningProgress;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.TimeReport;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * D�finit le convertisseur d'objets de transfert de donn�es de montage en objets m�tiers.
 */
public class PlanningDTOConverter implements ConvertPlanningDTO{

    /**
     * Convertit un montage objet de transfert de donn�es en un avancement de montage.
     * @param planningDTO le montage objet de transfert de donn�es
     * @return l'avancement de montage
     */
    public PlanningProgress toPlanningProgress(PlanningDTO planningDTO){
        String name = planningDTO.getName();
        Collection<JobProgress> jobs = convertJobDTOs(planningDTO.getJobs());
        return new PlanningProgress(name, jobs);
    }

    /**
     * Convertit les t�ches objets de transfert de donn�es en avancements de t�che.
     * @param jobDTOs les t�ches objets de transfert de donn�es
     * @return les avancements de t�ches
     */
    private Collection<JobProgress> convertJobDTOs(Iterable<JobDTO> jobDTOs){
        Set<JobProgress> jobProgresses = new HashSet<>();
        for (JobDTO job : jobDTOs) {
            jobProgresses.add(convertJobDTO(job));
        }
        return jobProgresses;
    }

    /**
     * Convertir une t�che objet de transfert de donn�es en un avancement de t�che
     * @param job la t�che objet de tranfert de donn�es
     * @return l'avancement de t�che
     */
    private JobProgress convertJobDTO(JobDTO job){
        String name = job.getName();
        DaySpan expected = new DaySpan(job.getExpStart(), job.getExpEnd());
        DaySpan actual = new DaySpan(job.getActStart(), job.getActEnd());
        TimeReport timeReport = new TimeReport(expected, actual);
        return new JobProgress(name, timeReport);
    }
}
