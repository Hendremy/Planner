package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.algo;

import java.util.*;

public class PertGraph {
    private List<PertNode> nodes;
    private final ArrayList<PertEdge> edges;

    public PertGraph(Iterable<PertTask> tasks){
        List<PertTask> taskList = new ArrayList<>();
        tasks.forEach(taskList::add);

        edges = new ArrayList<>(taskList.size());
        initEdges(taskList);
        calcLevels(taskList);
        buildGraph();
    }

    private void initEdges(List<PertTask> taskList){
        taskList.forEach(task -> edges.add(new PertEdge(task)));
    }

    private void calcLevels(List<PertTask> taskList){
        var priorMatrix = initPriorMatrix(taskList);
        var levels = new int[taskList.size()];
        int currentLevel = 0;

        while(!taskList.isEmpty()){
            determineLevels(taskList, levels, priorMatrix, currentLevel);
            currentLevel++;
        }
    }

    private int[][] initPriorMatrix(List<PertTask> taskList){
        int dim = taskList.size();
        int[][] priorMatrix = new int[dim][dim];
        initMatrix(priorMatrix);
        findDependencies(taskList, priorMatrix);
        return priorMatrix;
    }

    private void initMatrix(int[][] priorMatrix){
        for(int[] row : priorMatrix){
            Arrays.fill(row, 0);
        }
    }

    private void findDependencies(List<PertTask> taskList, int[][] priorMatrix){
        for(int hasToBeDone = 0; hasToBeDone < priorMatrix.length; ++hasToBeDone ){
            for(int toDo = 0; toDo < priorMatrix[hasToBeDone].length; ++toDo){
                if(taskList.get(toDo).hasPredecessor(taskList.get(hasToBeDone))){
                    priorMatrix[toDo][hasToBeDone] = 1;
                }
            }
        }
    }

    private void determineLevels(List<PertTask> tasks, int[] levels, int[][] priorMatrix, int currentLevel){
        sumOnesInRow(levels, priorMatrix);
        clearZerosRows(tasks, levels, priorMatrix, currentLevel);
    }

    private void sumOnesInRow(int[] levels, int[][] priorMatrix){
        for (int i = 0; i < levels.length; ++i) {
            levels[i] = sum(priorMatrix[i]);
        }
    }

    private int sum(int[] row){
        return Arrays.stream(row).sum();
    }

    private void clearZerosRows(List<PertTask> tasks, int[] levelsPerJob, int[][] priorMatrix, int currentLevel){
        for (int i = 0; i < tasks.size(); ++i) {
            if(levelsPerJob[i] == 0){
                clearColumn(i, priorMatrix);
                PertTask task = tasks.get(i);//Possibilité de changer tasks en ArrayList pour avoir accès à remove(int index)
                tasks.remove(task);
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
