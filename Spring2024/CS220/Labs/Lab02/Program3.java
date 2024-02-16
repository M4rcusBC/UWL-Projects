import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Program3 {

    public static void main(String[] args) {

        try {

            DataInputStream input = new DataInputStream(new FileInputStream("./cars.dat"));
            DataOutputStream output = new DataOutputStream(new FileOutputStream("./cars-km.dat"));

            int arraySize = input.readInt();
            Car[] carArray = new Car[arraySize];

            for (int i = 0; i < arraySize; i++) {
                carArray[i] = new Car(input.readUTF(), input.readUTF(), input.readInt());
            }
            
        } catch () {}
        
    }
  
}