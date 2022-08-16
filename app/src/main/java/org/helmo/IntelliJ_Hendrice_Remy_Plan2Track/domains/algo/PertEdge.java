package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

/**
 * D�finit une ar�te d'un graphe PERT
 */
public class PertEdge {
    private final PertTask task;
    private final int origin;
    private int target;

    private int freeMargin;
    private int totalMargin;

    /**
     * Initialise l'ar�te avec les num�ros d'�tapes d'origine et de destination et la t�che associ�e.
     * @param origin le num�ro d'�tape d'origine
     * @param target le num�ro d'�tape de destination
     * @param task la t�che associ�e � l'ar�te
     * @throws CyclicGraphException survient quand la t�che n'est pas fictive et que l'ajout de l'ar�te provoquerait un cycle dans le graphe
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
     * Retourne le num�ro de l'�tape d'origine de l'ar�te.
     * @return le num�ro de l'�tape d'origine de l'ar�te.
     */
    public int getOrigin(){return origin;}

    /**
     * Retourne le num�ro de l'�tape de destination de l'ar�te.
     * @return le num�ro de l'�tape de destination de l'ar�te.
     */
    public int getTarget(){
        return target;
    }

    /**
     * D�finit le num�ro de l'�tape de destination de l'ar�te
     * @param target le num�ro de l'�tape de destination de l'ar�te
     */
    public void setTarget(int target){
        this.target = target;
    }

    /**
     * Retourne la dur�e de la t�che associ�e.
     * @return la dur�e de la t�che associ�e
     */
    public int getDuration(){return task.getDuration();}

    /**
     * Retourne la t�che associ�e.
     * @return la t�che associ�e
     */
    public PertTask getTask(){
        return task;
    }

    /**
     * D�finit la marge libre de l'ar�te
     * @param freeMargin marge libre de l'ar�te
     */
    public void setFreeMargin(int freeMargin){
        this.freeMargin = freeMargin;
    }

    /**
     * Retourne la marge totale de l'ar�te.
     * @return la marge totale de l'ar�te
     */
    public int getTotalMargin(){
        return totalMargin;
    }

    /**
     * D�finit la marge totale de l'ar�te.
     * @param totalMargin la marge totale de l'ar�te.
     */
    public void setTotalMargin(int totalMargin){
        this.totalMargin = totalMargin;
    }
}
