package com.myproject;
import java.io.FileWriter; 
import java.io.PrintWriter; 
import java.util.Arrays;


public class Student {
	String name; 
	int[] marks; 
	String[] grades; 
	double average;

	Student(String name, int[] marks) {
	    this.name = name;
	    this.marks = marks;
	    this.grades = new String[marks.length];
	    calculateAverage();
	    assignGrades();
	}

	void calculateAverage() {
	    int sum = 0;
	    for (int mark : marks) sum += mark;
	    average = (double) sum / marks.length;
	}

	void assignGrades() {
	    for (int i = 0; i < marks.length; i++) {
	        grades[i] = getGrade(marks[i]);
	    }
	}

	String getGrade(int mark) {
	    if (mark >= 90) return "A+";
	    else if (mark >= 75) return "A";
	    else if (mark >= 60) return "B";
	    else if (mark >= 50) return "C";
	    else return "F";
	}

	String predictPerformance() {
	    int low = Arrays.stream(marks).min().getAsInt();
	    if (average >= 80) return "Excellent";
	    else if (average >= 60) return "Good";
	    else if (low < 50) return "Needs Improvement";
	    else return "Average";
	}

	void exportToFile(String[] subjects) {
	    try (PrintWriter writer = new PrintWriter(new FileWriter("report_card.txt", true))) {
	        writer.println("Student: " + name);
	        for (int i = 0; i < marks.length; i++) {
	            writer.println(subjects[i] + ": " + marks[i] + " (" + grades[i] + ")");
	        }
	        writer.println("Average: " + average);
	        writer.println("Performance: " + predictPerformance());
	        writer.println("-------------------------------\n");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	String getReport(String[] subjects) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("\n--- Report Card for ").append(name).append(" ---\n");
	    for (int i = 0; i < subjects.length; i++) {
	        sb.append(subjects[i]).append(": ").append(marks[i]).append(" | Grade: ").append(grades[i]).append("\n");
	    }
	    sb.append(String.format("Average: %.2f\n", average));
	    sb.append("Performance: ").append(predictPerformance()).append("\n");
	    return sb.toString();
	}

	}

