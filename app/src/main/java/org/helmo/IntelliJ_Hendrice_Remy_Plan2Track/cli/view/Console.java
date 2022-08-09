package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import java.io.*;
import java.util.Scanner;

public class Console implements Closeable {

    private static Console singleton;
    private final PrintStream out = System.out;
    private final Scanner in;
    private final String yesNo = "\n(O)ui/(N)on";
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";

    public static Console getInstance(){
        if(singleton == null){
            singleton = new Console();
        }
        return singleton;
    }

    private Console(){
        in = new Scanner(System.in);
    }

    public void print(String str){
        out.print(str);
    }

    public void println(String str){
        out.println(str);
    }

    public void error(String message){
        out.println("\nErreur: " + ANSI_RED + message + ANSI_RESET);
    }

    public String askString(String question){
        println(question);
        return readLine();
    }

    public int askPosInt(String question){
        println(question);
        return readInt();
    }

    public boolean askYesNo(String question){
        String response = "";
        while(!response.equalsIgnoreCase("O") && !response.equalsIgnoreCase("N")){
            response = askString(String.format("%s%s", question, yesNo));
        }
        return response.equalsIgnoreCase("O");
    }

    @Override
    public void close() throws IOException {
        in.close();
    }

    private String readLine(){
        return in.nextLine();
    }

    private int readInt(){
        int choice;
        try{
            String response = this.readLine();
            choice = Integer.parseInt(response);
        }catch(NumberFormatException ex){
            choice = -1;
        }
        return choice;
    }

}
