import java.util.Scanner;

class InvalidAgeException extends Exception { public InvalidAgeException(String s) { super(s); } }
class InvalidExperienceException extends Exception { public InvalidExperienceException(String s) { super(s); } }

public class PromotionValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean success = false;

        while (!success) { // Retry logic
            try {
                System.out.print("Enter Age: ");
                int age = sc.nextInt();
                System.out.print("Enter Years of Experience: ");
                int exp = sc.nextInt();

                if (age < 18) throw new InvalidAgeException("Employee is too young for promotion.");
                if (exp < 5) throw new InvalidExperienceException("Minimum 5 years experience required.");

                System.out.println("Validation Successful! Employee is eligible.");
                success = true; // Exit loop

            } catch (InvalidAgeException | InvalidExperienceException e) {
                System.out.println("Eligibility Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            } catch (Exception e) {
                System.out.println("Invalid Input. Try again.\n");
                sc.next(); // Clear buffer
            }
        }
    }
}