package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Planning;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Schedule;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Technician;

import java.util.ArrayList;
import java.util.List;

public class StaticPlanningRepository implements PlanningRepository{
    private List<Planning> plannings;
    private List<Technician> technicians;

    public StaticPlanningRepository(){
        load();
    }

    private void load(){
        loadTechnicians();
    }

    private void loadTechnicians(){
        technicians = new ArrayList<>();
        technicians.add(new Technician("Nicolas","Hendrikx", "T001"));
        technicians.add(new Technician("Jean","Jadot", "T002"));
        technicians.add(new Technician("Francois","Ludewig", "T003"));
    }

    @Override
    public void writeSchedule(Schedule schedule) {

    }

    @Override
    public Iterable<Technician> getTechnicians() {
        return technicians;
    }
}
