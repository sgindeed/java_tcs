import java.util.Scanner;

class InvalidDaysException extends Exception {
    public InvalidDaysException(String s) { super(s); }
}

public class SalaryCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter Basic Salary: ");
            double basic = sc.nextDouble();
            System.out.print("Enter Working Days: ");
            int days = sc.nextInt();

            if (days <= 0 || days > 31) {
                throw new InvalidDaysException("Working days must be between 1 and 31.");
            }

            double dailySalary = basic / 30;
            double monthlySalary = dailySalary * days;

            System.out.println("Calculated Monthly Salary: " + monthlySalary);

        } catch (InvalidDaysException e) {
            System.out.println("Data Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid numeric input.");
        } finally {
            System.out.println("Salary calculation process finished.");
            sc.close();
        }
    }
}