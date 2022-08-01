package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Console implements Closeable {

    private final PrintStream out = System.out;
    private final Scanner in;
    private final String yesNo = "\n(O)ui/(N)on";

    public Console(){
        in = new Scanner(System.in);
    }

    public void print(String str){
        out.print(str);
    }

    public void println(String str){
        out.println(str);
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
