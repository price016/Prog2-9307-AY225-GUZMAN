import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class AttendanceTracker {

    public static void main(String[] args) {

        // Main window
        JFrame frame = new JFrame("Attendance Tracker");
        frame.setSize(450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window

        // Main panel with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8); // spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels
        JLabel nameLabel = new JLabel("Attendance Name:");
        JLabel courseLabel = new JLabel("Course / Year:");
        JLabel timeLabel = new JLabel("Time In:");
        JLabel signatureLabel = new JLabel("E-Signature:");

        // Text fields
        JTextField nameField = new JTextField(20);
        JTextField courseField = new JTextField(20);
        JTextField timeField = new JTextField(20);
        JTextField signatureField = new JTextField(20);

        timeField.setEditable(false);
        signatureField.setEditable(false);

        // System date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timeField.setText(LocalDateTime.now().format(formatter));

        // Generate E-Signature
        signatureField.setText(UUID.randomUUID().toString());

        // Add components to panel (row by row)

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(courseLabel, gbc);
        gbc.gridx = 1;
        panel.add(courseField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(timeLabel, gbc);
        gbc.gridx = 1;
        panel.add(timeField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(signatureLabel, gbc);
        gbc.gridx = 1;
        panel.add(signatureField, gbc);

        // Submit Button
        JButton submitButton = new JButton("Submit Attendance");

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, gbc);

        // Button action
        submitButton.addActionListener(e ->
            JOptionPane.showMessageDialog(
                frame,
                "Attendance submitted successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            )
        );

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }
}
