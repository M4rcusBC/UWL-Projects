import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * This class reads car data from a file, converts the mileage from miles to
 * kilometers,
 * and writes the updated data to a new file.
 *
 * @author Marcus Clements
 * @since 2024-02-15
 */
public class Program2 {

    /**
     * The main method that drives the program.
     *
     * @param args Command-line arguments. Not used in this program.
     */
    public static void main(String[] args) {

        try {

            // Initialize BufferedReader, initialize Car array and set size to first line of file
            BufferedReader reader = new BufferedReader(new FileReader("./cars.txt"));
            String line;
            int carsWrittenToArray = 0; // Track cars written to array
            int arraySize = Integer.parseInt(reader.readLine());
            Car[] carArray = new Car[arraySize];

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",\\s*");

                // Convert miles to km, round up to nearest whole number, then cast to int
                int kilometers = (int) (Math.ceil((Double.parseDouble(tokens[2])) * 1.61));

                carArray[carsWrittenToArray] = new Car(tokens[0], tokens[1], kilometers); // Create new car object and
                                                                                          // add to array
                carsWrittenToArray++; // Increment cars written to array
            }

            reader.close(); // Close reader

            BufferedWriter writer = new BufferedWriter(new FileWriter("./cars-km.txt"));
            writer.write(Integer.toString(arraySize) + "\n");

            String writeLine; // String to write to file
            int carsWrittenToFile = 0;

            for (int i = 0; i < carArray.length; i++) {
                writeLine = carArray[i].getMake() + ", " + carArray[i].getModel() + ", " + carArray[i].getMileage()
                        + "\n";
                writer.write(writeLine);
                carsWrittenToFile++;
            }

            writer.close(); // Close writer

            System.out.println("Array size: " + arraySize);
            System.out.println("Car objects written to array: " + carsWrittenToArray);
            System.out.println("Car objects written to file: " + carsWrittenToFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}