package flashcards;

import java.io.*;
import java.util.*;

public class Card {
    private String action;
    Map<String, String> dictionary = new HashMap<>();
    Scanner scanner = new Scanner(System.in);

    public void run() {
        while (action != "exit") {
            menu();
            switch (action) {
                case ("add"):
                    add();
                    break;
                case ("remove"):
                    remove();
                    break;
                case ("import"):
                    importFile();
                    break;
                case ("export"):
                    exportFile();
                    break;
                case ("ask"):
                    ask();
                    break;
                case ("exit"):
                    exit();
                    break;
            }
        }
    }



    private void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the action (add, remove, import, export, ask, exit):");
        action = scanner.next();
    }



    private void add() {
        System.out.println("The card:");
        String term = scanner.nextLine();

        if (dictionary.containsKey(term) == true) {
            System.out.println("The card \"" + term + "\" already exists.");
        } else {

            System.out.println("The definition of the card:");
            String definition = scanner.nextLine();

            if (dictionary.containsValue(definition) == true) {
                System.out.println("The definition \"" + definition + "\" already exists.");

            } else {
                dictionary.put(term, definition);
                System.out.println("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
            }
        }
    }



    private void remove() {
        System.out.println("The card:");
        String term = scanner.nextLine();

        if (dictionary.containsKey(term)){
            dictionary.remove(term);
            System.out.println("The card has been removed.");
        }else {
            System.out.println("Can't remove \"" + term + "\": there is no such card.");
        }
    }


    private void importFile() {
        System.out.println("File name:");
        String pathToFile = scanner.nextLine();
        int loaded = 0;

        File file = new File(pathToFile);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String[] arr = scanner.nextLine().split(";");
                dictionary.put(arr[0], arr[1]);
                loaded++;
            }
            System.out.println(loaded + " cards have been loaded.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        }
    }

    private void exportFile() {
//try 1
        System.out.println("The card:");
        String pathToFile = scanner.nextLine();
        int exported = 0;

        File file = new File(pathToFile);
        final String sp = System.getProperty("line.separator");
        try (FileWriter writer = new FileWriter(file)) {
            for (var entry : dictionary.entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();
                writer.write(k + ";" + v + sp);
                exported++;
            }
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
        System.out.println(exported + " cards have been saved.");
//try 2
        /*
        File file = new File("C:\\export.txt");
        try (PrintWriter printWriter = new PrintWriter(file)) {
            for (var entry : dictionary.entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();
                printWriter.print(k + " : " + v +"; \n"); //
            }
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
        */
//try 3
        /*
            try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("C:\\export.txt"),"UTF-8");
                BufferedWriter bw = new BufferedWriter(writer)) {
                for (var entry : dictionary.entrySet()) {
                    String k = entry.getKey();
                    String v = entry.getValue();
                    bw.write(k + " : " + v +"; ");
                }

            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
         */

    }



    private void ask() {
        scanner = new Scanner(System.in);
        System.out.println("How many times to ask?");
        int countCards = scanner.nextInt();
        scanner.nextLine();

        Random random = new Random();
        int n = countCards;
        int lower = 1;
        int upper = dictionary.size();
        int [] rand = new int [n];

        for (int i = 0; i < n; i++) {
            int intervalLength = upper - lower + 1;
            rand[i] = random.nextInt(intervalLength) + lower;
        }

        int i = 0;
        for (var entry1 : dictionary.entrySet()) {
            String card = entry1.getKey();
            String definition = entry1.getValue();
            i++;

            for (int j = 0; j < countCards; j++) {
                if (rand[j] == i) {

                    System.out.println("Print the definition of \"" + card + "\":");
                    String answer = scanner.nextLine();

                    if (definition.equals(answer)) {
                        System.out.println("Correct answer.");
                    }

                    else if (dictionary.containsValue(answer)) {

                        for (Map.Entry<String, String> entry2 : dictionary.entrySet()) {

                            String key = entry2.getKey();
                            String value = entry2.getValue();

                            if (answer.equals(value)) {
                                System.out.println("Wrong answer. The correct one is \"" + definition  + "\", you've just written the definition of \"" + key + "\".");
                            }
                        }
                    }
                    else {
                        System.out.println("Wrong answer. The correct one is \"" + definition  + "\".");
                    }
                }
            }
        }
    }


    private void exit() {
        action = "exit";
        System.out.println("Bye bye!");

    }
}
/*
        for (int i = 0; i < item; i++) {
            for (Map.Entry<String, String> e : dictionary.entrySet()) {

                String term = e.getKey();
                String definition = e.getValue();

                System.out.println("Print the definition of \"" + e.getKey() + "\":");
                answer = scanner.nextLine();

                if (answer.equals(definition)) {
                    System.out.println("Correct answer.");
                } else if (dictionary.containsValue(answer)) {
                    for (Map.Entry<String, String> entry : dictionary.entrySet()) {

                        String key = entry.getKey();
                        String value = entry.getValue();

                        if (answer.equals(value)) {
                            System.out.println("Wrong answer. The correct one is \"" + definition + "\", you've just written the definition of \"" + key + "\".");
                        }
                    }
                } else {
                    System.out.println("Wrong answer. The correct one is \"" + definition + "\".");
                }
            }

        }
    }


}
    /////
/*
    private void enterNumberOfCards() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the number of cards:");
        countCards = Integer.parseInt(scanner.nextLine());
    }


    private void inputDictionary() {
        for (int i = 1; i <= countCards; i++) {

            System.out.println("The card #" + i + ":");
            String term = scanner.nextLine();

            if (dictionary.containsKey(term) == true) {
                System.out.println("The card \"" + term + "\" already exists.");
            }


            System.out.println("The definition of the card:");
            String definition = scanner.nextLine();

            if (dictionary.containsValue(definition) == true) {
                System.out.println("The definition \"" + definition + "\" already exists.");

            }

            dictionary.put(term, definition);


        }
    }
}
/*
    private void checkCards() {

        for (Map.Entry<String, String> e : dictionary.entrySet()) {

            String card = e.getKey();
            String definition = e.getValue();

            System.out.println("Print the definition of \"" + card + "\":");
            String answer = scanner.nextLine();

            if (answer.equals(definition)) {
                System.out.println("Correct answer.");
            }
            else if (dictionary.containsValue(answer)) {
                for (Map.Entry<String, String> entry : dictionary.entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();

                    if (answer.equals(value)) {
                        System.out.println("Wrong answer. The correct one is \"" + definition + "\", you've just written the definition of \"" + key + "\".");
                    }
                }
            } else {
                System.out.println("Wrong answer. The correct one is \"" + definition + "\".");
            }
        }
    }
}
*/


/*      int countCards;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the number of cards:");
        countCards = Integer.parseInt(scanner.nextLine());

        String [] term = new String[countCards];
        String [] definition =  new String[countCards];
        String [] answer = new String[countCards];

        for (int i = 1 ; i < countCards+1; i++) {
            System.out.println("The card #"+i+":");
            term[i-1] = scanner.nextLine();

            System.out.println("The definition of the card #"+i+":");
            definition[i-1] = scanner.nextLine();
        }

        for (int i = 0 ; i < countCards; i++) {

            System.out.println("Print the definition of \""+ term[i] +"\":");
            answer[i] = scanner.nextLine();
            if (answer[i].equals(definition[i])) {
                System.out.println("Correct answer.");
            }
            else {
                System.out.println("Wrong answer. The correct one is \""+definition[i]+"\"");
            }
        }
*/




