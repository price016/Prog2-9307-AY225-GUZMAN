# 📋 Data Quality Report — Java Program
### A beginner-friendly guide to understanding and using this tool

---

## 🤔 What Does This Program Do?

Imagine you have a spreadsheet full of sales records — hundreds or thousands of rows. Checking every single cell by hand for mistakes would take forever. This program does that job for you **automatically**.

You give it your spreadsheet (a `.csv` file), and it scans every row and column looking for **4 types of problems**:

| Problem | What it means |
|---|---|
| 🔍 **Missing Values** | A cell is completely empty — like a sale with no customer name |
| 📉 **Negative Sales** | A sales number is below zero — like a sale of -200 |
| 📅 **Invalid Dates** | A date is impossible or badly formatted — like `2024-13-45` (month 13 doesn't exist) |
| ♻️ **Duplicate Records** | The exact same row appears more than once |

At the end, it prints a neat **Data Quality Report** on your screen and saves it as a text file.

---

## 🗂️ Files You Need

| File | What it is |
|---|---|
| `DataQualityReport.java` | The program itself |
| `dataset.csv` | A sample spreadsheet to test with |
| `README.md` | This guide |

---

## 💻 How to Run the Program (Step by Step)

### ✅ Step 1 — Install Java JDK (one time only)

You need **Java JDK** (the full developer version, not just JRE).

1. Go to 👉 [https://adoptium.net](https://adoptium.net)
2. Click **Latest LTS Release** and download the installer for your computer
3. Install it like any normal program (just click Next → Next → Finish)

> ⚠️ **Important:** Make sure you install the **JDK**, not just the JRE. The JDK includes the `javac` compiler that you need.

To verify it worked, open a terminal and type:
```
javac -version
```
You should see something like `javac 21.0.1`. If you do, you're ready!

---

### ✅ Step 2 — Put all files in ONE folder

Create a folder anywhere on your computer (for example, on your Desktop) and put these files inside it:
```
JAVA/
  ├── DataQualityReport.java
  └── dataset.csv
```

---

### ✅ Step 3 — Open a Terminal

**On Mac:**
- Press `Cmd + Space`, type `Terminal`, press Enter

**On Windows:**
- Press `Win + R`, type `cmd`, press Enter

---

### ✅ Step 4 — Navigate to Your Folder

Type `cd` followed by the path to your folder. For example:

**Mac:**
```
cd /Users/yourname/Desktop/JAVA
```

**Windows:**
```
cd C:\Users\yourname\Desktop\JAVA
```

> 💡 Tip: You can drag the folder into the terminal window and it will type the path for you!

---

### ✅ Step 5 — Compile the Program (one time only)

```
javac DataQualityReport.java
```

This converts the `.java` source code into a `.class` file that your computer can run. After this, you should see `DataQualityReport.class` appear in your folder.

> If you see no output and get your prompt back — that's good! It means it worked.

---

### ✅ Step 6 — Run the Program

```
java -cp . DataQualityReport dataset.csv
```

> ⚠️ The `-cp .` part is important — it tells Java to look for the program in the **current folder**. Don't forget it!

Replace `dataset.csv` with the name of your own file if it's different.

---

### ✅ Step 7 — Read the Report

The report will appear on your screen. It is also automatically saved as `quality_report.txt` in the same folder.

---

## 📊 Understanding the Report

Here is what the report looks like:

```
════════════════════════════════════════════════════════════
  📋  DATA QUALITY REPORT
════════════════════════════════════════════════════════════
  File     : dataset.csv
  Date Run : Mon Mar 02 2026
  Rows     : 10   |   Columns: 4

  📊  SUMMARY
  Check                            Issues Found
  --------------------------------------------
  Missing Values                            2
  Negative Sales                            2
  Invalid Dates                             2
  Duplicate Records                         0
  --------------------------------------------
  TOTAL ISSUES                              6

  Data Health Score : 85.0%  (6 issues across 10 rows)
```

### What does the Data Health Score mean?
Think of it like a grade:
- **100%** = Your data is perfect, no problems found ✅
- **80–99%** = Minor issues, easy to fix
- **Below 80%** = Significant problems that need attention

---

### Detailed Section Example

```
════════════════════════════════════════════════════════════
  🔍  MISSING VALUES  (2 issues)
════════════════════════════════════════════════════════════
  Row    Column               Detail
  ----------------------------------------------------------
  3      sales                Missing value in column "sales"
  8      name                 Missing value in column "name"
```

This tells you:
- **Row 3** has a blank cell in the `sales` column → open your spreadsheet, go to row 3, and fill in the sales number
- **Row 8** has a blank cell in the `name` column → go to row 8 and fill in the name

---

## 📁 CSV File Format Requirements

Your CSV file must follow these rules for the program to work correctly:

**1. First row must be column headers:**
```
id,name,sales,date
```

**2. Data starts from row 2 onwards:**
```
1,Alice,500,2024-01-15
2,Bob,-200,2024-02-20
```

**3. Use these exact column names** for the program to recognize them:

| What you want to check | Name your column... |
|---|---|
| Sales / money amounts | `sales`, `revenue`, `amount`, `price`, or `total` |
| Dates | `date`, `order_date`, `start_date`, `end_date`, `created_at`, or `updated_at` |
| Names, IDs, etc. | Anything — all columns are checked for missing values |

**4. Dates must be in YYYY-MM-DD format:**
```
✅ Correct:   2024-06-15
❌ Wrong:     06/15/2024  or  June 15 2024  or  15-06-2024
```

---

## ❓ Common Errors and Fixes

| Error Message | What it means | How to fix |
|---|---|---|
| `Could not find or load main class` | Java can't find the `.class` file | Make sure you used `-cp .` in the run command |
| `File not found` | The CSV file isn't in the same folder | Put your CSV in the same folder as the `.java` file |
| `javac: command not found` | JDK is not installed | Install JDK from adoptium.net |
| `The file has no data rows` | Your CSV is empty or only has a header | Add data rows to your CSV file |

---

## 💡 Quick Reference Card

```
# One-time setup
javac DataQualityReport.java

# Every time you want to run it
java -cp . DataQualityReport YourFileName.csv
```

---

## 🧠 How the Program Works — The Logic Explained Simply

The program works like a very thorough inspector walking through your spreadsheet:

**Step 1 — Reading the file**
It opens your CSV file and reads it line by line, like reading a book. The first line becomes the column headers (the labels). Every line after that becomes one record (one row of data).

**Step 2 — Checking for missing values**
For every row, it looks at every single cell. If a cell is blank — completely empty — it writes down: "Row X, Column Y is missing."

**Step 3 — Checking for negative sales**
It looks for columns with money-related names (like `sales` or `price`). For each value in those columns, it checks: is this number less than zero? If yes, it flags it as suspicious.

**Step 4 — Checking for invalid dates**
It looks for columns with date-related names. For each value, it tries to interpret it as a real calendar date. If it can't (because the month is 13, or the value is just text like "N/A"), it flags it.

**Step 5 — Checking for duplicates**
It remembers every row it has seen. If it encounters a row that matches one it already saw — every single cell is identical — it flags it as a duplicate.

**Step 6 — Printing the report**
It counts up all the issues it found, calculates the health score, and prints everything out in a clean, organized format. It also saves the report to a text file automatically.

---

*Program created for Fundamentals of Programming 2 — Midterm Lab Work*