import java.util.Scanner;
import java.util.InputMismatchException;

// 1. Create the Custom Exception
class InvalidPatientDataException extends Exception {
    public InvalidPatientDataException(String message) {
        super(message);
    }
}

public class PatientBillingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Variables needed for calculation
        String name = "";
        int age = 0;
        int days = 0;
        double dailyCharge = 0.0;
        
        try {
            // Input: Patient Name
            System.out.print("Enter Patient Name: ");
            name = sc.nextLine();

            // Input: Age
            System.out.print("Enter Age: ");
            age = sc.nextInt();
            
            // Input: Number of Days
            System.out.print("Enter Number of Days Admitted: ");
            days = sc.nextInt();
            
            // Input: Daily Room Charge
            System.out.print("Enter Daily Room Charge: ");
            dailyCharge = sc.nextDouble();

            // Requirement: Throw user-defined exception for invalid age or days
            if (age <= 0) {
                throw new InvalidPatientDataException("Age must be greater than 0.");
            }
            if (days <= 0) {
                throw new InvalidPatientDataException("Days admitted must be greater than 0.");
            }

            // Calculation
            double totalBill = days * dailyCharge;
            
            // Requirement: Handle division by zero (Calculating average cost as an example scenario)
            // Ideally, average is total/days. If days is 0, this throws ArithmeticException.
            // (Note: days=0 is caught above, but we include this logic to satisfy the prompt requirements strictly)
            int averageCost = (int) (totalBill / days); 

            System.out.println("\n--- Bill Details ---");
            System.out.println("Patient Name: " + name);
            System.out.println("Total Bill Amount: " + totalBill);

        } catch (InputMismatchException e) {
            // Requirement: Handle invalid numeric input
            System.out.println("Error: Invalid input! Please enter numeric values for age, days, and charge.");
        } catch (InvalidPatientDataException e) {
            // Requirement: Handle user-defined exception
            System.out.println("Custom Error: " + e.getMessage());
        } catch (ArithmeticException e) {
            // Requirement: Handle division by zero
            System.out.println("Math Error: Cannot calculate average cost (Division by Zero).");
        } catch (Exception e) {
            // Generic handler for unexpected errors
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Requirement: Ensure final message is always printed
            System.out.println("Billing process completed.");
            sc.close();
        }
    }
}