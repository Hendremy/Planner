package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

/**
 * Définit une arête d'un graphe PERT
 */
public class PertEdge {
    private final PertTask task;
    private final int origin;
    private int target;

    private int freeMargin;
    private int totalMargin;

    /**
     * Initialise l'arête avec les numéros d'étapes d'origine et de destination et la tâche associée.
     * @param origin le numéro d'étape d'origine
     * @param target le numéro d'étape de destination
     * @param task la tâche associée à l'arête
     * @throws CyclicGraphException survient quand la tâche n'est pas fictive et que l'ajout de l'arête provoquerait un cycle dans le graphe
     */
    public PertEdge(int origin, int target, PertTask task) throws CyclicGraphException {
        if(origin < target || task.getDuration() == 0){
            this.task = task;
            this.origin = origin;
            this.target = target;
            freeMargin = -1;
            totalMargin = -1;
        }else{
            throw new CyclicGraphException();
        }
    }

    /**
     * Retourne le numéro de l'étape d'origine de l'arête.
     * @return le numéro de l'étape d'origine de l'arête.
     */
    public int getOrigin(){return origin;}

    /**
     * Retourne le numéro de l'étape de destination de l'arête.
     * @return le numéro de l'étape de destination de l'arête.
     */
    public int getTarget(){
        return target;
    }

    /**
     * Définit le numéro de l'étape de destination de l'arête
     * @param target le numéro de l'étape de destination de l'arête
     */
    public void setTarget(int target){
        this.target = target;
    }

    /**
     * Retourne la durée de la tâche associée.
     * @return la durée de la tâche associée
     */
    public int getDuration(){return task.getDuration();}

    /**
     * Retourne la tâche associée.
     * @return la tâche associée
     */
    public PertTask getTask(){
        return task;
    }

    /**
     * Définit la marge libre de l'arête
     * @param freeMargin marge libre de l'arête
     */
    public void setFreeMargin(int freeMargin){
        this.freeMargin = freeMargin;
    }

    /**
     * Retourne la marge totale de l'arête.
     * @return la marge totale de l'arête
     */
    public int getTotalMargin(){
        return totalMargin;
    }

    /**
     * Définit la marge totale de l'arête.
     * @param totalMargin la marge totale de l'arête.
     */
    public void setTotalMargin(int totalMargin){
        this.totalMargin = totalMargin;
    }
}
