package com.company;

import java.util.*;

public class Modes {

    public static void quiz(HashMap<String, String> map) {
        Scanner scan = new Scanner(System.in);
        int score = 0;
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        Random rand = new Random();
        int qNum = 0;


        for(String key : map.keySet()) { //fill values arraylist w/ values in the map Map(defintions)
            values.add(map.get(key));
        }
        for(String key : map.keySet()) {
            HashMap<String, Integer> choices = new HashMap<String, Integer>();
            String correctAns = ranAns();
            while(choices.size() != 3) {
                int num = rand.nextInt(values.size()) + 1;
                if(!map.get(key).equals(values.get(num-1))) {
                    choices.put(values.get(num-1), 0);
                }
            }
            ArrayList<String> ch = new ArrayList<>();
            for(String key2 : choices.keySet()) {
                ch.add(key2);
            }

            System.out.println("What does "+ key +" mean?");

            if(correctAns.equals("a")) {
                System.out.println("a) "+map.get(key));
                System.out.println("b) "+ch.get(0));
                System.out.println("c) "+ch.get(1));
                System.out.println("d) "+ch.get(2));
            } else if(correctAns.equals("b")) {
                System.out.println("a) "+ch.get(0));
                System.out.println("b) "+map.get(key));
                System.out.println("c) "+ch.get(2));
                System.out.println("d) "+ch.get(1));
            } else if(correctAns.equals("c")) {
                System.out.println("a) "+ch.get(0));
                System.out.println("b) "+ch.get(1));
                System.out.println("c) "+map.get(key));
                System.out.println("d) "+ch.get(2));
            } else {
                System.out.println("a) "+ch.get(0));
                System.out.println("b) "+ch.get(1));
                System.out.println("c) "+ch.get(2));
                System.out.println("d) "+map.get(key));
            }

            System.out.print("\nType \"a\", \"b\", \"c\", or \"d\" to answer or \"quit\" to end test: ");



            String userAns = scan.nextLine().toLowerCase();
            if(userAns.equals(correctAns)) {
                System.out.println("\nCorrect!\n");
                score++;
            } else if(userAns.equals("quit")) {
                break;
            } else {
                System.out.println("\nIncorrect :(\n");
                list.add(key);
            }
            qNum++;

        }
        //results
        System.out.println("\nYou got "+score+" out of "+qNum+ " correct.\n");
        if(score != qNum) {
            System.out.println("Terms to study: ");
            for(int i = 0; i<list.size(); i++) {
                System.out.println(list.get(i));
            }
        }


    }


    public static String ranAns() {
        Random rand = new Random();
        int num = rand.nextInt(4) + 1;
        if(num == 1) {
            return "a";
        } else if(num == 2) {
            return "b";
        } else if(num == 3) {
            return "c";
        } else {
            return "d";
        }
    }
    public static void learn(Map map) { //learn method
        Scanner scr = new Scanner(System.in);
        System.out.println("What side would you like to study? Answer \"front\" or \"back\", or type \"quit\" to quit");
        String ans = scr.nextLine();
        if (ans.equals("front")) {
            learnFront(map);
            System.out.println("Would you like to study the back side now? Answer \"yes\" or \"no\"");
            String an = scr.nextLine();
            if (an.equals("yes")) {
                learnBack(map);
            }
        } else if (ans.equals("back")) {
            learnBack(map);
            System.out.println("Would you like to study the front side now? Answer \"yes\" or \"no\"");
            String an = scr.nextLine();
            if (an.equals("yes")) {
                learnFront(map);
            }
        } else if (!ans.equals("quit")) {
            System.out.println("Not an option");
            learn(map);
        }

        if ((ans.equals("front")) || (ans.equals("back"))) {
            System.out.println("Done learning!");
        }
    }

    public static void learnFront(Map map) { //helper learn method (shows front side first)
        Scanner scr = new Scanner(System.in);
        Iterator itr = map.entrySet().iterator();
        System.out.println("Press Enter to reveal definition / go to the next card");
        System.out.println("Type \"quit\" to end");
        while (itr.hasNext()) {
            Map.Entry<String, String> card = (Map.Entry) itr.next();
            String option = scr.nextLine();
            if (option.equals("quit")) break;
            else if (option.equals("")) {
                System.out.print("Word: " + card.getKey() + "  ");
                String option2 = scr.nextLine();
                if (option2.equals("")) {
                    System.out.println("Definition: " + card.getValue());
                } else if (option2.equals("quit")) break;
            }

        }
    }

    public static void learnBack (Map map) { //helper learn method (shows back side first)
        Scanner scr = new Scanner(System.in);
        Iterator itr = map.entrySet().iterator();
        System.out.println("Press Enter to reveal word / go to the next card");
        System.out.println("Type \"quit\" to end");
        while (itr.hasNext()) {
            Map.Entry<String, String> card = (Map.Entry) itr.next();
            String option = scr.nextLine();
            if (option.equals("quit")) break;
            else if (option.equals("")) {
                System.out.print("Definition: " + card.getValue() + "  ");
                String option2 = scr.nextLine();
                if (option2.equals("")) {
                    System.out.println("Word: " + card.getKey());
                } else if (option2.equals("quit")) break;
            }

        }
    }

    public static void spell(HashMap<String, String> map) {
        int wordsShown = 0;

        Scanner input = new Scanner(System.in);
        ArrayList<String> incorrectWords = new ArrayList<>();

        //Usage instructions
        System.out.println("Spell Quiz");
        System.out.println("You will be given a definition, type the term it describes on the next line.");
        System.out.println("If you get the answer right, you will be shown the next definition, otherwise, you will be shown the correct term, then the definition.");
        System.out.println("Press Enter to continue.");
        String answer = input.nextLine();

        //Review cards loop
        for (HashMap.Entry<String, String> card : map.entrySet()) {
            wordsShown++;
            System.out.println(card.getValue());
            answer = input.nextLine();
            //Quit the program when the user types "quit"
            if (answer.equals("quit")) {
                wordsShown-=1;
                break;
            } else if (!answer.equalsIgnoreCase(card.getKey())) {
                incorrectWords.add(card.getKey());
                System.out.println(card.getValue() + ": " + card.getKey());

            }

        }

        //Tell the user how many cards they got right
        System.out.println("You got " + (wordsShown - incorrectWords.size()) + " word(s) correct out of " + map.size());

        //Tell the user what words they got wrong
        if (incorrectWords.size() == 0) {
            System.out.print("Congratulations! You answered perfectly! ");
        } else {
            System.out.print("You got these word(s) wrong: ");
            for (int i = 0; i < incorrectWords.size(); i++) {
                System.out.print(incorrectWords.get(i));
                if (i == incorrectWords.size() - 1) {
                    System.out.print(". ");
                } else {
                    System.out.print(", ");
                }
            }
        }

    }

}
