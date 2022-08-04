package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
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
    void removeTaskThatIsPrior(){
        a.addPredecessor(b);

        planning.removeJob(b);

        for(var job : planning.getJobs()){
            assertNotEquals(job, b);
        }
        assertFalse(a.hasPredecessor(b));
    }

    @Test
    void removeAloneTask(){
        planning.removeJob(e);

        for(var job : planning.getJobs()){
            assertNotEquals(job, e);
        }
    }

    @Test
    void removeNullTask(){
        planning.removeJob(null);

        List<Job> jobs = new ArrayList<>();
        planning.getJobs().forEach(jobs::add);
        assertEquals(jobs.size(),5);
    }

    @Test
    void removeTaskNotInPlanning(){
        Job z = new Job("Z");
        planning.removeJob(z);

        List<Job> jobs = new ArrayList<>();
        planning.getJobs().forEach(jobs::add);
        assertEquals(jobs.size(),5);
    }

    @Test
    void removeTaskIsPriorToMultiple(){
        a.addPredecessor(b);
        c.addPredecessor(b);
        d.addPredecessor(b);
        e.addPredecessor(b);

        planning.removeJob(b);

        for(var job : planning.getJobs()){
            assertNotEquals(job, b);
        }
        assertFalse(a.hasPredecessor(b));
        assertFalse(c.hasPredecessor(b));
        assertFalse(d.hasPredecessor(b));
        assertFalse(e.hasPredecessor(b));
    }
}
