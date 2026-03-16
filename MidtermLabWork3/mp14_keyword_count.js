const fs = require("fs");
const readline = require("readline");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

rl.question("Enter CSV file path: ", function(path) {

    rl.question("Enter keyword to search: ", function(keyword) {

        /*
        Variable: keyword
        This is the word that the program will search for
        inside the dataset.
        */

        fs.readFile(path, "utf8", function(err, data) {

            if (err) {
                console.log("Error reading dataset file.");
                rl.close();
                return;
            }

            /*
            Dataset Handling:
            The dataset text is split into rows so we can
            analyze each record separately.
            */

            const rows = data.split("\n");

            /*
            Variable: count
            This variable keeps track of how many times
            the keyword appears in the dataset.
            */

            let count = 0;

            /*
            Processing Logic:
            Loop through every row in the dataset and
            check if the row contains the keyword.
            */

            for (let i = 0; i < rows.length; i++) {

                /*
                toLowerCase() makes the search case-insensitive.
                This allows the program to match words like
                "Data", "data", or "DATA".
                */

                if (rows[i].toLowerCase().includes(keyword.toLowerCase())) {
                    count++;
                }
            }

            // Display the final result to the user
            console.log("\nKeyword '" + keyword + "' found " + count + " times.");

            rl.close();
        });

    });

});