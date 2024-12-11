import java.util.*;

public class A2{
    public static void main(String[] args) {
        // Create a LinkedList containing colors
        LinkedList<String> colors = new LinkedList<>();
        colors.add("red");
        colors.add("blue");
        colors.add("yellow");
        colors.add("orange");

        // i. Display the contents of the List using an Iterator
        System.out.println("Contents of the list using Iterator:");
        Iterator<String> iterator = colors.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // ii. Display the contents of the List in reverse order using a ListIterator
        System.out.println("\nContents of the list in reverse order using ListIterator:");
        ListIterator<String> listIterator = colors.listIterator(colors.size());
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }

        // iii. Create another list containing pink and green
        LinkedList<String> newColors = new LinkedList<>();
        newColors.add("pink");
        newColors.add("green");

        // Insert the elements of this new list between blue and yellow
        int index = colors.indexOf("blue") + 1; // Find index right after "blue"
        colors.addAll(index, newColors); // Add newColors after blue

        // Display the final contents of the list
        System.out.println("\nFinal contents of the list after inserting pink and green:");
        for (String color : colors) {
            System.out.println(color);
        }
    }
}
