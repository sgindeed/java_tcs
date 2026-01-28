import java.util.Scanner;

class LowAttendanceException extends Exception {
    public LowAttendanceException(String message) { super(message); }
}

public class AttendanceSystem {
    
    // Method that propagates the exception to main()
    public static void checkEligibility(int total, int attended) throws LowAttendanceException {
        if (total == 0) throw new ArithmeticException("Total classes cannot be zero.");
        
        double percentage = ((double) attended / total) * 100;
        System.out.println("Attendance Percentage: " + percentage + "%");
        
        if (percentage < 75) {
            throw new LowAttendanceException("Student is NOT eligible for exams (Attendance < 75%).");
        } else {
            System.out.println("Student is eligible for exams.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter Total Classes: ");
            int total = sc.nextInt();
            System.out.print("Enter Classes Attended: ");
            int attended = sc.nextInt();

            checkEligibility(total, attended);

        } catch (LowAttendanceException e) {
            System.out.println("Eligibility Error: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Math Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid Input.");
        }
    }
}