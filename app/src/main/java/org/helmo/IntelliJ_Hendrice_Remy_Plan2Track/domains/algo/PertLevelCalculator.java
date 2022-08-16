package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.*;

/**
 * Définit le calculateur de niveaux de tâches PERT
 */
public class PertLevelCalculator {

    /**
     * Répartit le réseau de tâches en niveaux d'antériorité
     * @param tasks le réseau de tâches
     * @return les tâches regroupées par niveau d'antériorité
     */
    public List<Set<PertTask>> calcLevels(Iterable<PertTask> tasks){
        var taskList = tasksToList(tasks);
        var priorMatrix = initPriorMatrix(taskList);
        var levels = new int[taskList.size()];
        List<Set<PertTask>> tasksInLevels = new ArrayList<>();
        do{
            determineLevels(taskList, tasksInLevels, levels, priorMatrix);
        }while((!allTasksProcessed(levels)));
        return tasksInLevels;
    }

    /**
     * Retourne vrai si toutes les tâches ont eu leur niveau calculé, sinon faux.
     * @param levels les niveaux en cours de calcul
     * @return vrai si toutes les tâches ont eu leur niveau calculé, sinon faux
     */
    private boolean allTasksProcessed(int[] levels){
        for(int i = 0; i < levels.length; ++i){
            if(levels[i] > 0) return false;
        }
        return true;
    }

    /**
     * Convertit un itérable de tâches PERT en une liste.
     * @param tasks l'itérable de tâches à convertir
     * @return la liste des tâches
     */
    private List<PertTask> tasksToList(Iterable<PertTask> tasks){
        List<PertTask> taskList = new ArrayList<>();
        tasks.forEach(taskList::add);
        return taskList;
    }

    /**
     * Crée et retourne la matrice d'antériorité des tâches.
     * @param taskList la liste des tâches
     * @return la matrice d'antériorité des tâches
     */
    private int[][] initPriorMatrix(List<PertTask> taskList){
        int dim = taskList.size();
        int[][] priorMatrix = new int[dim][dim];
        initMatrix(priorMatrix);
        findDependencies(taskList, priorMatrix);
        return priorMatrix;
    }

    /**
     * Initialise les cases de la matrice d'antériorité à 0.
     * @param priorMatrix la matrice d'antériorité
     */
    private void initMatrix(int[][] priorMatrix){
        for(int[] row : priorMatrix){
            Arrays.fill(row, 0);
        }
    }

    /**
     * Initialise la matrice d'antériorité des tâches.
     * @param taskList la liste de tâches
     * @param priorMatrix la matrice d'antériorité
     */
    private void findDependencies(List<PertTask> taskList, int[][] priorMatrix){
        for(int hasToBeDone = 0; hasToBeDone < priorMatrix.length; ++hasToBeDone ){
            for(int toDo = 0; toDo < priorMatrix[hasToBeDone].length; ++toDo){
                if(taskList.get(toDo).hasPredecessor(taskList.get(hasToBeDone))){
                    priorMatrix[toDo][hasToBeDone] = 1;
                }
            }
        }
    }

    /**
     * Calcul les niveaux d'antériorité de chaque tâche.
     * @param tasks la liste des tâches
     * @param tasksInLevels les tâches regroupées par niveaux d'antériorité en construction
     * @param levels les niveaux des tâches en cours de calcul
     * @param priorMatrix la matrice d'antériorité
     */
    private void determineLevels(List<PertTask> tasks, List<Set<PertTask>> tasksInLevels,
                                 int[] levels, int[][] priorMatrix){
        sumOnesInRow(levels, priorMatrix);
        clearZerosRows(tasks, tasksInLevels, levels, priorMatrix);
    }

    /**
     * Somme les 1 d'une rangée et place cette somme dans le tableau des niveaux en cours de calcul.
     * @param levels les niveaux en cours de calcul
     * @param priorMatrix la matrice d'antériorité
     */
    private void sumOnesInRow(int[] levels, int[][] priorMatrix){
        for (int i = 0; i < levels.length; ++i) {
            levels[i] = sum(priorMatrix[i]);
        }
    }

    /**
     * Somme les entiers d'une rangée.
     * @param row la rangée
     * @return la somme des entiers de la rangée
     */
    private int sum(int[] row){
        return Arrays.stream(row).sum();
    }

    /**
     * Définit le niveau final des tâches ayant une rangée complète de 0 et enlève les 1 de leurs colonnes respectives.
     * @param tasks la liste des tâches
     * @param tasksInLevels les tâches regroupées par niveaux d'antériorité en construction
     * @param levelsPerJob les niveaux des tâches en cours de calcul
     * @param priorMatrix la matrice d'antériorité
     */
    private void clearZerosRows(List<PertTask> tasks, List<Set<PertTask>> tasksInLevels,
                                int[] levelsPerJob, int[][] priorMatrix){
        Set<PertTask> level = new HashSet<>();
        for (int i = 0; i < tasks.size(); ++i) {
            if(levelsPerJob[i] == 0){
                clearColumn(i, priorMatrix);
                disableRow(i, priorMatrix);
                PertTask task = tasks.get(i);
                level.add(task);
            }
        }
        tasksInLevels.add(level);
    }

    /**
     * Passe toutes les cases de la colonne à 0;
     * @param col le numéro de la colonne
     * @param matrix la matrice à mettre à jour
     */
    private void clearColumn(int col, int[][] matrix){
        for(int i = 0; i < matrix.length; ++i){
            matrix[i][col] = 0;
        }
    }

    /**
     * Passe toutes les cases de la rangée à -1 signifiant que le niveau de sa tâche a été défini.
     * @param row la rangée
     * @param matrix la matrice à mettre à jour
     */
    private void disableRow(int row, int[][] matrix){
        Arrays.fill(matrix[row], -1);
    }
}
