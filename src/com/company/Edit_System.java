package com.company;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Edit_System extends File_System {

    public static void edit() throws IOException{
        HashMap<String, String> Map = new HashMap<>();
        System.out.println("What deck would you like to edit?");
        Scanner input = new Scanner(System.in);
        String deckName = input.nextLine();
        Map = readFile(deckName);
        System.out.println(Map);
        //Adding Cards
        while (true) {
            System.out.println("Type your word to add a new term, type remove to remove a term, type quit when you're done");
            String a = input.nextLine().toLowerCase();
            if (a.equals("quit")) {
                saveNames(deckName);
                saveToFile(Map, deckName);

                readFile(deckName);

                break;
            } else if(a.equals("remove")) {
                System.out.println("What would you like to remove?");
                String c = input.nextLine().toLowerCase();
                Map.remove(c);
                System.out.println(Map);

            } else {

                System.out.println("Type your definition");
                String b = input.nextLine().toLowerCase();

                Map.put(a, b);

                System.out.println(Map);

            }
        }

    }
    public static HashMap<String, String> create(String deckName) throws IOException{
        HashMap<String, String> Map = new HashMap<>();
        Scanner input = new Scanner(System.in);

        //Adding Cards
        while (true) {
            System.out.println("Type your word to add a new term, type remove to remove a term, type quit when you're done");
            String a = input.nextLine().toLowerCase();
            if (a.equals("quit")) {
                saveNames(deckName);
                saveToFile(Map, deckName);
                HashMap<String, String> use = readFile(deckName);

                break;
            } else if(a.equals("remove")) {
                System.out.println("What would you like to remove?");
                String c = input.nextLine().toLowerCase();
                Map.remove(c);
                System.out.println(Map);

            } else {

                System.out.println("Type your definition");
                String b = input.nextLine().toLowerCase();

                Map.put(a, b);

                System.out.println(Map);

            }
        }
        return Map;
    }
    public static String whatDeck() throws IOException {
        System.out.println("What would you like to name this deck?");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        Boolean dupe = duplicate(name);
        if (dupe) {
            System.out.println("This name already exists, pick another name");
            name = whatDeck();
        }
        return name;
    }
}
