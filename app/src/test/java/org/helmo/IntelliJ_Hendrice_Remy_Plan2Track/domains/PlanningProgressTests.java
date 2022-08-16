package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Plan de tests pour l'US-10 - Calcul du retard du planning
 */
public class PlanningProgressTests {

    @Test
    public void emptyPlanningHasNoDelay(){
        PlanningProgress planningProgress = new PlanningProgress("empty",new HashSet<>());

        long delay = planningProgress.getDelay();

        assertEquals(0, delay);
    }

    @Test
    public void onTimePlanningHasNoDelay(){
        List<JobProgress> progressList = new ArrayList<>();

        DaySpan actualA = new DaySpan(LocalDate.of(2022,8,16), LocalDate.of(2022,8,18));
        DaySpan expectedA = new DaySpan(LocalDate.of(2022,8,16), LocalDate.of(2022,8,18));

        DaySpan actualB = new DaySpan(LocalDate.of(2022,8,18), LocalDate.of(2022,8,23));
        DaySpan expectedB = new DaySpan(LocalDate.of(2022,8,18), LocalDate.of(2022,8,23));

        progressList.add( new JobProgress("A", new TimeReport(expectedA, actualA)));
        progressList.add( new JobProgress("B", new TimeReport(expectedB, actualB)));
        PlanningProgress planningProgress = new PlanningProgress("onTime",progressList);

        long delay = planningProgress.getDelay();

        assertEquals(0, delay);
    }

    @Test
    public void delayedFinishedPlanningHasDelay(){
        List<JobProgress> progressList = new ArrayList<>();

        DaySpan expectedA = new DaySpan(LocalDate.of(2022,8,16), LocalDate.of(2022,8,18));
        DaySpan actualA = new DaySpan(LocalDate.of(2022,8,18), LocalDate.of(2022,8,20));

        DaySpan expectedB = new DaySpan(LocalDate.of(2022,8,18), LocalDate.of(2022,8,23));
        DaySpan actualB = new DaySpan(LocalDate.of(2022,8,25), LocalDate.of(2022,8,26));

        progressList.add( new JobProgress("A", new TimeReport(expectedA, actualA)));
        progressList.add( new JobProgress("B", new TimeReport(expectedB, actualB)));
        PlanningProgress planningProgress = new PlanningProgress("delayed",progressList);

        long delay = planningProgress.getDelay();

        assertTrue(delay > 0);
    }
}
