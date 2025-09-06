import java.time.Year;
public class Q1_3PP {
    // Instance variables
    String brand;
    String model;
    int year;
    String color;
    boolean isRunning;
    // Constructor
    public Q1_3PP(String brand, String model, int year, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.isRunning = false;
    }
    // Methods
    public void startEngine() {
        isRunning = true;
        System.out.println(brand + " " + model + " engine started.");
    }
    public void stopEngine() {
        isRunning = false;
        System.out.println(brand + " " + model + " engine stopped.");
    }
    public void displayInfo() {
        System.out.println("Car Info: " + brand + " " + model + " (" + year + "), Color: " + color + "," + isRunning);
    }
    public int getAge() {
        return Year.now().getValue() - year;
    }
    public static void main(String[] args) {
        Q1_3PP car1 = new Q1_3PP("Toyota", "Camry", 2018, "Red");
        Q1_3PP car2 = new Q1_3PP("Tesla", "Model S", 2021, "Black");
        Q1_3PP car3 = new Q1_3PP("Ford", "Mustang", 2015, "Blue");

        car1.startEngine();
        car1.displayInfo();
        System.out.println("Car Age: " + car1.getAge() + " years\n");
        car2.displayInfo();
        car2.startEngine();
        car2.stopEngine();
        System.out.println("Car Age: " + car2.getAge() + " years\n");
        car3.displayInfo();
        System.out.println("Car Age: " + car3.getAge() + " years");
// Real-world analogy: Each car has its own state and behavior.
    }
}