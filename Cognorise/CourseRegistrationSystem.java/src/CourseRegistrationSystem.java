import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseRegistrationSystem {
    private JFrame frame;
    private JTextField studentIdField;
    private JTextField studentNameField;
    private JComboBox<String> courseComboBox;
    private DefaultListModel<String> registeredCoursesModel;
    private JList<String> registeredCoursesList;
    private JLabel studentInfoLabel;

    private String[] courses = {"Course 1", "Course 2", "Course 3"};

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CourseRegistrationSystem();
            }
        });
    }

    public CourseRegistrationSystem() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Course Registration System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel studentPanel = new JPanel();
        studentPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));
        studentPanel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField(10);
        studentPanel.add(studentIdField);
        studentPanel.add(new JLabel("Student Name:"));
        studentNameField = new JTextField(10);
        studentPanel.add(studentNameField);
        panel.add(studentPanel);
        studentInfoLabel = new JLabel("Student Info: ");
        panel.add(studentInfoLabel);
        JPanel coursePanel = new JPanel();
        coursePanel.setBorder(BorderFactory.createTitledBorder("Course Registration"));
        courseComboBox = new JComboBox<>(courses);
        coursePanel.add(new JLabel("Courses:"));
        coursePanel.add(courseComboBox);
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new RegisterButtonActionListener());
        coursePanel.add(registerButton);
        panel.add(coursePanel);
        JPanel registeredCoursesPanel = new JPanel();
        registeredCoursesPanel.setBorder(BorderFactory.createTitledBorder("Registered Courses"));
        registeredCoursesModel = new DefaultListModel<>();
        registeredCoursesList = new JList<>(registeredCoursesModel);
        registeredCoursesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        registeredCoursesPanel.add(new JScrollPane(registeredCoursesList));
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new RemoveButtonActionListener());
        registeredCoursesPanel.add(removeButton);
        panel.add(registeredCoursesPanel);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private class RegisterButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String selectedCourse = (String) courseComboBox.getSelectedItem();
            registeredCoursesModel.addElement(selectedCourse);
            updateStudentInfoLabel();
        }
    }

    private class RemoveButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = registeredCoursesList.getSelectedIndex();
            if (selectedIndex != -1) {
                registeredCoursesModel.remove(selectedIndex);
                updateStudentInfoLabel();
            }
        }
    }

    private void updateStudentInfoLabel() {
        String studentId = studentIdField.getText();
        String studentName = studentNameField.getText();
        String studentInfo = "Student ID: " + studentId + ", Student Name: " + studentName;
        studentInfoLabel.setText(studentInfo);
    }
}
