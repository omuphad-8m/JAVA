import java.sql.*;

public class StudentTableMetadata {

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database"; // Replace "your_database" with your DB name
    private static final String DB_USER = "root"; // Replace with your DB username
    private static final String DB_PASSWORD = "password"; // Replace with your DB password

    public static void main(String[] args) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Query the student table
            String query = "SELECT * FROM student";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Retrieve ResultSetMetaData
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            // Display information about all columns
            int columnCount = resultSetMetaData.getColumnCount();
            System.out.println("Number of columns: " + columnCount);

            for (int i = 1; i <= columnCount; i++) {
                System.out.println("\nColumn " + i + ":");
                System.out.println("Name: " + resultSetMetaData.getColumnName(i));
                System.out.println("Type: " + resultSetMetaData.getColumnTypeName(i));
                System.out.println("Size: " + resultSetMetaData.getColumnDisplaySize(i));
                System.out.println("Nullable: " + (resultSetMetaData.isNullable(i) == ResultSetMetaData.columnNullable ? "Yes" : "No"));
            }

            // Close the connection
            connection.close();

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database or retrieving metadata.");
            e.printStackTrace();
        }
    }
}
