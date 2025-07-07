package com.myproject;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class StudentAnalyzerGUI extends JFrame { 
		private static final long serialVersionUID = 1L;
		private ArrayList<Student> students = new ArrayList<>(); 
		private String[] subjects = {"Tamil", "English", "Maths", "Science", "Social"};

		private JTextField nameField = new JTextField(15);
		private JTextField[] markFields = new JTextField[subjects.length];
		private JTextArea outputArea = new JTextArea(15, 40);
		private JTextField searchField = new JTextField(15);

		public StudentAnalyzerGUI() {
				super("Student Performance Analyzer");
				setLayout(new BorderLayout(10, 10));
			    getContentPane().setBackground(new Color(230, 245, 255)); // Light pastel background

			    // 🎯 Header
			    JLabel titleLabel = new JLabel("🎓 Student Performance Analyzer", SwingConstants.CENTER);
			    titleLabel.setFont(new Font("Verdana", Font.BOLD, 20));
			    titleLabel.setOpaque(true);
			    titleLabel.setBackground(new Color(70, 130, 180));
			    titleLabel.setForeground(Color.WHITE);
			    titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			    add(titleLabel, BorderLayout.NORTH);

			    // 📝 Input Panel
			    JPanel inputPanel = new JPanel(new GridLayout(subjects.length + 2, 2, 5, 5));
			    inputPanel.setBackground(new Color(245, 255, 250)); // Mint background
			    inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Student Details"));

			    inputPanel.add(new JLabel("Student Name:"));
			    inputPanel.add(nameField);

			    for (int i = 0; i < subjects.length; i++) {
			        inputPanel.add(new JLabel("Marks in " + subjects[i] + ":"));
			        markFields[i] = new JTextField();
			        inputPanel.add(markFields[i]);
			    }

			    JButton addButton = new JButton("➕ Add Student");
			    addButton.setBackground(new Color(60, 179, 113));
			    addButton.setForeground(Color.WHITE);
			    addButton.addActionListener(e -> addStudent());
			    inputPanel.add(addButton);

			    JButton viewAllButton = new JButton("📄 View All Reports");
			    viewAllButton.setBackground(new Color(100, 149, 237));
			    viewAllButton.setForeground(Color.WHITE);
			    viewAllButton.addActionListener(e -> viewAll());
			    inputPanel.add(viewAllButton);

			    // 🔍 Search Panel
			    JPanel searchPanel = new JPanel(new FlowLayout());
			    searchPanel.setBackground(new Color(255, 250, 240));
			    searchPanel.setBorder(BorderFactory.createTitledBorder("Search Student"));

			    searchPanel.add(new JLabel("Search by Name:"));
			    searchPanel.add(searchField);

			    JButton searchButton = new JButton("🔍 Search");
			    searchButton.setBackground(new Color(255, 140, 0));
			    searchButton.setForeground(Color.WHITE);
			    searchButton.addActionListener(e -> searchStudent());
			    searchPanel.add(searchButton);

			    // 📜 Output Area
			    outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
			    outputArea.setEditable(false);
			    outputArea.setBackground(new Color(250, 250, 250));
			    outputArea.setBorder(BorderFactory.createTitledBorder("Output"));
			    JScrollPane scrollPane = new JScrollPane(outputArea);

			    // ➕ Add components to frame
			    add(inputPanel, BorderLayout.WEST);
			    add(searchPanel, BorderLayout.CENTER);
			    add(scrollPane, BorderLayout.SOUTH);

			    setDefaultCloseOperation(EXIT_ON_CLOSE);
			    pack();
			    setLocationRelativeTo(null); // Center on screen
			    setVisible(true);
			}

	private void addStudent() {
	    try {
	        String name = nameField.getText();
	        int[] marks = new int[subjects.length];
	        for (int i = 0; i < subjects.length; i++) {
	            marks[i] = Integer.parseInt(markFields[i].getText());
	        }
	        Student s = new Student(name, marks);
	        students.add(s);
	        outputArea.setText("Student added successfully!\n" + s.getReport(subjects));
	        s.exportToFile(subjects);
	        nameField.setText("");
	        for (JTextField field : markFields) field.setText("");
	    } catch (Exception ex) {
	        outputArea.setText("Error adding student. Please check inputs.");
	    }
	}

	private void viewAll() {
	    StringBuilder sb = new StringBuilder();
	    for (Student s : students) {
	        sb.append(s.getReport(subjects)).append("\n");
	    }
	    outputArea.setText(sb.toString());
	}

	private void searchStudent() {
	    String query = searchField.getText().toLowerCase();
	    StringBuilder sb = new StringBuilder();
	    for (Student s : students) {
	        if (s.name.toLowerCase().contains(query)) {
	            sb.append(s.getReport(subjects)).append("\n");
	        }
	    }
	    outputArea.setText(sb.length() > 0 ? sb.toString() : "Student not found.");
	}

	public static void main(String[] args) {
	    SwingUtilities.invokeLater(StudentAnalyzerGUI::new);
	}

	}
