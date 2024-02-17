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

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMileage() {
        return this.mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "Car [make=" + this.make + " model=" + this.model + " mileage=" + this.mileage + "]";
    }

}