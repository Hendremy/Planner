package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains;

import java.util.ArrayList;

public class PlanningCreator{

    /*public Planning create(String name){
        return new Planning(name);
    }*/

    //Montages des tests d'acceptation
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

    /*public Planning create(String name){
        Planning test = new Planning("test");
        test.addJob("A","",2, new ArrayList<>());

        var Apred = new ArrayList<String>();
        Apred.add("A");

        test.addJob("D","",4,Apred);
        test.addJob("E","",2,Apred);
        test.addJob("J","",1,Apred);

        var Epred = new ArrayList<String>();
        Epred.add("E");

        test.addJob("B","",1,Epred);
        test.addJob("C","",3,Epred);

        var DEpred = new ArrayList<String>(); DEpred.add("D");DEpred.add("E");

        test.addJob("F","",1,DEpred);

        var Bpred = new ArrayList<String>(); Bpred.add("B");

        test.addJob("G","",4,Bpred);

        var Gpred = new ArrayList<String>(); Gpred.add("G");

        test.addJob("H","",1,Gpred);

        var JCHFpred = new ArrayList<String>();
        JCHFpred.add("J"); JCHFpred.add("C");
        JCHFpred.add("H"); JCHFpred.add("F");

        test.addJob("I","",2,JCHFpred);

        return test;
    }*/
}
