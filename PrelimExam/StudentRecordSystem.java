package PrelimExam;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class StudentRecordSystem extends JFrame {

    private DefaultTableModel model;
    private JTable table;
    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField lab1Field;
    private JTextField lab2Field;
    private JTextField lab3Field;
    private JTextField prelimField;
    private JTextField attendanceField;
    private JTextField searchField;
    private TableRowSorter<DefaultTableModel> sorter;

    private static final String CSV_FILENAME = "PrelimExam/class_records.csv";
    
    // Modern color scheme
    private static final Color PRIMARY_COLOR = new Color(59, 130, 246);
    private static final Color PRIMARY_HOVER = new Color(37, 99, 235);
    private static final Color DANGER_COLOR = new Color(239, 68, 68);
    private static final Color DANGER_HOVER = new Color(220, 38, 38);
    private static final Color SUCCESS_COLOR = new Color(34, 197, 94);
    private static final Color SUCCESS_HOVER = new Color(22, 163, 74);
    private static final Color BACKGROUND = new Color(249, 250, 251);
    private static final Color CARD_BACKGROUND = Color.WHITE;
    private static final Color TEXT_PRIMARY = new Color(31, 41, 55);
    private static final Color TEXT_SECONDARY = new Color(107, 114, 128);

    public StudentRecordSystem() {
        super("Student Record System");

        // macOS look & feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        
        getContentPane().setBackground(BACKGROUND);

        Font uiFont = new Font("San Francisco", Font.PLAIN, 14);
        Font titleFont = new Font("San Francisco", Font.BOLD, 24);

        // ================= TABLE =================
        model = new DefaultTableModel(new Object[]{
                "StudentID",
                "First Name",
                "Last Name",
                "LAB WORK 1",
                "LAB WORK 2",
                "LAB WORK 3",
                "PRELIM EXAM",
                "ATTENDANCE GRADE"
        }, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        table = new JTable(model);
        table.setFont(uiFont);
        table.setRowHeight(40);
        table.setShowGrid(true);
        table.setGridColor(new Color(229, 231, 235));
        table.setIntercellSpacing(new Dimension(1, 1));
        table.setSelectionBackground(new Color(219, 234, 254));
        table.setSelectionForeground(TEXT_PRIMARY);
        table.getTableHeader().setFont(uiFont.deriveFont(Font.BOLD, 13f));
        table.getTableHeader().setBackground(new Color(243, 244, 246));
        table.getTableHeader().setForeground(TEXT_PRIMARY);
        table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(229, 231, 235)));
        table.setBackground(CARD_BACKGROUND);

        // Add row sorter for search functionality
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createLineBorder(new Color(229, 231, 235), 1));
        tableScroll.getViewport().setBackground(CARD_BACKGROUND);

        // ================= SEARCH BAR =================
        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.setBackground(CARD_BACKGROUND);
        searchPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));

        JLabel searchIcon = new JLabel("🔍");
        searchIcon.setFont(new Font("San Francisco", Font.PLAIN, 18));
        
        searchField = new JTextField();
        searchField.setFont(uiFont);
        searchField.setForeground(TEXT_PRIMARY);
        searchField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                performSearch();
            }
        });

        JButton clearSearchBtn = createIconButton("✕", TEXT_SECONDARY, TEXT_SECONDARY);
        clearSearchBtn.setToolTipText("Clear search");
        clearSearchBtn.addActionListener(e -> {
            searchField.setText("");
            performSearch();
        });

        JPanel searchInputPanel = new JPanel(new BorderLayout(8, 0));
        searchInputPanel.setBackground(CARD_BACKGROUND);
        searchInputPanel.add(searchIcon, BorderLayout.WEST);
        searchInputPanel.add(searchField, BorderLayout.CENTER);
        searchInputPanel.add(clearSearchBtn, BorderLayout.EAST);

        JLabel searchLabel = new JLabel("Search Student Records");
        searchLabel.setFont(uiFont.deriveFont(Font.BOLD));
        searchLabel.setForeground(TEXT_PRIMARY);

        searchPanel.add(searchLabel, BorderLayout.NORTH);
        searchPanel.add(searchInputPanel, BorderLayout.CENTER);

        // ================= INPUT FIELDS =================
        idField = createStyledField("Student ID", uiFont);
        firstNameField = createStyledField("First Name", uiFont);
        lastNameField = createStyledField("Last Name", uiFont);
        lab1Field = createStyledField("Lab Work 1", uiFont);
        lab2Field = createStyledField("Lab Work 2", uiFont);
        lab3Field = createStyledField("Lab Work 3", uiFont);
        prelimField = createStyledField("Prelim Exam", uiFont);
        attendanceField = createStyledField("Attendance Grade", uiFont);

        // Input panel with better layout
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 12, 12));
        inputPanel.setBackground(CARD_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        inputPanel.add(createFieldWithLabel("Student ID", idField));
        inputPanel.add(createFieldWithLabel("First Name", firstNameField));
        inputPanel.add(createFieldWithLabel("Last Name", lastNameField));
        inputPanel.add(createFieldWithLabel("Lab Work 1", lab1Field));
        inputPanel.add(createFieldWithLabel("Lab Work 2", lab2Field));
        inputPanel.add(createFieldWithLabel("Lab Work 3", lab3Field));
        inputPanel.add(createFieldWithLabel("Prelim Exam", prelimField));
        inputPanel.add(createFieldWithLabel("Attendance", attendanceField));

        // ================= BUTTONS =================
        JButton addBtn = createStyledButton("+ Add Student", PRIMARY_COLOR, PRIMARY_HOVER);
        JButton deleteBtn = createStyledButton("Delete", DANGER_COLOR, DANGER_HOVER);
        JButton saveBtn = createStyledButton("Save", SUCCESS_COLOR, SUCCESS_HOVER);
        JButton exportBtn = createStyledButton("Export…", PRIMARY_COLOR, PRIMARY_HOVER);

        addBtn.addActionListener(this::onAdd);
        deleteBtn.addActionListener(this::onDelete);
        saveBtn.addActionListener(this::onSave);
        exportBtn.addActionListener(this::onExport);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnPanel.setBackground(CARD_BACKGROUND);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        btnPanel.add(addBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(saveBtn);
        btnPanel.add(exportBtn);

        // ================= TOP SECTION =================
        JLabel title = new JLabel("Student Record System", SwingConstants.CENTER);
        title.setFont(titleFont);
        title.setForeground(TEXT_PRIMARY);

        JLabel subtitle = new JLabel("Manage and track student academic records", SwingConstants.CENTER);
        subtitle.setFont(uiFont);
        subtitle.setForeground(TEXT_SECONDARY);

        JPanel titlePanel = new JPanel(new BorderLayout(0, 5));
        titlePanel.setBackground(CARD_BACKGROUND);
        titlePanel.add(title, BorderLayout.NORTH);
        titlePanel.add(subtitle, BorderLayout.CENTER);

        JPanel topInputSection = new JPanel(new BorderLayout(0, 10));
        topInputSection.setBackground(CARD_BACKGROUND);
        topInputSection.add(inputPanel, BorderLayout.CENTER);
        topInputSection.add(btnPanel, BorderLayout.SOUTH);
        
        JPanel topCard = new JPanel(new BorderLayout(0, 15));
        topCard.setBackground(CARD_BACKGROUND);
        topCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        topCard.add(titlePanel, BorderLayout.NORTH);
        topCard.add(topInputSection, BorderLayout.CENTER);

        // ================= MAIN LAYOUT =================
        JPanel centerPanel = new JPanel(new BorderLayout(0, 15));
        centerPanel.setBackground(BACKGROUND);
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(tableScroll, BorderLayout.CENTER);
        
        JPanel mainPanel = new JPanel(new BorderLayout(0, 15));
        mainPanel.setBackground(BACKGROUND);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(topCard, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        loadCSV();
    }

    // ================= STYLED COMPONENTS =================
    private JTextField createStyledField(String placeholder, Font font) {
        JTextField field = new JTextField();
        field.setFont(font);
        field.setForeground(TEXT_PRIMARY);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        
        // Placeholder functionality
        field.setText(placeholder);
        field.setForeground(TEXT_SECONDARY);

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(TEXT_PRIMARY);
                }
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
                    BorderFactory.createEmptyBorder(7, 9, 7, 9)
                ));
            }

            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(TEXT_SECONDARY);
                }
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
                    BorderFactory.createEmptyBorder(8, 10, 8, 10)
                ));
            }
        });

        return field;
    }

    private JPanel createFieldWithLabel(String label, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout(0, 5));
        panel.setBackground(CARD_BACKGROUND);
        
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("San Francisco", Font.BOLD, 12));
        lbl.setForeground(TEXT_SECONDARY);
        
        panel.add(lbl, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        
        return panel;
    }

    private JButton createStyledButton(String text, Color bgColor, Color hoverColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("San Francisco", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setPreferredSize(new Dimension(120, 40));
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(hoverColor);
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(bgColor);
            }
        });
        
        return btn;
    }

    private JButton createIconButton(String text, Color fgColor, Color hoverColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("San Francisco", Font.PLAIN, 14));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setForeground(fgColor);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setForeground(hoverColor);
            }
            public void mouseExited(MouseEvent e) {
                btn.setForeground(fgColor);
            }
        });
        
        return btn;
    }

    // ================= SEARCH FUNCTIONALITY =================
    private void performSearch() {
        String searchText = searchField.getText().trim().toLowerCase();
        
        if (searchText.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
                public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                    // Search across all columns
                    for (int i = 0; i < entry.getValueCount(); i++) {
                        String value = entry.getStringValue(i).toLowerCase();
                        if (value.contains(searchText)) {
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
    }

    // ================= ACTIONS =================
    private void onAdd(ActionEvent e) {
        String id = idField.getText().trim();
        String first = firstNameField.getText().trim();
        String last = lastNameField.getText().trim();
        String l1 = lab1Field.getText().trim();
        String l2 = lab2Field.getText().trim();
        String l3 = lab3Field.getText().trim();
        String pre = prelimField.getText().trim();
        String att = attendanceField.getText().trim();

        boolean empty = id.isEmpty() || first.isEmpty() || last.isEmpty()
            || l1.isEmpty() || l2.isEmpty() || l3.isEmpty()
            || pre.isEmpty() || att.isEmpty()
            || id.equals("Student ID")
            || first.equals("First Name")
            || last.equals("Last Name")
            || l1.equals("Lab Work 1")
            || l2.equals("Lab Work 2")
            || l3.equals("Lab Work 3")
            || pre.equals("Prelim Exam")
            || att.equals("Attendance Grade");

        if (empty) {
            JOptionPane.showMessageDialog(this, 
                "Please fill all fields.",
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        model.addRow(new Object[]{id, first, last, l1, l2, l3, pre, att});
        saveToCSV(new File(CSV_FILENAME)); // Auto-save after add
        
        JOptionPane.showMessageDialog(this, 
            "Student record added successfully!",
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);

        // Reset all fields
        resetFields();
    }

    private void resetFields() {
        resetField(idField, "Student ID");
        resetField(firstNameField, "First Name");
        resetField(lastNameField, "Last Name");
        resetField(lab1Field, "Lab Work 1");
        resetField(lab2Field, "Lab Work 2");
        resetField(lab3Field, "Lab Work 3");
        resetField(prelimField, "Prelim Exam");
        resetField(attendanceField, "Attendance Grade");
    }

    private void resetField(JTextField field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(TEXT_SECONDARY);
    }

    private void onDelete(ActionEvent e) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int modelRow = table.convertRowIndexToModel(row);
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete this record?",
                "Confirm Delete", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (confirm == JOptionPane.YES_OPTION) {
                model.removeRow(modelRow);
                saveToCSV(new File(CSV_FILENAME)); // Auto-save after delete
                JOptionPane.showMessageDialog(this, 
                    "Record deleted successfully!",
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                "Please select a row to delete.",
                "No Selection", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void onSave(ActionEvent e) {
        saveToCSV(new File(CSV_FILENAME));
    }

    private void onExport(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
        chooser.setSelectedFile(new File("class_records_export.csv"));

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            if (!f.getName().endsWith(".csv")) {
                f = new File(f.getAbsolutePath() + ".csv");
            }
            saveToCSV(f);
        }
    }

    // ================= CSV =================
    private void saveToCSV(File file) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            pw.println("StudentID,first_name,last_name,LAB WORK 1,LAB WORK 2,LAB WORK 3,PRELIM EXAM,ATTENDANCE GRADE");
            for (int i = 0; i < model.getRowCount(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int c = 0; c < model.getColumnCount(); c++) {
                    if (c > 0) sb.append(',');
                    Object val = model.getValueAt(i, c);
                    sb.append(val == null ? "" : val.toString());
                }
                pw.println(sb.toString());
            }
            JOptionPane.showMessageDialog(this, 
                "File saved successfully to:\n" + file.getAbsolutePath(),
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error saving file:\n" + ex.getMessage(),
                "Save Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadCSV() {
        File file = new File(CSV_FILENAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", -1);
                if (p.length >= 8) {
                    model.addRow(new Object[]{
                            p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7]
                    });
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error loading file:\n" + ex.getMessage(),
                "Load Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentRecordSystem().setVisible(true));
    }
}