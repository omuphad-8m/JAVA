import java.util.*;

public class A1 {
    public static void main(String[] args) {
        // Create a TreeSet to store unique integers in sorted order
        Set<Integer> numbers = new TreeSet<>();
        
        // Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter 'n' integers
        System.out.print("Enter the number of integers: ");
        int n = scanner.nextInt();

        // Accept 'n' integers from the user
        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            numbers.add(num); // TreeSet will handle duplicates automatically
        }

        // Display the sorted integers
        System.out.println("Sorted unique integers: " + numbers);

        // Search for a particular element
        System.out.print("Enter an element to search: ");
        int searchElement = scanner.nextInt();

        // Check if the element is present in the set
        if (numbers.contains(searchElement)) {
            System.out.println(searchElement + " is present in the collection.");
        } else {
            System.out.println(searchElement + " is not present in the collection.");
        }

        scanner.close();
    }
}

