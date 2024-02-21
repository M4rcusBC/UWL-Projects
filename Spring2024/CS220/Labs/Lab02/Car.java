/**
 * This class represents a Car with make, model and mileage.
 *
 * @author Marcus Clements
 * @since 2024-02-15
 */
public class Car {

    private String make, model;
    private int mileage;

    /**
     * Default constructor that initializes the car with default values.
     */
    public Car() {
        this.make = "No Make";
        this.model = "No Model";
        this.mileage = 0;
    }

    /**
     * Constructor that initializes the car with provided make, model and mileage.
     *
     * @param make    The make of the car.
     * @param model   The model of the car.
     * @param mileage The mileage of the car.
     */
    public Car(String make, String model, int mileage) {
        this.make = make;
        this.model = model;
        this.mileage = mileage;
    }

    /**
     * Returns the make of the car.
     *
     * @return A string representing the make of the car.
     */
    public String getMake() {
        return this.make;
    }

    /**
     * Sets the make of the car.
     *
     * @param make The make of the car.
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Returns the model of the car.
     *
     * @return A string representing the model of the car.
     */
    public String getModel() {
        return this.model;
    }

    /**
     * Sets the model of the car.
     *
     * @param model The model of the car.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Returns the mileage of the car.
     *
     * @return An integer representing the mileage of the car.
     */
    public int getMileage() {
        return this.mileage;
    }

    /**
     * Sets the mileage of the car.
     *
     * @param mileage The mileage of the car.
     */
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    /**
     * Returns a string representation of the car.
     *
     * @return A string representation of the car.
     */
    @Override
    public String toString() {
        return "Car [make=" + this.make + ", model=" + this.model + ", mileage=" + this.mileage + "]";
    }
}