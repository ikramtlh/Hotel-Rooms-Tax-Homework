import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HotelTaxSystem {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        
        // Specify the path to the room data file
        Scanner fileScanner = new Scanner(new FileReader("C:\\Users\\HP\\Desktop\\ikram\\tinx\\Hotel-Rooms-Tax-Homework\\rooms.txt"));
        
        System.out.println("-- Hotel Tax System --");
        System.out.print("Do you want to specify a custom tax rate? (yes/no): ");
        String response = input.nextLine().trim().toLowerCase(); // Read user response

        double taxRate = 0.10; // Default tax rate

        if (response.equals("yes")) {
            System.out.print("Specify custom tax rate (integer percentage, e.g., 15 for 15%): ");
            if (input.hasNextInt()) {
                int taxRateInput = input.nextInt();
                taxRate = taxRateInput / 100.0; // Convert percentage to decimal
            } else {
                System.out.println("Invalid input. Using default tax rate of 10%.");
            }
        } else {
            System.out.println("Using default tax rate of 10%.");
        }

        double totalIncomeBeforeTax = 0.0;
        double totalTax = 0.0;

        // Process each room's data
        while (fileScanner.hasNextLine()) {
            String roomType = fileScanner.nextLine(); // Read room type
            if (!fileScanner.hasNextLine()) break; // Check if there's another line for bookings
            String bookingsLine = fileScanner.nextLine().trim(); // Read bookings
            if (!fileScanner.hasNextLine()) break; // Check if there's another line for room price
            String roomPriceLine = fileScanner.nextLine().trim(); // Read room price

            // Try to parse bookings and room price
            int bookings = 0;
            double roomPrice = 0.0;

            try {
                bookings = Integer.parseInt(bookingsLine);
                roomPrice = Double.parseDouble(roomPriceLine);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format in data. Skipping this entry.");
                continue; // Skip this entry if there's a format error
            }

            double incomeBeforeTax = bookings * roomPrice;
            double tax = incomeBeforeTax * taxRate;

            System.out.printf("%nRoom: %s%n", roomType);
            System.out.printf("Bookings: %d, Room Price: £%.2f%n", bookings, roomPrice);
            System.out.printf("Income Before Tax: £%.2f, Tax: £%.2f%n", incomeBeforeTax, tax);

            totalIncomeBeforeTax += incomeBeforeTax;
            totalTax += tax;
        }

        // Output the totals
        System.out.println();
        System.out.printf("Total Income Before Tax: £%.2f%n", totalIncomeBeforeTax);
        System.out.printf("Total Tax Collected: £%.2f%n", totalTax);

        fileScanner.close();
        input.close();
    }
}
