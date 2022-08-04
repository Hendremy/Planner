package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;

import java.util.ArrayList;
import java.util.List;

public class StaticPlanningRepository implements PlanningRepository{
    private List<Planning> plannings;
    private List<Technician> technicians;

    public StaticPlanningRepository(){
        load();
    }

    @Override
    public void load() {
        loadPlannings();
        loadTechnicians();
    }

    @Override
    public void loadPlannings() {
        plannings = new ArrayList<>();
    }

    @Override
    public void loadTechnicians(){
        technicians = new ArrayList<>();
        technicians.add(new Technician("Nicolas","Hendrikx", "T001"));
        technicians.add(new Technician("Jean","Jadot", "T002"));
        technicians.add(new Technician("François","Ludewig", "T003"));
    }

    @Override
    public Iterable<Planning> getPlannings() {
        return plannings;
    }

    @Override
    public Iterable<Technician> getTechnicians() {
        return technicians;
    }

    @Override
    public void writePlannings(Iterable<Planning> plannings) {

    }
}
