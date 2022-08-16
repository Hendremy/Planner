package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Plan de tests pour l'US-10 - Calcul du retard du planning
 */
public class PlanningProgressTests {

    /**
     * Un montage n'ayant aucune tâche devrait ne pas avoir de retard
     */
    @Test
    public void emptyPlanningHasNoDelay(){
        PlanningProgress planningProgress = new PlanningProgress("empty",new HashSet<>());

        long delay = planningProgress.getDelay();

        assertEquals(0, delay);
    }

    /**
     * Un montage fini dans les temps devrait ne pas avoir de retard
     */
    @Test
    public void finishedOnTimePlanningHasNoDelay(){
        List<JobProgress> progressList = new ArrayList<>();

        DaySpan actualA = new DaySpan(LocalDate.of(2022,8,16), LocalDate.of(2022,8,18));
        DaySpan expectedA = new DaySpan(LocalDate.of(2022,8,16), LocalDate.of(2022,8,18));

        progressList.add( new JobProgress("A", new TimeReport(expectedA, actualA)));
        PlanningProgress planningProgress = new PlanningProgress("finishedOnTime",progressList);

        long delay = planningProgress.getDelay();

        assertEquals(0, delay);
    }

    /**
     * Un montage fini en retard devrait avoir du retard
     */
    @Test
    public void delayedFinishedPlanningHasDelay(){
        List<JobProgress> progressList = new ArrayList<>();

        DaySpan expectedA = new DaySpan(LocalDate.of(2022,8,16), LocalDate.of(2022,8,18));
        DaySpan actualA = new DaySpan(LocalDate.of(2022,8,18), LocalDate.of(2022,8,20));

        progressList.add( new JobProgress("A", new TimeReport(expectedA, actualA)));
        PlanningProgress planningProgress = new PlanningProgress("finishedDelayed",progressList);

        long delay = planningProgress.getDelay();

        assertEquals(2, delay);
    }

    /**
     * Un montage en cours devrait avoir du retard si il a des tâches non-finies devant se finir avant aujourd'hui.
     */
    @Test
    public void delayedUnfinishedPlannigHasDelay(){
        List<JobProgress> progressList = new ArrayList<>();

        DaySpan expectedA = new DaySpan(LocalDate.now().minusDays(5), LocalDate.now().minusDays(3));
        DaySpan actualA = new DaySpan(LocalDate.now().minusDays(4), null);

        progressList.add( new JobProgress("A", new TimeReport(expectedA, actualA)));
        PlanningProgress planningProgress = new PlanningProgress("delayed",progressList);

        long delay = planningProgress.getDelay();

        assertEquals(3, delay);
    }

    /**
     * Un montage en cours ne devrait pas avoir du retard s'il a des tâches à finir dans le futur
     */
    @Test
    public void onTimeUnfinishedPlannigHasDelay(){
        List<JobProgress> progressList = new ArrayList<>();

        DaySpan expectedA = new DaySpan(LocalDate.now().minusDays(5), LocalDate.now().plusDays(5));
        DaySpan actualA = new DaySpan(LocalDate.now().minusDays(4), null);

        progressList.add( new JobProgress("A", new TimeReport(expectedA, actualA)));
        PlanningProgress planningProgress = new PlanningProgress("delayed",progressList);

        long delay = planningProgress.getDelay();

        assertEquals(0, delay);
    }
}
