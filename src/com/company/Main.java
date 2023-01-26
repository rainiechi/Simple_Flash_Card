package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static Edit_System e;
    private static File_System f;
    private static Modes m;

    public static void main(String args[]) throws java.io.IOException  {

        start();

    }
    public static void start() throws java.io.IOException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! Would you like to load a saved file? \nType \"y\" or \"n\": ");
        String r = scan.nextLine();

        if(r.equalsIgnoreCase("y")) {
            y();
        } else if(r.equalsIgnoreCase("n")){
            String u = e.whatDeck();
            e.create(u);
            y();
        } else {
            start();
        }
    }

    public static void y() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Pick a deck, type \"add\" to add new deck, \"edit\" to edit a deck, or type \"delete\" to delete a deck\nAvailable decks:");
        ArrayList<String> list = f.deckList();
        for(int i = 0; i < list.size(); i++) {
            System.out.println("-" + list.get(i));
        }
        String a = scan.nextLine();
        if(a.equalsIgnoreCase("add")) {
            String u = e.whatDeck();
            e.create(u);
            y();
        }
        else if (a.equalsIgnoreCase("delete")) {
            System.out.println("What deck do you want to delete? Or type \"quit\" to go back");
            String del = scan.nextLine();
            if (del.equalsIgnoreCase("quit")) {
                y();
            } else {
                f.delDeck(del);
                y();
            }
        } else if(a.equalsIgnoreCase("edit")) {
            e.edit();
            y();
        } else {
            HashMap<String, String> b = f.readFile(a);
            pick(b);
        }
    }

    public static void pick(HashMap<String, String> Map) throws IOException { //homescreen method
        Scanner scan = new Scanner(System.in);
        System.out.println("Do you want to work on \"quiz\", \"learn\", or \"spell\"? enter \"0\" to choose a different deck");
        String s = scan.nextLine();
        if(s.equalsIgnoreCase("quiz")) {
            if(Map.size()<4) {
                System.out.println("Not enough terms in deck to perform quiz function");
                pick(Map);
            } else {
                m.quiz(Map);
                pick(Map);
            }

        } else if(s.equalsIgnoreCase("learn")) {
            m.learn(Map);
            pick(Map);
        } else if(s.equalsIgnoreCase("spell")) {
            m.spell(Map);
            pick(Map);
        } else if (s.equalsIgnoreCase("0")) {
            y();
        }  else {
            System.out.println("Not an option");
            pick(Map);
        }

    }

}