package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlannedJob;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Schedule;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONPlanningSerializer implements PlanningSerializer {


    @Override
    public PlanningDTO deserialize(String planningJson) {
        return null;
    }

    @Override
    public String serialize(Schedule schedule) {
        JSONObject jPlanning = new JSONObject();
        jPlanning.put("Name",schedule.getName());
        JSONArray jJobs = new JSONArray();
        Iterable<PlannedJob> plannedJobs = schedule.getSchedule();
        for (PlannedJob plannedJob : plannedJobs) {
            JSONObject jJob = serializeJob(plannedJob);
            jJobs.put(jJob);
        }
        return jPlanning.toString();
    }

    private JSONObject serializeJob(PlannedJob plannedJob){
        JSONObject job = new JSONObject();
        job.put("Technician", plannedJob.getTechCode());
        job.put("Name", plannedJob.getName());
        job.put("Description", plannedJob.getDescription());
        job.put("ExpStart", plannedJob.getStartDate());
        job.put("ExpEnd", plannedJob.getEndDate());
        job.put("ActStart","");
        job.put("ActEnd","");
        return job;
    }
}
