const fs = require("fs");
const readline = require("readline");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

// Ask the user for the dataset file path
rl.question("Enter CSV file path: ", function(path) {

    /*
    Variable: filePath
    This stores the location of the CSV dataset entered by the user.
    The program will use this path to open and read the dataset file.
    */

    // Read the dataset file
    fs.readFile(path, "utf8", function(err, data) {

        // Error handling if the file cannot be opened
        if (err) {
            console.log("Error reading file.");
            rl.close();
            return;
        }

        /*
        Dataset Handling:
        The dataset is stored as a large text string.
        We split the dataset using "\n" so each row becomes an element in an array.
        */ 

        const rows = data.split("\n");

        console.log("\nRows with missing values:\n");

        /*
        Processing Logic:
        Loop through each row of the dataset
        to check if any column has missing data.
        */

        for (let i = 0; i < rows.length; i++) {

            const columns = rows[i].split(",");

            /*
            Processing Logic:
            Check every column in the row.
            If a column is empty, it means the dataset has a missing value.
            */

            for (let j = 0; j < columns.length; j++) {

                if (columns[j].trim() === "") {

                    /*
                    trim() removes spaces so the program can detect
                    truly empty values in the dataset.
                    */

                    console.log("Missing value in row:", i + 1);
                    break; // stop checking the row once a missing value is found
                }
            }
        }

        rl.close();
    });

});