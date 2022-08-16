package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import java.io.*;
import java.util.Scanner;

/**
 * Définit l'objet qui se charge du dialogue avec l'utilisateur. Elle affiche les données à afficher et récupère les entrées de l'utilisateur.
 */
public class Console implements Closeable {

    private static Console singleton;
    private final PrintStream out = System.out;
    private final Scanner in;
    private final String yesNo = "\n(O)ui/(N)on";
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";

    /**
     * Récupère l'unique instance de cet objet ou le crée si elle n'est pas encore crée.
     * @return l'unique instance de cet objet
     */
    public static Console getInstance(){
        if(singleton == null){
            singleton = new Console();
        }
        return singleton;
    }

    /**
     * Initialise le scanner qui lit les entrées de l'utilisateur.
     */
    private Console(){
        in = new Scanner(System.in);
    }

    /**
     * Affiche une chaîne de caractères.
     * @param str la chaîne de caractères à afficher
     */
    public void print(String str){
        out.print(str);
    }

    /**
     * Affiche une chaîne de caractères suivie d'un retour à la ligne.
     * @param str la chaîne de caractères à afficher
     */
    public void println(String str){
        out.println(str);
    }

    /**
     * Affiche un message d'erreur.
     * @param message le message d'erreur à afficher
     */
    public void error(String message){
        out.println("\nErreur: " + ANSI_RED + message + ANSI_RESET);
    }

    /**
     * Pose une question à l'utilisateur et attend une chaîne de caractères en réponse
     * @param question la question à poser à l'utilisateur
     * @return la réponse de l'utilisateur
     */
    public String askString(String question){
        println(question);
        print(">");
        return readLine();
    }

    /**
     * Pose une question à l'utilisateur et attend un entier positif en réponse
     * @param question la question à poser à l'utilisateur
     * @return l'entier positif de l'utilisateur ou -1 si le choix est invalide
     */
    public int askPosInt(String question){
        println(question);
        print(">");
        int choice = readInt();
        return choice >= 0 ? choice : -1;
    }

    /**
     * Pose une question à l'utilisateur et attend le caractère 'O' ou 'N' (oui/non)
     * @param question la question à poser à l'utilisateur
     * @return vrai si l'utilisateur a répondu 'O' ou faux s'il a répondu 'N'
     */
    public boolean askYesNo(String question){
        String response = "";
        while(!response.equalsIgnoreCase("O") && !response.equalsIgnoreCase("N")){
            response = askString(String.format("%s%s", question, yesNo));
        }
        return response.equalsIgnoreCase("O");
    }

    /**
     * Ferme le scanner de la console.
     * @throws IOException survient lors d'un problème lors de la fermeture du scanner.
     */
    @Override
    public void close() throws IOException {
        in.close();
    }

    /**
     * Attend l'entrée d'une chaîne de caractères de l'utilisateur et renvoie cette chaîne de caractères
     * @return la chaîne de caractère entrée par l'utilisateur
     */
    private String readLine(){
        return in.nextLine();
    }

    /**
     * Attend l'entrée d'un nombre entier de l'utilisateur et renvoie ce nombre entier ou -1 si son entrée est invalide.
     * @return le nombre entier entré ou -1 si l'entrée est invalide
     */
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
