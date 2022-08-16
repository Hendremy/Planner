package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.controllers;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Job;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepository;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas.PlanningRepositoryException;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.viewmodels.TechnicianViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * D�finit le contr�leur d'assignation des t�ches.
 */
public class AssignJobsController implements AssignJobs {

    private final ManagePlanning manageController;

    /**
     * Initialise le contr�leur d'assignation des t�ches avec le controleur de gestion de montage.
     * @param  manageController le controleur de gestion de montage
     */
    public AssignJobsController(ManagePlanning manageController) {
        this.manageController = manageController;
    }

    /**
     * Retourne les chefs d'�quipes.
     * @return les chefs d'�quipes
     * @throws PlanningRepositoryException survient lors d'une erreur dans le chargement des chefs d'�quipe dans le PlanningRepository
     */
    private Iterable<Technician> getTechnicians() throws PlanningRepositoryException {
        return getRepository().getTechnicians();
    }

    @Override
    public Collection<TechnicianViewModel> getTechniciansViewModels() throws PlanningRepositoryException {
        List<TechnicianViewModel> techVMs = new ArrayList<>();
        Iterable<Technician> techs = getTechnicians();
        techs.forEach(t -> techVMs.add(new TechnicianViewModel(t)));
        return techVMs;
    }

    @Override
    public Iterable<Job> getJobs() {
        return getPlanning().getJobs();
    }

    @Override
    public boolean jobExists(String name){
        return getPlanning().hasJob(name);
    }

    @Override
    public void assignJob(String jobName, String code) throws PlanningRepositoryException {
        Job job = getPlanning().getJobByName(jobName);
        Technician tech = findTechnician(code);
        if(job != null && tech != null){
            job.setTechnician(tech);
        }
    }

    /**
     * R�cup�re le chef d'�quipe qui correspond au code en param�tres.
     * @param code le code d'un chef d'�quipe
     * @return le chef d'�quipe qui correspond au code
     * @throws PlanningRepositoryException survient lors d'une erreur dans le chargement des chefs d'�quipe dans le PlanningRepository
     */
    private Technician findTechnician(String code) throws PlanningRepositoryException {
        Iterable<Technician> techs = getTechnicians();
        for(Technician tech : techs){
            if(tech.getCode().equals(code)){
                return tech;
            }
        }
        return null;
    }

    /**
     * Retourne l'objet de stockage de montage.
     * @return l'objet de stockage de montage
     */
    private PlanningRepository getRepository() { return manageController.getRepository();}
    private Planning getPlanning(){
        return manageController.getPlanning();
    }
}
