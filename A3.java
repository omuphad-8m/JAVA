import java.util.*;

public class A3{
    public static void main(String[] args) {
        // Create a Hashtable to store student names and percentages
        Hashtable<String, Double> studentGrades = new Hashtable<>();

        // Add student name and percentage to the Hashtable
        studentGrades.put("Alice", 85.5);
        studentGrades.put("Bob", 92.0);
        studentGrades.put("Charlie", 78.3);
        studentGrades.put("Diana", 88.7);

        // i. Display the details of the Hashtable
        System.out.println("Details of the Hashtable (Student Name and Percentage):");
        for (Map.Entry<String, Double> entry : studentGrades.entrySet()) {
            System.out.println("Student: " + entry.getKey() + ", Percentage: " + entry.getValue());
        }

        // ii. Search for a specific student and display their percentage
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the name of the student to search: ");
        String studentName = scanner.nextLine();

        // Search for the student in the Hashtable
        if (studentGrades.containsKey(studentName)) {
            System.out.println(studentName + "'s percentage: " + studentGrades.get(studentName));
        } else {
            System.out.println("Student not found.");
        }

        scanner.close();
    }
}
