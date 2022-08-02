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
        var levelsPerJob = new int[jobs.size()];
        int currentLevel = 0;

        while(!jobs.isEmpty()){
            Set<PertEdge> level = new HashSet<>();
            determineLevels(jobs, level, levelsPerJob, priorMatrix, currentLevel);
            edgeLevels.add(level);
            currentLevel++;
        }
    }

    private int[][] initPriorMatrix(List<Job> jobs){
        int dim = jobs.size();
        int[][] priorMatrix = new int[dim][dim];
        for(int[] row : priorMatrix){
            Arrays.fill(row, 0);
        }
        for(int hasToBeDone = 0; hasToBeDone < dim; ++hasToBeDone ){
            for(int toDo = 0; toDo < dim; ++toDo){
                if(jobs.get(toDo).hasPrior(jobs.get(hasToBeDone))){
                    priorMatrix[toDo][hasToBeDone] = 1;
                }
            }
        }
        return priorMatrix;
    }

    private void determineLevels(List<Job> jobs, Set<PertEdge> level,
                                 int[] levelsPerJob, int[][] priorMatrix, int currentLevel){
        sumOnesInRow(levelsPerJob, priorMatrix);
        clearZerosRows(jobs, level, levelsPerJob, priorMatrix, currentLevel);
    }

    private void sumOnesInRow(int[] levels, int[][] priorMatrix){
        for (int i = 0; i < levels.length; ++i) {
            levels[i] = sum(priorMatrix[i]);
        }
    }

    private int sum(int[] row){
        return Arrays.stream(row).sum();
    }

    private void clearZerosRows(List<Job> jobs, Set<PertEdge> level,
                                int[] levelsPerJob, int[][] priorMatrix, int currentLevel){
        for (int i = 0; i < jobs.size(); ++i) {
            if(levelsPerJob[i] == 0){
                clearColumn(i, priorMatrix);
            }
        }
    }

    private void clearColumn(int col, int[][] matrix){
        for(int i = 0; i < matrix.length; ++i){
            matrix[i][col] = 0;
        }
    }
}
