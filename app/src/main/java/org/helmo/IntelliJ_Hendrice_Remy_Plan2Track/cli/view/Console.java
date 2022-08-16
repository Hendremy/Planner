package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.cli.view;

import java.io.*;
import java.util.Scanner;

/**
 * D�finit l'objet qui se charge du dialogue avec l'utilisateur. Elle affiche les donn�es � afficher et r�cup�re les entr�es de l'utilisateur.
 */
public class Console implements Closeable {

    private static Console singleton;
    private final PrintStream out = System.out;
    private final Scanner in;
    private final String yesNo = "\n(O)ui/(N)on";
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";

    /**
     * R�cup�re l'unique instance de cet objet ou le cr�e si elle n'est pas encore cr�e.
     * @return l'unique instance de cet objet
     */
    public static Console getInstance(){
        if(singleton == null){
            singleton = new Console();
        }
        return singleton;
    }

    /**
     * Initialise le scanner qui lit les entr�es de l'utilisateur.
     */
    private Console(){
        in = new Scanner(System.in);
    }

    /**
     * Affiche une cha�ne de caract�res.
     * @param str la cha�ne de caract�res � afficher
     */
    public void print(String str){
        out.print(str);
    }

    /**
     * Affiche une cha�ne de caract�res suivie d'un retour � la ligne.
     * @param str la cha�ne de caract�res � afficher
     */
    public void println(String str){
        out.println(str);
    }

    /**
     * Affiche un message d'erreur.
     * @param message le message d'erreur � afficher
     */
    public void error(String message){
        out.println("\nErreur: " + ANSI_RED + message + ANSI_RESET);
    }

    /**
     * Pose une question � l'utilisateur et attend une cha�ne de caract�res en r�ponse
     * @param question la question � poser � l'utilisateur
     * @return la r�ponse de l'utilisateur
     */
    public String askString(String question){
        println(question);
        print(">");
        return readLine();
    }

    /**
     * Pose une question � l'utilisateur et attend un entier positif en r�ponse
     * @param question la question � poser � l'utilisateur
     * @return l'entier positif de l'utilisateur ou -1 si le choix est invalide
     */
    public int askPosInt(String question){
        println(question);
        print(">");
        int choice = readInt();
        return choice >= 0 ? choice : -1;
    }

    /**
     * Pose une question � l'utilisateur et attend le caract�re 'O' ou 'N' (oui/non)
     * @param question la question � poser � l'utilisateur
     * @return vrai si l'utilisateur a r�pondu 'O' ou faux s'il a r�pondu 'N'
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
     * @throws IOException survient lors d'un probl�me lors de la fermeture du scanner.
     */
    @Override
    public void close() throws IOException {
        in.close();
    }

    /**
     * Attend l'entr�e d'une cha�ne de caract�res de l'utilisateur et renvoie cette cha�ne de caract�res
     * @return la cha�ne de caract�re entr�e par l'utilisateur
     */
    private String readLine(){
        return in.nextLine();
    }

    /**
     * Attend l'entr�e d'un nombre entier de l'utilisateur et renvoie ce nombre entier ou -1 si son entr�e est invalide.
     * @return le nombre entier entr� ou -1 si l'entr�e est invalide
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
