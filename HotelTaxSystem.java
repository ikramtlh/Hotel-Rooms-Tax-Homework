import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HotelTaxSystem {

    private static final double DEFAULT_TAX_RATE = 0.10;
    private static final String FILE_PATH = "C:\\Users\\HP\\Desktop\\ikram\\tinx\\Hotel-Rooms-Tax-Homework\\rooms.txt"; // Define the path to the rooms file

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to specify a custom tax rate? (yes/no)");
        String response = scanner.nextLine();

        double taxRate;

        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Please enter the tax rate as a percentage (e.g., 15 for 15%):");
            taxRate = scanner.nextDouble() / 100.0;
            scanner.nextLine();
        } else {
            taxRate = DEFAULT_TAX_RATE;
            System.out.println("The default tax rate is " + (taxRate * 100) + "%");
        }

        File file = new File(FILE_PATH); // Use the predefined file path

        try {
            Scanner fileScanner = new Scanner(file);
            double totalIncomeBeforeTax = 0.0;
            double totalTax = 0.0;

            while (fileScanner.hasNextLine()) {
                String roomType = fileScanner.nextLine();
                int bookings = Integer.parseInt(fileScanner.nextLine());
                double roomPrice = Double.parseDouble(fileScanner.nextLine());

                double incomeBeforeTax = bookings * roomPrice;
                double tax = incomeBeforeTax * taxRate;

                System.out.println("\nRoom: " + roomType);
                System.out.println("Number of bookings: " + bookings);
                System.out.println("Room price: " + roomPrice);
                System.out.println("Income before tax: " + incomeBeforeTax);
                System.out.println("Tax: " + tax);

                totalIncomeBeforeTax += incomeBeforeTax;
                totalTax += tax;
            }

            System.out.println("\nTotal income before tax: " + totalIncomeBeforeTax);
            System.out.println("Total tax: " + totalTax);

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found. Please check the file path.");
        }

        scanner.close();
    }
}
