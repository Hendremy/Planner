package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.DaySpan;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.JobProgress;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlanningProgress;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.TimeReport;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PlanningDTOConverter implements ConvertPlanningDTO{

    public PlanningProgress toPlanningProgress(PlanningDTO planningDTO){
        String name = planningDTO.getName();
        Collection<JobProgress> jobs = convertJobDTOs(planningDTO.getJobs());
        return new PlanningProgress(name, jobs);
    }

    private Collection<JobProgress> convertJobDTOs(Iterable<JobDTO> jobDTOs){
        Set<JobProgress> jobProgresses = new HashSet<>();
        for (JobDTO job : jobDTOs) {
            jobProgresses.add(convertJobDTO(job));
        }
        return jobProgresses;
    }

    private JobProgress convertJobDTO(JobDTO job){
        String name = job.getName();
        DaySpan expected = new DaySpan(job.getExpStart(), job.getExpEnd());
        DaySpan actual = new DaySpan(job.getActStart(), job.getActEnd());
        TimeReport timeReport = new TimeReport(expected, actual);
        return new JobProgress(name, timeReport);
    }
}
