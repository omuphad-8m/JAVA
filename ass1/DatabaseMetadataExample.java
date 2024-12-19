import java.sql.*;

public class DatabaseMetadataExample {

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

            // Retrieve DatabaseMetaData object
            DatabaseMetaData databaseMetaData = connection.getMetaData();

            // Display information about the database
            System.out.println("Database Product Name: " + databaseMetaData.getDatabaseProductName());
            System.out.println("Database Product Version: " + databaseMetaData.getDatabaseProductVersion());
            System.out.println("Driver Name: " + databaseMetaData.getDriverName());
            System.out.println("Driver Version: " + databaseMetaData.getDriverVersion());

            // List all tables in the database
            System.out.println("\nTables in the database:");
            ResultSet tables = databaseMetaData.getTables(null, null, "%", new String[]{"TABLE"});

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("- " + tableName);
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
