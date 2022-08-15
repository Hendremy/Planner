package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PlanningProgress {
    private final String name;
    private final Set<JobProgress> jobs;

    public PlanningProgress(String name, Collection<JobProgress> jobs){
        this.name = name;
        this.jobs = new HashSet<>(jobs);
    }

    public Collection<JobProgress> getJobs(){
        return new HashSet<>(jobs);
    }

    public String getName() {
        return name;
    }

    public long getDelay(){
        LocalDate latestExpectedEnd = getLatestExpectedEnd();
        if(latestExpectedEnd.isEqual(LocalDate.MIN)){
            return 0;
        }else{
            return ChronoUnit.DAYS.between(latestExpectedEnd, LocalDate.now());
        }
    }

    private LocalDate getLatestExpectedEnd(){
        LocalDate date = LocalDate.MIN;
        for(JobProgress job : jobs){
            if(!job.isOnTime()){
                date = maxDate(date, job.getExpectedEnd());
            }
        }
        return date;
    }

    private LocalDate maxDate(LocalDate dateA, LocalDate dateB){
        return dateA.isAfter(dateB) ? dateA : dateB;
    }

}
