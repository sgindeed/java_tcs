import java.util.Scanner;

class LeaveLimitExceededException extends Exception {
    public LeaveLimitExceededException(String s) { super(s); }
}

public class LeaveManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalLeaves = 20; // Available balance

        try {
            System.out.print("Enter Employee ID: ");
            String id = sc.next();
            System.out.print("Enter Leaves Requested: ");
            int req = sc.nextInt();

            if (req > totalLeaves) {
                throw new LeaveLimitExceededException("Requested leaves (" + req + ") exceed balance (" + totalLeaves + ").");
            }

            System.out.println("Leave Approved for ID: " + id);

        } catch (LeaveLimitExceededException e) {
            System.out.println("Request Denied: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input.");
        }
    }
}