import java.util.Scanner;

class InsufficientStockException extends Exception {
    public InsufficientStockException(String msg) { super(msg); }
}

public class MedicineStock {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int available = 100; // Example stock

        try {
            System.out.print("Enter Medicine Name: ");
            String med = sc.nextLine();
            System.out.print("Enter Requested Quantity: ");
            int request = sc.nextInt();

            if (request < 0) throw new IllegalArgumentException("Quantity cannot be negative.");
            if (request > available) throw new InsufficientStockException("Request exceeds available stock (" + available + ").");

            System.out.println("Stock Verified. Dispensing " + request + " units of " + med);

        } catch (InsufficientStockException e) {
            System.out.println("Stock Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Input Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid numeric input.");
        }
    }
}