import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HotelTaxSystem {

    // Default tax rate
    private static final double DEFAULT_TAX_RATE = 0.10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the file path
        System.out.println("Please enter the file path containing the room data (e.g., rooms.txt):");
        String filePath = scanner.nextLine();  // Read the file path entered by the user
        System.out.println("File path: " + filePath);  // Display the file path

        // Ask if the manager wants to specify a custom tax rate
        System.out.println("Do you want to specify a custom tax rate? (yes/no)");
        String response = scanner.nextLine();

        double taxRate;

        if (response.equalsIgnoreCase("yes")) {
            // The manager enters a custom tax rate
            System.out.println("Please enter the tax rate as a percentage (e.g., 15 for 15%):");
            taxRate = scanner.nextDouble() / 100.0;  // Convert percentage to decimal

            // Consume the rest of the line to avoid issues with nextLine()
            scanner.nextLine();
        } else {
            // Use the default tax rate
            taxRate = DEFAULT_TAX_RATE;
            System.out.println("The default tax rate is " + (taxRate * 100) + "%");
        }

        // Open and read the specified rooms.txt file
        File file = new File(filePath);

        try {
            Scanner fileScanner = new Scanner(file);
            double totalIncomeBeforeTax = 0.0;
            double totalTax = 0.0;

            // Read rooms one by one from the file
            while (fileScanner.hasNextLine()) {
                String roomType = fileScanner.nextLine();  // Type of the room
                int bookings = Integer.parseInt(fileScanner.nextLine());  // Number of bookings
                double roomPrice = Double.parseDouble(fileScanner.nextLine());  // Price of the room

                // Calculate income before tax and the tax
                double incomeBeforeTax = bookings * roomPrice;
                double tax = incomeBeforeTax * taxRate;

                // Display information for each room
                System.out.println("\nRoom: " + roomType);
                System.out.println("Number of bookings: " + bookings);
                System.out.println("Room price: " + roomPrice);
                System.out.println("Income before tax: " + incomeBeforeTax);
                System.out.println("Tax: " + tax);

                // Add to totals
                totalIncomeBeforeTax += incomeBeforeTax;
                totalTax += tax;
            }

            // Display the overall totals
            System.out.println("\nTotal income before tax: " + totalIncomeBeforeTax);
            System.out.println("Total tax: " + totalTax);

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found. Please check the file path.");
        }

        scanner.close();
    }
}
