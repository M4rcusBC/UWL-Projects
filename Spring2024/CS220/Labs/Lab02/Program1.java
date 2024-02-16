import java.util.IllegalFormatException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Program1 {

    public static void main(String[] args) {

        try {

            Scanner scan = new Scanner(new File("./cars.txt")); // File path is relative to the project root

            String line;
            int count = 0;
            int arraySize = Integer.parseInt(scan.nextLine());
            Car[] carArray = new Car[arraySize];
            scan.nextLine();

            while (scan.hasNextLine()) {
                line = scan.nextLine(); // Read the entire line from the file

                // Split the line into tokens using the comma, and at least one space, as the delimiter
                String[] tokens = line.split(",\\s*");
                
                // Create new Car object and pass the tokens as arguments
                carArray[count] = new Car(tokens[0], tokens[1], (Integer.parseInt(tokens[2]))); 
                count++;
            }

            scan.close();

            // Print each Car object in the array
            for (int i = 0; i < carArray.length; i++) {
                System.out.println(carArray[i]);
            }

            // Print the array size and number of Car objects written to the array for verification
            System.out.println("Array size: " + arraySize + "\nCar objects written to array: " + count);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InputMismatchException f) {
            f.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException g) {
            g.printStackTrace();
        } catch (IllegalFormatException h) {
            h.printStackTrace();
        }

    }

}