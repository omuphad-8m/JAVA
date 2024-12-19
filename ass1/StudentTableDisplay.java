import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class StudentTableDisplay {

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database"; // Replace "your_database" with your DB name
    private static final String DB_USER = "root"; // Replace with your DB username
    private static final String DB_PASSWORD = "password"; // Replace with your DB password

    public static void main(String[] args) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create the student table
            createStudentTable(connection);

            // Insert sample data into the student table
            insertSampleData(connection);

            // Retrieve data from the student table
            ResultSet resultSet = fetchStudentData(connection);

            // Display data in a Swing JTable
            displayDataInTable(resultSet);

            // Close the connection
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createStudentTable(Connection connection) throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS student (" +
                "roll_number INT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "percentage FLOAT)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableQuery);
        }
    }

    private static void insertSampleData(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO student (roll_number, name, percentage) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            // Sample data
            Object[][] data = {
                {1, "Alice", 85.5},
                {2, "Bob", 78.0},
                {3, "Charlie", 92.3}
            };

            for (Object[] row : data) {
                preparedStatement.setInt(1, (Integer) row[0]);
                preparedStatement.setString(2, (String) row[1]);
                preparedStatement.setFloat(3, (Float) row[2]);
                preparedStatement.executeUpdate();
            }
        }
    }

    private static ResultSet fetchStudentData(Connection connection) throws SQLException {
        String selectQuery = "SELECT * FROM student";
        Statement statement = connection.createStatement();
        return statement.executeQuery(selectQuery);
    }

    private static void displayDataInTable(ResultSet resultSet) throws SQLException {
        // Create JFrame
        JFrame frame = new JFrame("Student Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        // Create table model and JTable
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Roll Number", "Name", "Percentage"}, 0);
        JTable table = new JTable(tableModel);

        // Populate table model with data from ResultSet
        while (resultSet.next()) {
            int rollNumber = resultSet.getInt("roll_number");
            String name = resultSet.getString("name");
            float percentage = resultSet.getFloat("percentage");
            tableModel.addRow(new Object[]{rollNumber, name, percentage});
        }

        // Add table to JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Make frame visible
        frame.setVisible(true);
    }
}
