import java.io.*;
import java.util.Scanner;

public class DoctorFileDetails {
    public static void main(String[] args) {
        // NOTE: Create a file named 'doctors.txt' in your project folder to test success.
        File file = new File("doctors.txt");
        Scanner fileReader = null;

        try {
            fileReader = new Scanner(file);
            System.out.println("--- Doctor Details ---");
            while (fileReader.hasNextLine()) {
                System.out.println(fileReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: The file 'doctors.txt' was not found.");
        } finally {
            if (fileReader != null) {
                fileReader.close();
                System.out.println("File resource closed.");
            }
        }
    }
}