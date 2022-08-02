package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PertGraph {
    private List<PertNode> nodes;
    private Set<PertEdge> edges;

    public PertGraph(Iterable<Job> jobs){
        edges = new HashSet<>();
        calcEdgeLevels(jobs);

    }

    private void calcEdgeLevels(Iterable<Job> jobs){
        initialLoop(jobs);

    }

    private void initialLoop(Iterable<Job> jobs){
        for(Job job : jobs){
            PertEdge jobEdge = new PertEdge(job);
            if(!job.hasPriorJobs()){
                jobEdge.setLevel(0);
            }
            edges.add(jobEdge);
        }
    }
}
