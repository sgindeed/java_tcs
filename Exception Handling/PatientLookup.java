import java.util.Scanner;

public class PatientLookup {
    static String[] patientIDs = {"P101", "P102", null, "P104"}; // 'null' simulates a missing record

    // Method propagates exception
    public static void getPatient(int index) {
        if (patientIDs[index] == null) {
            throw new NullPointerException("Record at this index is empty/null.");
        }
        System.out.println("Patient ID found: " + patientIDs[index]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter Patient Index (0-3): ");
            int index = sc.nextInt();
            
            getPatient(index);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid Index. ID does not exist.");
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }
}