package softwaresystems.StudentRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class GradesForm extends JPanel {
    private final JTable table;
    private final DefaultTableModel tableModel;

    public GradesForm(JFrame frame) {
        setLayout(new BorderLayout());

        // Table setup
        String[] columnNames = {"SID", "Assignments", "Midterm", "Final Exam", "Final Mark", "Letter Grade"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel) {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                Color alternateColor = new Color(220, 220, 220);
                Color whiteColor = Color.WHITE;
                if(!comp.getBackground().equals(getSelectionBackground())) {
                    Color c = (row % 2 == 0 ? alternateColor : whiteColor);
                    comp.setBackground(c);
                    c = null;
                }
                return comp;
            }
        };
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        { // Load data from data.csv
        try (java.util.Scanner sc = new java.util.Scanner(new java.io.File("data.csv"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split(",");
                if (tokens.length == 4) {
                    StudentRecord sr = new StudentRecord(
                            tokens[0],
                            Float.parseFloat(tokens[1]),
                            Float.parseFloat(tokens[2]),
                            Float.parseFloat(tokens[3])
                    );
                    // Add the record to the table
                    tableModel.addRow(new Object[]{
                            sr.getStudentId(),
                            sr.getAssignmentGrade(),
                            sr.getMidtermGrade(),
                            sr.getExamGrade(),
                            sr.getFinalGrade(),
                            sr.getLetterGrade()
                    });
                }
            }
        } // Error handling
        catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        }
    
        // Create a panel for adding new records
        JPanel bottomPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
    
        JLabel addRecordLabel = new JLabel("Add record");
        bottomPanel.add(addRecordLabel);
    
        JLabel sidLabel = new JLabel("SID:");
        JTextField sidField = new JTextField(8);
        bottomPanel.add(sidLabel);
        bottomPanel.add(sidField);
        JLabel assignmentsLabel = new JLabel("Assignments:");
        JTextField assignmentsField = new JTextField(5);
        bottomPanel.add(assignmentsLabel);
        bottomPanel.add(assignmentsField);
        JLabel midtermLabel = new JLabel("Midterm:");
        JTextField midtermField = new JTextField(5);
        bottomPanel.add(midtermLabel);
        bottomPanel.add(midtermField);
        JLabel finalExamLabel = new JLabel("Final Exam:");
        JTextField finalExamField = new JTextField(5);
        bottomPanel.add(finalExamLabel);
        bottomPanel.add(finalExamField);
    
        JButton addButton = new JButton("Add");
        bottomPanel.add(addButton);
    
        // Handle the add button click event
        addButton.addActionListener(e -> {
            try {
                // Create a new StudentRecord from input fields
                StudentRecord sr = new StudentRecord(
                        sidField.getText(),
                        Float.parseFloat(assignmentsField.getText()),
                        Float.parseFloat(midtermField.getText()),
                        Float.parseFloat(finalExamField.getText())
                );
                // Add record to the table
                tableModel.addRow(new Object[]{
                        sr.getStudentId(),
                        sr.getAssignmentGrade(),
                        sr.getMidtermGrade(),
                        sr.getExamGrade(),
                        sr.getFinalGrade(),
                        sr.getLetterGrade()
                });
                // Clear fields
                sidField.setText("");
                assignmentsField.setText("");
                midtermField.setText("");
                finalExamField.setText("");
            } catch (NumberFormatException ex) {
                // If parse fails, do nothing or show an error message
                JOptionPane.showMessageDialog(this,
                        "Please enter valid numeric values for grades.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    
        // Add the bottom panel to the form
        this.add(bottomPanel, BorderLayout.SOUTH);
        }
    }
}
