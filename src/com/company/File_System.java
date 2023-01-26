package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class File_System extends Main {

    public static ArrayList<String> deckList() throws IOException { //reads file with deck names and return arraylist of those names
        ArrayList<String> list = new ArrayList<>();

        File myObj = new File("..\\savedCards.txt");
        if(!myObj.exists()) {myObj.createNewFile();
        }

        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String ln = myReader.nextLine();
            if(!list.contains(ln)) {
                list.add(ln);
            }                                //some code in method from https://www.w3schools.com/java/java_files.asp

        }
        return list;
    }
    public static HashMap<String, String> readFile(String fileName) throws java.io.IOException { //reads file with cards return map with term adn definition
        HashMap<String, String> Map = new HashMap<>();
        File myObj = new File("..\\"+fileName+".txt");
        if(myObj.exists()) {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String key = myReader.nextLine();
                if (key != null) {
                    String value = myReader.nextLine();
                    Map.put(key, value);
                }
            }
            myReader.close();
        } else {
            System.out.println("No deck with that name exists\n");
            y();
        }                                                                      //some code in method from https://www.w3schools.com/java/java_files.asp
        return Map;
    }
    public static void saveToFile(HashMap<String, String> map, String fileName) throws java.io.IOException{ //creates file with term and defintion
        FileWriter myWriter = new FileWriter("..\\"+fileName+".txt");
        for(String key : map.keySet()) {
            String value = map.get(key);
            myWriter.write(key+"\n"+value+"\n");
            //some code in method from https://www.w3schools.com/java/java_files.asp
        }
        myWriter.close();
    }
    public static void delDeck(String fileName) throws java.io.IOException{ //removes deck name from file with list of deck names and deletes deck file
        File myObj = new File("..\\"+fileName+".txt");
        ArrayList<String> list = deckList();
        FileWriter myWriter = new FileWriter("..\\savedCards.txt");
        for(int i = 0; i < list.size(); i++) {
            String ln = list.get(i);
            if(!ln.equals(fileName)) {
                myWriter.write(ln + "\n");
            }
        }
        myWriter.close();
        myObj.delete();                    //some code in method from https://www.w3schools.com/java/java_files.asp
    }
    public static void saveNames(String fileName) throws java.io.IOException { //save names of decks to file
        ArrayList<String> list = deckList();
        FileWriter myWriter = new FileWriter("..\\savedCards.txt");
        for (int i = 0; i < list.size(); i++) {
            myWriter.write(list.get(i) + "\n");
        }
        myWriter.write(fileName + "\n");           //some code in method from https://www.w3schools.com/java/java_files.asp
        myWriter.close();
    }

    public static boolean duplicate(String name) throws IOException { //checks if the deck name already exists
        boolean result = false;
        ArrayList<String> list = deckList();
        for(String s : list) {
            if (name.equalsIgnoreCase(s)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
