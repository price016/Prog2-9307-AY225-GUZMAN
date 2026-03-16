import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MP14KeywordCount {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Ask for the dataset file
        System.out.print("Enter CSV file path: ");
        String filePath = input.nextLine();

        // Ask for the keyword to search
        System.out.print("Enter keyword to search: ");
        String keyword = input.nextLine();

        int count = 0;

        try {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;

            // Read each row and check if keyword exists
            while ((line = reader.readLine()) != null) {

                if (line.toLowerCase().contains(keyword.toLowerCase())) {
                    count++;
                }
            }

            reader.close();

            System.out.println("\nKeyword '" + keyword + "' found " + count + " times in dataset.");

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        input.close();
    }
}