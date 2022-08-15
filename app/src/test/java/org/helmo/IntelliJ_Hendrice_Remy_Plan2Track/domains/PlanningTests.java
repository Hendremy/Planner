package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlanningTests {

    //Plan de test pour suppression d'une tâche
    private Planning test;
    private Job a;
    private Job b;
    private Job c;
    private Job d;
    private Job e;

    @BeforeEach
    void init(){
        test = new Planning("test");
        test.addJob("A","",6, new ArrayList<>());
        test.addJob("B","",5, new ArrayList<>());

        var C = new ArrayList<String>();
        C.add("A");
        var D = new ArrayList<String>();
        D.add("B");
        var E = new ArrayList<String>();
        E.add("C");
        var F = new ArrayList<String>();
        F.add("A");F.add("D");
        var G = new ArrayList<String>();
        G.add("E");G.add("F");

        test.addJob("C","",4, C);
        test.addJob("D","",6, D);
        test.addJob("E","",5, E);

        test.addJob("F","",6, F);
        test.addJob("G","",4, G);

    }

    @Test
    void removeTaskThatIsPrior() throws JobNotFoundException {
        a.addPredecessor(b);

        test.removeJob("B");

        for(var job : test.getJobs()){
            assertNotEquals(job, b);
        }
        assertFalse(a.hasPredecessor(b));
    }

    @Test
    void removeAloneTask() throws JobNotFoundException {
        test.removeJob("E");

        for(var job : test.getJobs()){
            assertNotEquals(job, e);
        }
    }

    @Test
    void removeNullTask() throws JobNotFoundException {
        test.removeJob(null);

        List<Job> jobs = new ArrayList<>();
        test.getJobs().forEach(jobs::add);
        assertEquals(jobs.size(),5);
    }

    @Test
    void removeTaskNotInPlanning() throws JobNotFoundException {
        test.removeJob("Z");

        List<Job> jobs = new ArrayList<>();
        test.getJobs().forEach(jobs::add);
        assertEquals(jobs.size(),5);
    }

    @Test
    void removeTaskIsPriorToMultiple() throws JobNotFoundException {
        a.addPredecessor(b);
        c.addPredecessor(b);
        d.addPredecessor(b);
        e.addPredecessor(b);

        test.removeJob("B");

        for(var job : test.getJobs()){
            assertNotEquals(job, b);
        }
        assertFalse(a.hasPredecessor(b));
        assertFalse(c.hasPredecessor(b));
        assertFalse(d.hasPredecessor(b));
        assertFalse(e.hasPredecessor(b));
    }
}
