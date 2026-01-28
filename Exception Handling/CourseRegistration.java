import java.util.Scanner;

class CreditLimitExceededException extends Exception {
    public CreditLimitExceededException(String message) { super(message); }
}

public class CourseRegistration {
    public static void validateCredits(int credits) throws CreditLimitExceededException {
        int MAX_CREDITS = 25; // Example limit
        if (credits > MAX_CREDITS) {
            throw new CreditLimitExceededException("Credit limit exceeded! Max allowed is " + MAX_CREDITS);
        }
        System.out.println("Registration Successful for " + credits + " credits.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter Course Code: ");
            String code = sc.next();
            System.out.print("Enter Credits: ");
            int credits = sc.nextInt();

            validateCredits(credits);
            
        } catch (CreditLimitExceededException e) {
            System.out.println("Registration Failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input.");
        }
    }
}