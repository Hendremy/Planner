package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.FakeTask;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertEdge;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertGraph;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertNode;

import java.time.LocalDate;
import java.util.Set;

public class ScheduleGenerator {

    public Schedule generate(PertGraph graph, Planning planning, LocalDate startDate){
        Schedule schedule = new Schedule(planning.getName());
        Set<PertEdge> edges = graph.getEdges();

        for(PertEdge edge : edges){
            if(!(edge.getTask() instanceof FakeTask)){
                PertNode originNode = graph.getNode(edge.getOrigin());
                int daysToAdd = originNode.getEarliestTime();
                LocalDate taskStart = startDate.plusDays(daysToAdd);
                Job job = getJobFromPlanning(edge, planning);
                schedule.add(job, taskStart);
            }
        }
        return schedule;
    }

    public Job getJobFromPlanning(PertEdge edge, Planning planning){
        String jobName = edge.getTask().getName();
        return planning.getJobByName(jobName);
    }
}
