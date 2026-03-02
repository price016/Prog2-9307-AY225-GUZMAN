import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * ============================================================
 *  DATA CLEANING AND VALIDATION REPORT
 *  A command-line Java tool to audit CSV dataset quality.
 *
 *  Detects:
 *    1. Missing values
 *    2. Negative sales
 *    3. Invalid dates
 *    4. Duplicate records
 *
 *  Usage:
 *    javac DataQualityReport.java
 *    java  DataQualityReport dataset.csv
 * ============================================================
 */
public class DataQualityReport {

    // ── Known column-name patterns ──────────────────────────
    private static final Set<String> SALES_COLUMNS = new HashSet<>(
        Arrays.asList("sales", "revenue", "amount", "price", "total")
    );
    private static final Set<String> DATE_COLUMNS = new HashSet<>(
        Arrays.asList("date", "created_at", "updated_at", "order_date", "start_date", "end_date")
    );

    private static final String LINE  = "═".repeat(62);
    private static final String DLINE = "─".repeat(62);
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // ── Issue record ────────────────────────────────────────
    static class Issue {
        int    row;
        String column;
        String detail;

        Issue(int row, String column, String detail) {
            this.row    = row;
            this.column = column;
            this.detail = detail;
        }
    }

    // ════════════════════════════════════════════════════════
    //  MAIN
    // ════════════════════════════════════════════════════════
    public static void main(String[] args) throws IOException {

        String filePath = (args.length > 0) ? args[0] : "dataset.csv";

        System.out.println("\n⏳  Loading file: " + filePath + " ...");
        File csvFile = new File(filePath);
        if (!csvFile.exists()) {
            System.err.println("\n❌  ERROR: File not found → \"" + filePath + "\"");
            System.err.println("   Make sure the CSV file is in the same folder.\n");
            System.exit(1);
        }

        // ── Read CSV ──────────────────────────────────────
        List<String>              headers = new ArrayList<>();
        List<Map<String, String>> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean firstLine = true;
            int rowNum = 2; // row 1 = header, data starts at 2

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] cells = line.split(",", -1);

                if (firstLine) {
                    for (String h : cells) headers.add(h.trim().toLowerCase());
                    firstLine = false;
                } else {
                    Map<String, String> record = new LinkedHashMap<>();
                    record.put("_row", String.valueOf(rowNum));
                    for (int i = 0; i < headers.size(); i++) {
                        String val = (i < cells.length) ? cells[i].trim() : "";
                        record.put(headers.get(i), val);
                    }
                    records.add(record);
                    rowNum++;
                }
            }
        }

        if (records.isEmpty()) {
            System.err.println("\n❌  ERROR: The file has no data rows.\n");
            System.exit(1);
        }

        System.out.println("✅  Loaded " + records.size() + " records with columns: " + headers);
        System.out.println("⏳  Running validation checks...");

        // ── Run checks ────────────────────────────────────
        List<Issue> missing      = findMissingValues(records, headers);
        List<Issue> negativeSales= findNegativeSales(records, headers);
        List<Issue> invalidDates = findInvalidDates(records, headers);
        List<Issue> duplicates   = findDuplicates(records, headers);

        // ── Print & save report ───────────────────────────
        String report = buildReport(filePath, headers, records,
                                    missing, negativeSales, invalidDates, duplicates);
        System.out.println(report);

        String outputPath = csvFile.getParent() != null
                ? csvFile.getParent() + File.separator + "quality_report.txt"
                : "quality_report.txt";
        Files.writeString(Path.of(outputPath), report);
        System.out.println("  📁  Report saved to: " + outputPath + "\n");
    }

    // ════════════════════════════════════════════════════════
    //  CHECK 1 – Missing values
    // ════════════════════════════════════════════════════════
    static List<Issue> findMissingValues(List<Map<String, String>> records,
                                          List<String> headers) {
        List<Issue> issues = new ArrayList<>();
        for (Map<String, String> row : records) {
            int rowNum = Integer.parseInt(row.get("_row"));
            for (String col : headers) {
                String val = row.getOrDefault(col, "");
                if (val == null || val.isEmpty()) {
                    issues.add(new Issue(rowNum, col,
                        "Missing value in column \"" + col + "\""));
                }
            }
        }
        return issues;
    }

    // ════════════════════════════════════════════════════════
    //  CHECK 2 – Negative sales
    // ════════════════════════════════════════════════════════
    static List<Issue> findNegativeSales(List<Map<String, String>> records,
                                          List<String> headers) {
        List<Issue> issues = new ArrayList<>();
        List<String> saleCols = new ArrayList<>();
        for (String h : headers) if (SALES_COLUMNS.contains(h)) saleCols.add(h);

        for (Map<String, String> row : records) {
            int rowNum = Integer.parseInt(row.get("_row"));
            for (String col : saleCols) {
                String val = row.getOrDefault(col, "");
                if (val.isEmpty()) continue;
                try {
                    double num = Double.parseDouble(val);
                    if (num < 0) {
                        issues.add(new Issue(rowNum, col,
                            "Negative value (" + val + ") in column \"" + col + "\""));
                    }
                } catch (NumberFormatException ignored) { /* not a number */ }
            }
        }
        return issues;
    }

    // ════════════════════════════════════════════════════════
    //  CHECK 3 – Invalid dates
    // ════════════════════════════════════════════════════════
    static List<Issue> findInvalidDates(List<Map<String, String>> records,
                                         List<String> headers) {
        List<Issue> issues = new ArrayList<>();
        List<String> dateCols = new ArrayList<>();
        for (String h : headers) if (DATE_COLUMNS.contains(h)) dateCols.add(h);

        for (Map<String, String> row : records) {
            int rowNum = Integer.parseInt(row.get("_row"));
            for (String col : dateCols) {
                String val = row.getOrDefault(col, "");
                if (val.isEmpty()) continue;
                if (!isValidDate(val)) {
                    issues.add(new Issue(rowNum, col,
                        "Invalid date \"" + val + "\" in column \"" + col + "\""));
                }
            }
        }
        return issues;
    }

    /** Returns true only for real, parseable yyyy-MM-dd dates */
    static boolean isValidDate(String val) {
        if (!val.matches("\\d{4}-\\d{2}-\\d{2}")) return false;
        try {
            LocalDate.parse(val, DATE_FMT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // ════════════════════════════════════════════════════════
    //  CHECK 4 – Duplicate records
    // ════════════════════════════════════════════════════════
    static List<Issue> findDuplicates(List<Map<String, String>> records,
                                       List<String> headers) {
        List<Issue> issues = new ArrayList<>();
        Map<String, Integer> seen = new LinkedHashMap<>();

        for (Map<String, String> row : records) {
            int rowNum = Integer.parseInt(row.get("_row"));
            StringBuilder key = new StringBuilder();
            for (String h : headers) key.append(row.getOrDefault(h, "")).append("|");
            String keyStr = key.toString();

            if (seen.containsKey(keyStr)) {
                issues.add(new Issue(rowNum, "ALL",
                    "Duplicate of row " + seen.get(keyStr)));
            } else {
                seen.put(keyStr, rowNum);
            }
        }
        return issues;
    }

    // ════════════════════════════════════════════════════════
    //  BUILD REPORT STRING
    // ════════════════════════════════════════════════════════
    static String buildReport(String filePath,
                               List<String> headers,
                               List<Map<String, String>> records,
                               List<Issue> missing,
                               List<Issue> negativeSales,
                               List<Issue> invalidDates,
                               List<Issue> duplicates) {

        int totalRows   = records.size();
        int totalCols   = headers.size();
        int totalIssues = missing.size() + negativeSales.size()
                        + invalidDates.size() + duplicates.size();
        double qualityScore = Math.max(0,
            100.0 - ((double) totalIssues / (totalRows * totalCols)) * 100.0);

        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(LINE).append("\n");
        sb.append("  📋  DATA QUALITY REPORT\n");
        sb.append(LINE).append("\n");
        sb.append(String.format("  File     : %s%n", new File(filePath).getName()));
        sb.append(String.format("  Date Run : %s%n", new Date()));
        sb.append(String.format("  Rows     : %d   |   Columns: %d%n", totalRows, totalCols));
        sb.append(DLINE).append("\n");

        // ── Summary ───────────────────────────────────────
        sb.append("\n  📊  SUMMARY\n");
        sb.append(String.format("  %-32s %10s%n", "Check", "Issues Found"));
        sb.append("  " + "-".repeat(44) + "\n");
        sb.append(String.format("  %-32s %10d%n", "Missing Values",    missing.size()));
        sb.append(String.format("  %-32s %10d%n", "Negative Sales",    negativeSales.size()));
        sb.append(String.format("  %-32s %10d%n", "Invalid Dates",     invalidDates.size()));
        sb.append(String.format("  %-32s %10d%n", "Duplicate Records", duplicates.size()));
        sb.append("  " + "-".repeat(44) + "\n");
        sb.append(String.format("  %-32s %10d%n", "TOTAL ISSUES", totalIssues));
        sb.append(String.format("%n  Data Health Score : %.1f%%  (%d issue(s) across %d rows)%n",
                  qualityScore, totalIssues, totalRows));

        // ── Detail sections ───────────────────────────────
        appendSection(sb, "🔍  Missing Values",    missing);
        appendSection(sb, "📉  Negative Sales",    negativeSales);
        appendSection(sb, "📅  Invalid Dates",     invalidDates);
        appendSection(sb, "♻️   Duplicate Records", duplicates);

        // ── Recommendations ───────────────────────────────
        sb.append("\n").append(LINE).append("\n");
        sb.append("  💡  RECOMMENDATIONS\n");
        sb.append(LINE).append("\n");

        if (missing.size()       > 0) sb.append("  • Fill in or remove the "     + missing.size()       + " row(s) with missing values.\n");
        if (negativeSales.size() > 0) sb.append("  • Review the "                + negativeSales.size() + " record(s) with negative sales — likely data-entry errors.\n");
        if (invalidDates.size()  > 0) sb.append("  • Correct the "               + invalidDates.size()  + " invalid date(s) — use format YYYY-MM-DD (e.g. 2024-06-15).\n");
        if (duplicates.size()    > 0) sb.append("  • Remove "                    + duplicates.size()    + " duplicate row(s) to avoid double-counting.\n");
        if (totalIssues == 0)         sb.append("  ✅  Your dataset looks clean! No issues detected.\n");

        sb.append("\n").append(LINE).append("\n");
        return sb.toString();
    }

    static void appendSection(StringBuilder sb, String title, List<Issue> issues) {
        sb.append("\n").append(LINE).append("\n");
        sb.append(String.format("  %s  (%d issue%s)%n",
                  title, issues.size(), issues.size() != 1 ? "s" : ""));
        sb.append(LINE).append("\n");

        if (issues.isEmpty()) {
            sb.append("  ✅  No issues found.\n");
        } else {
            sb.append(String.format("  %-6s %-20s %s%n", "Row", "Column", "Detail"));
            sb.append("  " + "-".repeat(58) + "\n");
            for (Issue iss : issues) {
                sb.append(String.format("  %-6d %-20s %s%n", iss.row, iss.column, iss.detail));
            }
        }
    }
}