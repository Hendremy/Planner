package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertEdge;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertGraph;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo.PertNode;

import java.util.Date;
import java.util.Set;

public class ScheduleGenerator {

    private final long dayInMilliseconds = 1000 * 60 * 60 *24;

    public Schedule generate(PertGraph graph, Date startDate){
        Schedule schedule = new Schedule();
        Set<PertEdge> edges = graph.getEdges();

        for(PertEdge edge : edges){
            PertNode originNode = graph.getNode(edge.getOrigin());
            Date taskStart = new Date(startDate.getTime() + originNode.getEarliestTime() * dayInMilliseconds);
            schedule.add(edge.getTask(), taskStart);
        }

        return schedule;
    }
}
