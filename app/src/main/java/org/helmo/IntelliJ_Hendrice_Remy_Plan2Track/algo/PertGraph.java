package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.algo;

import java.util.*;

public class PertGraph<T> {
    private List<PertNode<T>> nodes;
    private final ArrayList<PertEdge<T>> edges;

    public PertGraph(Iterable<T> objects){
        List<T> typeList = new ArrayList<>();
        objects.forEach(typeList::add);

        edges = new ArrayList<>(typeList.size());
        initEdges(typeList);
        calcLevels(typeList);
        buildGraph();
    }

    private void initEdges(List<T> objects){
        objects.forEach(obj -> edges.add(new PertEdge<PertCandidate<T>>(obj)));
    }

    private void calcLevels(List<T> objs){
        var priorMatrix = initPriorMatrix(objs);
        var levels = new int[objs.size()];
        int currentLevel = 0;

        while(!objs.isEmpty()){
            determineLevels(objs, levels, priorMatrix, currentLevel);
            currentLevel++;
        }
    }

    private int[][] initPriorMatrix(List<T> objects){
        int dim = objects.size();
        int[][] priorMatrix = new int[dim][dim];
        initMatrix(priorMatrix);
        findDependencies(objects, priorMatrix, dim);
        return priorMatrix;
    }

    private void initMatrix(int[][] priorMatrix){
        for(int[] row : priorMatrix){
            Arrays.fill(row, 0);
        }
    }

    private void findDependencies(List<T> jobs, int[][] priorMatrix, int dim){
        for(int hasToBeDone = 0; hasToBeDone < dim; ++hasToBeDone ){
            for(int toDo = 0; toDo < dim; ++toDo){
                if(jobs.get(toDo).hasPrior(jobs.get(hasToBeDone))){
                    priorMatrix[toDo][hasToBeDone] = 1;
                }
            }
        }
    }

    private void determineLevels(List<T> jobs, int[] levels, int[][] priorMatrix, int currentLevel){
        sumOnesInRow(levels, priorMatrix);
        clearZerosRows(jobs, levels, priorMatrix, currentLevel);
    }

    private void sumOnesInRow(int[] levels, int[][] priorMatrix){
        for (int i = 0; i < levels.length; ++i) {
            levels[i] = sum(priorMatrix[i]);
        }
    }

    private int sum(int[] row){
        return Arrays.stream(row).sum();
    }

    private void clearZerosRows(List<T> jobs, int[] levelsPerJob, int[][] priorMatrix, int currentLevel){
        for (int i = 0; i < jobs.size(); ++i) {
            if(levelsPerJob[i] == 0){
                clearColumn(i, priorMatrix);
                T obj = jobs.get(i);
                jobs.remove(job);
                edges.get(i).setLevel(currentLevel);
            }
        }
    }

    private void clearColumn(int col, int[][] matrix){
        for(int i = 0; i < matrix.length; ++i){
            matrix[i][col] = 0;
        }
    }

    private void buildGraph(){

    }
}
