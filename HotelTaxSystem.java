import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class HotelTaxSystem {
    public static void main(String[] args) throws FileNotFoundException {
        // Scanner for user input from the keyboard
        Scanner input = new Scanner(System.in);
        
        // Scanner to read data from the rooms.txt file
        // Note: Update the path if the file is in a different location
        Scanner file = new Scanner(new FileReader("C:\\Users\\HP\\Desktop\\ikram\\tinx\\Hotel-Rooms-Tax-Homework\\rooms.txt"));
        
        // Display the application header
        System.out.println("-- Room Tax System --");

        // Ask the user if they want to enter a specific tax rate
        System.out.print("Do you want to enter a specific tax rate? (YES/NO): ");
        String userResponse = input.nextLine();  // Read user's response as a String
        double taxRate = 20.0; // Default tax rate in percent (20%)
        
        // Check if the user entered "YES" (case-insensitive)
        if (userResponse.equalsIgnoreCase("YES")) {
            System.out.print("Specify Tax Rate (%) : "); // Prompt user to enter custom tax rate
            // Check if the next input is a valid double (e.g., 15.5)
            if (input.hasNextDouble()) {
                taxRate = input.nextDouble(); // Update tax rate with user's input
            } else {
                System.out.println("Invalid tax rate input. Using default rate."); // Error message for invalid input
            }
        }

        // Variables to keep track of total income and total tax for all room types
        double totalIncome = 0;
        double totalTax = 0;

        // Loop through each line of the file to process room data
        while (file.hasNext()) {
            String roomType = file.next();            // Room type (e.g., "Single")
            int bookings = Integer.parseInt(file.next());  // Number of bookings (converted to an integer)
            double roomPrice = Double.parseDouble(file.next());  // Price per room (converted to a double)

            // Calculate income for the room type (bookings * room price)
            double income = bookings * roomPrice;
            
            // Calculate tax for the room type (income * tax rate / 100)
            double tax = income * (taxRate / 100);

            // Add income and tax for this room type to the totals
            totalIncome += income;
            totalTax += tax;

            // Display details for each room type
            System.out.printf("Room Type: %s, Bookings: %d, Room Price: £%.2f, Income: £%.2f, Tax: £%.2f%n",
                    roomType, bookings, roomPrice, income, tax);
        }

        // Display total income and tax for all rooms
        System.out.printf("\nTotal Income : £%.2f%n", totalIncome);
        System.out.printf("Total Tax: £%.2f%n", totalTax);
        
        // Close the file and input scanners to free resources
        file.close();
        input.close();
    }
}
