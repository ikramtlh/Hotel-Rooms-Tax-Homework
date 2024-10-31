import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Scanner;

public class HotelTaxSystem {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        Scanner file = new Scanner(new FileReader("C:\\Users\\HP\\Desktop\\ikram\\tinx\\Hotel-Rooms-Tax-Homework\\rooms.txt"));
        
        System.out.println("-- Room Tax System --");

        System.out.print("Specify Custom Tax Rate MINJ: Y/N: ");
        String minj = input.nextLine();
        double taxRate = 20.0; // Default tax rate
        
        if (minj.equalsIgnoreCase("Y")) {
            System.out.print("Specify Tax Rate (%) : "); // Clearer prompt for the tax input
            
            // Clear the buffer before reading the next input
            if (input.hasNextDouble()) {
                taxRate = input.nextDouble();
                input.nextLine();  // Consume the newline left over
            } else {
                System.out.println("Invalid tax rate input. Using default rate.");
                input.nextLine();  // Clear invalid input from buffer
            }
        }

        DecimalFormat df = new DecimalFormat("£0.00");
        double totalIncome = 0;
        double totalTax = 0;

        while (file.hasNext()) {
            String roomType = file.next();
            int bookings = Integer.parseInt(file.next());
            double roomPrice = Double.parseDouble(file.next());

            double income = bookings * roomPrice;
            double tax = income * (taxRate / 100);

            totalIncome += income;
            totalTax += tax;

            System.out.printf("Room Type: %s, Bookings: %d, Room Price: %s, Income: %s, Tax: %s%n",
                    roomType, bookings, df.format(roomPrice), df.format(income), df.format(tax));
        }

        // Print total income and total tax
        System.out.printf("Total Income : %s%n", df.format(totalIncome));
        System.out.printf("Total Tax: %s%n", df.format(totalTax));
        
        file.close();
        input.close();
    }
}
