import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.EOFException;
import java.io.FileNotFoundException;

/**
 * This class reads car data from a text file, writes the data to a binary file, 
 * then reads the data from the binary file and prints it to the console.
 *
 * @author Marcus Clements
 * @since 2024-02-15
 */
public class Program3 {

    /**
     * The main method that drives the program.
     *
     * @param args Command-line arguments. Not used in this program.
     */
    public static void main(String[] args) {

        try {

            // Initialize BufferedReader to read from "cars.txt" and DataOutputStream to write to "cars.bin"
            BufferedReader br = new BufferedReader(new FileReader("./cars.txt"));
            DataOutputStream output = new DataOutputStream(new FileOutputStream("./cars.bin"));

            // Read first line of file to get list size
            int listSize = Integer.parseInt(br.readLine());
            
            String inputLine; // String to read from file

            output.writeInt(listSize);
            output.writeUTF("\n");

            while ((inputLine = br.readLine()) != null) {
                String[] tokens = inputLine.split(",\\s*"); // Split line into tokens

                // If line does not contain 3 tokens, throw IOException
                if (tokens.length != 3) {
                    throw new IOException();
                }

                // Write tokens to "cars.bin"
                output.writeUTF(tokens[0]);
                output.writeUTF(tokens[1]);
                output.writeInt(Integer.parseInt(tokens[2]));
                output.writeUTF("\n");
                output.flush(); // Flush output stream after each write
            }

            // Close BufferedReader and DataOutputStream, print list size for verification
            br.close();
            output.close();
            System.out.println("List size: " + listSize);

            System.out.println("Now reading from \"cars.bin\" and displaying data: \n");

            // Initialize DataInputStream to read from "cars.bin"
            DataInputStream input = new DataInputStream(new FileInputStream("./cars.bin"));
            String readMake, readModel;
            int readMileage, listSizeFromBinaryFile;

            // Read list size from "cars.bin" and print to console
            listSizeFromBinaryFile = input.readInt(); 
            System.out.println(listSizeFromBinaryFile);

            for (int i = 0; i < listSizeFromBinaryFile; i++) {
                try {
                    // Read make, model, and mileage from "cars.bin" and print to console
                    input.readUTF();
                    readMake = input.readUTF();
                    readModel = input.readUTF();
                    readMileage = input.readInt();
                    System.out.printf("%s, %s, %d\n", readMake, readModel, readMileage);
                } catch (EOFException e) {
                    System.err.println("List index: " + i);
                    break; // Break loop if end of file is reached
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            input.close(); // Close DataInputStream

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}