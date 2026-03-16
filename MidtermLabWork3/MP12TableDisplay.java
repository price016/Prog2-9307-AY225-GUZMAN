import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
 * MP12 - Display Dataset in Formatted Table Output
 *
 * This program reads a CSV dataset file provided by the user.
 * The program parses each row of the dataset and displays the
 * contents in a formatted table structure in the console.
 */

    public class MP12TableDisplay {

        public static void main(String[] args) {

            /*
            * Variable: input
            * Scanner object used to receive user input from the keyboard.
            * In this case, the user will provide the file path of the dataset.
            */
            Scanner input = new Scanner(System.in);

            /*
            * Ask the user to input the dataset file path.
            * The program cannot continue processing until the dataset location is provided.
            */
            System.out.print("Enter CSV file path: ");
            
            /*
            * Variable: filePath
            * Stores the location of the dataset entered by the user.
            * Example: /Users/name/Documents/dataset.csv
            */
            String filePath = input.nextLine();

            try {
                
                /*
                * Dataset Handling:
                * FileReader opens the CSV dataset file.
                * BufferedReader allows the program to read the file line by line
                * instead of loading the entire dataset into memory.
                */
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                String line;

                /* Print table top border */
                System.out.println("+----------------------+----------------------+----------------------+");
                while ((line = reader.readLine()) != null) {

                    /* Split CSV row */
                    String[] columns = line.split(",");

                    /* Print table row */
                    for (int i = 0; i < columns.length; i++) {
                        System.out.printf("| %-20s ", columns[i]);
                    }

                    System.out.println("|");

                    /* Print row border */
                    System.out.println("+----------------------+----------------------+----------------------+");
                }

                reader.close();
            
            } catch (IOException e) {
                System.out.println("Error reading the file.");
            }

            input.close();
        }
    }