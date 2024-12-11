import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class B1{
    private static HashMap<String, String> cityMap = new HashMap<>();
    private static JTextField cityNameField, cityCodeField, searchField;
    private static JTextArea resultArea;

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("City and STD Code Management");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        // Add components to the panel
        JLabel cityNameLabel = new JLabel("City Name:");
        cityNameField = new JTextField();
        JLabel cityCodeLabel = new JLabel("City Code:");
        cityCodeField = new JTextField();
        JLabel searchLabel = new JLabel("Search City:");
        searchField = new JTextField();
        
        JButton addButton = new JButton("Add City");
        JButton removeButton = new JButton("Remove City");
        JButton searchButton = new JButton("Search City");
        
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        panel.add(cityNameLabel);
        panel.add(cityNameField);
        panel.add(cityCodeLabel);
        panel.add(cityCodeField);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(searchLabel);
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(new JScrollPane(resultArea));

        frame.add(panel);
        frame.setVisible(true);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCity();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCity();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCity();
            }
        });
    }

    // Method to add a city and its STD code
    private static void addCity() {
        String cityName = cityNameField.getText().trim();
        String cityCode = cityCodeField.getText().trim();

        if (!cityName.isEmpty() && !cityCode.isEmpty()) {
            if (!cityMap.containsKey(cityName)) {
                cityMap.put(cityName, cityCode);
                resultArea.setText("City " + cityName + " with code " + cityCode + " added successfully.");
            } else {
                resultArea.setText("City already exists in the collection.");
            }
        } else {
            resultArea.setText("Please enter both city name and city code.");
        }

        cityNameField.setText("");
        cityCodeField.setText("");
    }

    // Method to remove a city from the collection
    private static void removeCity() {
        String cityName = cityNameField.getText().trim();

        if (!cityName.isEmpty()) {
            if (cityMap.containsKey(cityName)) {
                cityMap.remove(cityName);
                resultArea.setText("City " + cityName + " removed successfully.");
            } else {
                resultArea.setText("City not found in the collection.");
            }
        } else {
            resultArea.setText("Please enter a city name to remove.");
        }

        cityNameField.setText("");
    }

    // Method to search for a city and display its STD code
    private static void searchCity() {
        String cityName = searchField.getText().trim();

        if (!cityName.isEmpty()) {
            if (cityMap.containsKey(cityName)) {
                String cityCode = cityMap.get(cityName);
                resultArea.setText("City: " + cityName + " - STD Code: " + cityCode);
            } else {
                resultArea.setText("City not found.");
            }
        } else {
            resultArea.setText("Please enter a city name to search.");
        }

        searchField.setText("");
    }
}
