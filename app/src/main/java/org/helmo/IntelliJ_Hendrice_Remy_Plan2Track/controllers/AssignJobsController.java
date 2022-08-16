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
 * Définit le contrôleur d'assignation des tâches.
 */
public class AssignJobsController implements AssignJobs {

    private final ManagePlanning manageController;

    /**
     * Initialise le contrôleur d'assignation des tâches avec le controleur de gestion de montage.
     * @param  manageController le controleur de gestion de montage
     */
    public AssignJobsController(ManagePlanning manageController) {
        this.manageController = manageController;
    }

    /**
     * Retourne les chefs d'équipes.
     * @return les chefs d'équipes
     * @throws PlanningRepositoryException survient lors d'une erreur dans le chargement des chefs d'équipe dans le PlanningRepository
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
     * Récupère le chef d'équipe qui correspond au code en paramètres.
     * @param code le code d'un chef d'équipe
     * @return le chef d'équipe qui correspond au code
     * @throws PlanningRepositoryException survient lors d'une erreur dans le chargement des chefs d'équipe dans le PlanningRepository
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
