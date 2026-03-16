import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MP13MissingValues {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Ask user for the dataset location
        System.out.print("Enter CSV file path: ");
        String filePath = input.nextLine();

        int rowNumber = 1;

        try {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;

            System.out.println("\nChecking for rows with missing values...\n");

            // Read the dataset line by line
            while ((line = reader.readLine()) != null) {

                String[] columns = line.split(",");

                // Check if any column is empty
                for (String value : columns) {

                    if (value.trim().equals("")) {
                        System.out.println("Missing value found in row: " + rowNumber);
                        break;
                    }
                }

                rowNumber++;
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        input.close();
    }
}