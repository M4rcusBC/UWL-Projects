import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Program2 {
   
    public static void main(String[] args) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("./cars.txt"));
            String line;
            int carsWrittenToArray = 0;
            int arraySize = Integer.parseInt(reader.readLine());
            Car[] carArray = new Car[arraySize];

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",\\s*");

                // Convert miles to km, round up to nearest whole number, then cast to int
                int kilometers = (int)(Math.ceil((Double.parseDouble(tokens[2])) * 1.61));

                carArray[carsWrittenToArray] = new Car(tokens[0], tokens[1], kilometers);
                carsWrittenToArray++;
            }

            reader.close();

            String writeLine;            

            BufferedWriter writer = new BufferedWriter(new FileWriter("./cars-km.txt"));
            writer.write(Integer.toString(arraySize) + "\n");


            int carsWrittenToFile = 0;
            for (int i = 0; i < carArray.length; i++) {
                writeLine = carArray[i].getMake() + ", " + carArray[i].getModel() + ", " + carArray[i].getMileage() + "\n";
                writer.write(writeLine);
                carsWrittenToFile++;
            }

            writer.close();


            System.out.println("Array size: " + arraySize);
            System.out.println("Car objects written to array: " + carsWrittenToArray);
            System.out.println("Car objects written to file: " + carsWrittenToFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        }

    }

}
