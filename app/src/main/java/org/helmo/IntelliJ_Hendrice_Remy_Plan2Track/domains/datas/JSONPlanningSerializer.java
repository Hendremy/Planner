package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.datas;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.PlannedJob;
import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.Schedule;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class JSONPlanningSerializer implements PlanningSerializer {


    @Override
    public PlanningDTO deserialize(String planningJson) {
        JSONObject jsonPlanning = new JSONObject(planningJson);
        String name = jsonPlanning.getString("Name");
        JSONArray jsonJobs = jsonPlanning.getJSONArray("Jobs");
        Set<JobDTO> jobsDTO = new HashSet<>();
        for (Object obj : jsonJobs) {
            JSONObject jsonJob = (JSONObject) obj;
            jobsDTO.add(deserializeJob(jsonJob));
        }
        return new PlanningDTO(name, jobsDTO);
    }

    private JobDTO deserializeJob(JSONObject jsonJob){
        String name = jsonJob.getString("Name");
        String description = jsonJob.getString("Description");
        String technician = jsonJob.getString("Technician");
        LocalDate expStart = parseJsonDate(jsonJob.getString("ExpStart"));
        LocalDate expEnd = parseJsonDate(jsonJob.getString("ExpEnd"));
        LocalDate actStart = parseJsonDate(jsonJob.getString("ActStart"));
        LocalDate actEnd = parseJsonDate(jsonJob.getString("ActEnd"));
        return new JobDTO(name, description, technician, expStart, expEnd, actStart, actEnd);
    }

    private LocalDate parseJsonDate(String dateString){
        if(dateString != null && !dateString.isBlank()){
            return LocalDate.parse(dateString);
        }else{
            return null;
        }
    }

    @Override
    public String serialize(Schedule schedule) {
        JSONObject jsonPlanning = new JSONObject();
        jsonPlanning.put("Name",schedule.getName());
        JSONArray jsonJobs = new JSONArray();
        Iterable<PlannedJob> plannedJobs = schedule.getSchedule();
        for (PlannedJob plannedJob : plannedJobs) {
            JSONObject jJob = serializeJob(plannedJob);
            jsonJobs.put(jJob);
        }
        jsonPlanning.put("Jobs", jsonJobs);
        return jsonPlanning.toString();
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
