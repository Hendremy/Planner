package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain;

import java.util.*;

public class PertGraph {
    private List<PertNode> nodes;
    private List<Set<PertEdge>> edgeLevels;

    public PertGraph(Iterable<Job> jobs){
        edgeLevels = new ArrayList<>();
        List<Job> jobList = new ArrayList<>();
        jobs.forEach(jobList::add);
        calcEdgeLevels(jobList);
    }

    private void calcEdgeLevels(List<Job> jobs){
        var priorMatrix = initPriorMatrix(jobs);
        int[] levels = new int[jobs.size()];
        for (int i = 0; i < jobs.size(); ++i) {
            levels[i] = sum(priorMatrix[i]);
        }

    }

    private int sum(int[] row){
        return Arrays.stream(row).sum();
    }

    private int[][] initPriorMatrix(List<Job> jobs){
        int dim = jobs.size();
        int[][] priorMatrix = new int[dim][dim];
        for(int[] row : priorMatrix){
            Arrays.fill(row, 0);
        }
        for(int prior = 0; prior < dim; ++prior ){
            for(int after = 0; after < dim; ++after){
                if(jobs.get(after).hasPrior(jobs.get(prior))){
                    priorMatrix[after][prior] = 1;
                }
            }
        }
        return priorMatrix;
    }


}
