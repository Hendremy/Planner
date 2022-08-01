package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli;

import java.io.*;
import java.util.Scanner;

public class Console implements Closeable {

    private final PrintStream OUT = System.out;
    private final Scanner IN;

    public Console(){
        IN = new Scanner(System.in);
    }

    public void print(String str){
        OUT.print(str);
    }

    public void println(String str){
        OUT.println(str);
    }

    public String readLine(){
        return IN.nextLine();
    }

    public int readInt(){
        int choice;
        try{
            String response = this.readLine();
            choice = Integer.parseInt(response);
        }catch(NumberFormatException ex){
            choice = -1;
        }
        return choice;
    }

    @Override
    public void close() throws IOException {
        IN.close();
    }
}
