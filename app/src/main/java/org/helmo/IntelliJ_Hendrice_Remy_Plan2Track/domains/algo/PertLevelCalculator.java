package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.*;

public class PertLevelCalculator {

    public List<Set<PertTask>> calcLevels(Iterable<PertTask> tasks){
        var taskList = tasksToList(tasks);
        var priorMatrix = initPriorMatrix(taskList);
        var levels = new int[taskList.size()];
        List<Set<PertTask>> tasksInLevels = new ArrayList<>();

        while(!taskList.isEmpty()){
            determineLevels(taskList, tasksInLevels, levels, priorMatrix);
        }

        return tasksInLevels;
    }

    private List<PertTask> tasksToList(Iterable<PertTask> tasks){
        List<PertTask> taskList = new ArrayList<>();
        tasks.forEach(taskList::add);
        return taskList;
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

    private void determineLevels(List<PertTask> tasks, List<Set<PertTask>> tasksInLevels,
                                 int[] levels, int[][] priorMatrix){
        sumOnesInRow(levels, priorMatrix);
        clearZerosRows(tasks, tasksInLevels, levels, priorMatrix);
    }

    private void sumOnesInRow(int[] levels, int[][] priorMatrix){
        for (int i = 0; i < levels.length; ++i) {
            levels[i] = sum(priorMatrix[i]);
        }
    }

    private int sum(int[] row){
        return Arrays.stream(row).sum();
    }

    private void clearZerosRows(List<PertTask> tasks, List<Set<PertTask>> tasksInLevels,
                                int[] levelsPerJob, int[][] priorMatrix){
        Set<PertTask> level = new HashSet<>();
        for (int i = 0; i < tasks.size(); ++i) {
            if(levelsPerJob[i] == 0){
                clearColumn(i, priorMatrix);
                PertTask task = tasks.get(i);//Possibilité de changer tasks en ArrayList pour avoir accès à remove(int index)
                tasks.remove(task);
                level.add(task);
            }
        }
        tasksInLevels.add(level);
    }

    private void clearColumn(int col, int[][] matrix){
        for(int i = 0; i < matrix.length; ++i){
            matrix[i][col] = 0;
        }
    }
}
