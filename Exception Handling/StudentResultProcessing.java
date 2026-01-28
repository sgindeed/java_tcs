import java.util.Scanner;
import java.util.InputMismatchException;

class InvalidMarkException extends Exception {
    public InvalidMarkException(String message) { super(message); }
}

public class StudentResultProcessing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name;
        int rollNo;
        int[] marks = new int[5];
        int total = 0;

        try {
            System.out.print("Enter Student Name: ");
            name = sc.nextLine();
            System.out.print("Enter Roll Number: ");
            rollNo = sc.nextInt();

            System.out.println("Enter marks for 5 subjects:");
            for (int i = 0; i < 5; i++) {
                marks[i] = sc.nextInt();
                
                // Requirement: Throw exception if mark < 0 or > 100
                if (marks[i] < 0 || marks[i] > 100) {
                    throw new InvalidMarkException("Marks must be between 0 and 100.");
                }
                total += marks[i];
            }

            // Simulate Invalid Array Access (for demonstration as per prompt)
            // System.out.println(marks[10]); // This would catch ArrayIndexOutOfBoundsException

            double average = total / 5.0;
            System.out.println("Total: " + total);
            System.out.println("Average: " + average);

        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter valid numeric values for roll number and marks.");
        } catch (InvalidMarkException e) {
            System.out.println("Result Error: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Tried to access invalid subject index.");
        } finally {
            sc.close();
        }
    }
}
