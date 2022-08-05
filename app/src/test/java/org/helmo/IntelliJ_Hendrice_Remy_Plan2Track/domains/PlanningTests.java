package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlanningTests {

    //Plan de test pour suppression d'une tâche
    private Planning planning;
    private Job a;
    private Job b;
    private Job c;
    private Job d;
    private Job e;

    @BeforeEach
    void init(){
        planning = new Planning("Tomorrowland");
        a = new Job("A");
        b = new Job("B");
        c = new Job("C");
        d = new Job("D");
        e = new Job("E");

        planning.addJob(a);
        planning.addJob(b);
        planning.addJob(c);
        planning.addJob(d);
        planning.addJob(e);
    }

    @Test
    void removeTaskThatIsPrior() throws JobNotFoundException {
        a.addPredecessor(b);

        planning.removeJob("B");

        for(var job : planning.getJobs()){
            assertNotEquals(job, b);
        }
        assertFalse(a.hasPredecessor(b));
    }

    @Test
    void removeAloneTask() throws JobNotFoundException {
        planning.removeJob("E");

        for(var job : planning.getJobs()){
            assertNotEquals(job, e);
        }
    }

    @Test
    void removeNullTask() throws JobNotFoundException {
        planning.removeJob(null);

        List<Job> jobs = new ArrayList<>();
        planning.getJobs().forEach(jobs::add);
        assertEquals(jobs.size(),5);
    }

    @Test
    void removeTaskNotInPlanning() throws JobNotFoundException {
        planning.removeJob("Z");

        List<Job> jobs = new ArrayList<>();
        planning.getJobs().forEach(jobs::add);
        assertEquals(jobs.size(),5);
    }

    @Test
    void removeTaskIsPriorToMultiple() throws JobNotFoundException {
        a.addPredecessor(b);
        c.addPredecessor(b);
        d.addPredecessor(b);
        e.addPredecessor(b);

        planning.removeJob("B");

        for(var job : planning.getJobs()){
            assertNotEquals(job, b);
        }
        assertFalse(a.hasPredecessor(b));
        assertFalse(c.hasPredecessor(b));
        assertFalse(d.hasPredecessor(b));
        assertFalse(e.hasPredecessor(b));
    }
}
