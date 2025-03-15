package softwaresystems.StudentRecord;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Lab 07");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 700);

        GradesForm form = new GradesForm(mainFrame);
        mainFrame.setContentPane(form);

        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }
}
