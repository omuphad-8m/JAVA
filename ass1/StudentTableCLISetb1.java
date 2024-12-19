import java.sql.*;
import java.util.Scanner;

public class StudentTableCLISetb1{

    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database"; // Replace "your_database"
    private static final String DB_USER = "root"; // Replace with your DB username
    private static final String DB_PASSWORD = "password"; // Replace with your DB password

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Insert");
                System.out.println("2. Modify");
                System.out.println("3. Delete");
                System.out.println("4. Search");
                System.out.println("5. View All");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        insertStudent(connection, scanner);
                        break;
                    case 2:
                        modifyStudent(connection, scanner);
                        break;
                    case 3:
                        deleteStudent(connection, scanner);
                        break;
                    case 4:
                        searchStudent(connection, scanner);
                        break;
                    case 5:
                        viewAllStudents(connection);
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertStudent(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Roll Number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Percentage: ");
        float percentage = scanner.nextFloat();

        String insertQuery = "INSERT INTO student (roll_number, name, percentage) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, rollNumber);
            preparedStatement.setString(2, name);
            preparedStatement.setFloat(3, percentage);
            preparedStatement.executeUpdate();
            System.out.println("Student inserted successfully.");
        }
    }

    private static void modifyStudent(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Roll Number of the student to modify: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter New Name: ");
        String newName = scanner.nextLine();

        System.out.print("Enter New Percentage: ");
        float newPercentage = scanner.nextFloat();

        String updateQuery = "UPDATE student SET name = ?, percentage = ? WHERE roll_number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newName);
            preparedStatement.setFloat(2, newPercentage);
            preparedStatement.setInt(3, rollNumber);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("No student found with the given Roll Number.");
            }
        }
    }

    private static void deleteStudent(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Roll Number of the student to delete: ");
        int rollNumber = scanner.nextInt();

        String deleteQuery = "DELETE FROM student WHERE roll_number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, rollNumber);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("No student found with the given Roll Number.");
            }
        }
    }

    private static void searchStudent(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Roll Number of the student to search: ");
        int rollNumber = scanner.nextInt();

        String searchQuery = "SELECT * FROM student WHERE roll_number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            preparedStatement.setInt(1, rollNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Roll Number: " + resultSet.getInt("roll_number"));
                    System.out.println("Name: " + resultSet.getString("name"));
                    System.out.println("Percentage: " + resultSet.getFloat("percentage"));
                } else {
                    System.out.println("No student found with the given Roll Number.");
                }
            }
        }
    }

    private static void viewAllStudents(Connection connection) throws SQLException {
        String selectQuery = "SELECT * FROM student";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            System.out.println("\nAll Students:");
            System.out.printf("%-15s %-20s %-10s%n", "Roll Number", "Name", "Percentage");
            System.out.println("----------------------------------------------------");

            while (resultSet.next()) {
                int rollNumber = resultSet.getInt("roll_number");
                String name = resultSet.getString("name");
                float percentage = resultSet.getFloat("percentage");
                System.out.printf("%-15d %-20s %-10.2f%n", rollNumber, name, percentage);
            }
        }
    }
}