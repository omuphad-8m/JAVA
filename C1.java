import java.io.*;
import java.util.*;

public class C1 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java FileEditor <filename>");
            return;
        }

        String filename = args[0];
        List<String> lines = readFile(filename);
        
        if (lines == null) {
            return;  // Exit if the file can't be read.
        }
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            // Display menu
            System.out.println("\nMenu:");
            System.out.println("1. Insert line");
            System.out.println("2. Delete line");
            System.out.println("3. Append line");
            System.out.println("4. Modify line");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character after the choice
            
            switch (choice) {
                case 1: // Insert line
                    insertLine(scanner, lines);
                    break;
                case 2: // Delete line
                    deleteLine(scanner, lines);
                    break;
                case 3: // Append line
                    appendLine(scanner, lines);
                    break;
                case 4: // Modify line
                    modifyLine(scanner, lines);
                    break;
                case 5: // Exit
                    saveToFile(filename, lines);
                    System.out.println("Changes saved. Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Method to read the file into a list of strings
    private static List<String> readFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return null;
        }
        return lines;
    }

    // Method to save the list back to the file
    private static void saveToFile(String filename, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving the file: " + e.getMessage());
        }
    }

    // Method to insert a line at a specified position
    private static void insertLine(Scanner scanner, List<String> lines) {
        System.out.print("Enter the line to insert: ");
        String newLine = scanner.nextLine();
        System.out.print("Enter the position to insert (1-based index): ");
        int position = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume the newline character

        if (position >= 0 && position <= lines.size()) {
            lines.add(position, newLine);
            System.out.println("Line inserted successfully.");
        } else {
            System.out.println("Invalid position.");
        }
    }

    // Method to delete a line at a specified position
    private static void deleteLine(Scanner scanner, List<String> lines) {
        System.out.print("Enter the position of the line to delete (1-based index): ");
        int position = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume the newline character

        if (position >= 0 && position < lines.size()) {
            lines.remove(position);
            System.out.println("Line deleted successfully.");
        } else {
            System.out.println("Invalid position.");
        }
    }

    // Method to append a line to the end of the list
    private static void appendLine(Scanner scanner, List<String> lines) {
        System.out.print("Enter the line to append: ");
        String newLine = scanner.nextLine();
        lines.add(newLine);
        System.out.println("Line appended successfully.");
    }

    // Method to modify a line at a specified position
    private static void modifyLine(Scanner scanner, List<String> lines) {
        System.out.print("Enter the position of the line to modify (1-based index): ");
        int position = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume the newline character

        if (position >= 0 && position < lines.size()) {
            System.out.print("Enter the new content for the line: ");
            String newLine = scanner.nextLine();
            lines.set(position, newLine);
            System.out.println("Line modified successfully.");
        } else {
            System.out.println("Invalid position.");
        }
    }
}
 