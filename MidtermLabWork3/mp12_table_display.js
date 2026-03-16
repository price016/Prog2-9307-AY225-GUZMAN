// Import the file system module
const fs = require("fs");

// Import readline so we can ask user input
const readline = require("readline");

// Create input interface
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

// Ask user where the dataset is located
rl.question("Enter the CSV file path: ", function(path) {

    // Read the file
    fs.readFile(path, "utf8", function(err, data) {

        if (err) {
            console.log("Error reading the file.");
            console.log(err.message);
            rl.close();
            return;
        }

        console.log("\nDataset Table Output:\n");

        // Split rows using new line
        const rows = data.split("\n");

        // Loop through rows
        for (let i = 0; i < rows.length; i++) {

            const columns = rows[i].split(",");

            let output = "";

            // Format table columns
            for (let j = 0; j < columns.length; j++) {
                output += "| " + columns[j].padEnd(20) + " ";
            }

            output += "|";

            console.log(output);
        }

        rl.close();
    });

});