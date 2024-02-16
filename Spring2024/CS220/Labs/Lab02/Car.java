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

    pu

    @Override
    public String toString() {
        return "Car [make=" + this.make + " model=" + this.model + " mileage=" + this.mileage + "]";
    }

}