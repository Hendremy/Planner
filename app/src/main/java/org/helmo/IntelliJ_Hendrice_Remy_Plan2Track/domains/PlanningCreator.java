package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import java.util.ArrayList;

public class PlanningCreator{

    /*public Planning create(String name){
        return new Planning(name);
    }*/

    public Planning create(String name){
        Planning test = new Planning("test");
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


        return test;
    }
}
