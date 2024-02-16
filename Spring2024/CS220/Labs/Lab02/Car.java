import java.util.IllegalFormatException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Car {

    private String make, model;
    private int mileage;

    public Car() {
        this.make = "No Make";
        this.model = "No Model";
        this.mileage = 0;
    }

    public Car(String make, String model, int mileage) {
        this.make = make;
        this.model = model;
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "Car [make=" + this.make + " model=" + this.model + " mileage=" + this.mileage + "]";
    }

    public static void main(String[] args) {

        try{

            Scanner scan = new Scanner(new File("./cars.txt")); // File path is relative to the project root

            String line;
            int count = 0;
            Car[] carArray = new Car[100];

            while(scan.hasNextLine()) {
                line = scan.nextLine(); // Read the entire line from the file
                String[] tokens = line.split(",\\s*"); // Split the line into tokens using the comma, and at least one space, as the delimiter
                carArray[count] = new Car(tokens[0], tokens[1], (Integer.parseInt(tokens[2]))); // Create new Car object and pass the tokens as arguments
                count++;
            }

            scan.close();

            // Print each Car object in the array
            for (int i = 0; i < carArray.length; i++) {
                System.out.println(carArray[i]);
            }

            // Print the number of Car objects written to the array for verification
            System.out.println("Car objects written to array: " + count);

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