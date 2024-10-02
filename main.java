import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car{
    private String carID;
    private String carModel;
    private String carBrand;
    private double basePrice;
    private boolean isAvailable;

public Car(String carID, String carModel , String carBrand, double basePrice, boolean isAvailable){
      this.carID = carID;
      this.carModel = carModel;
      this.carBrand = carBrand;
      this.basePrice = basePrice;
      this.isAvailable = true;
}
public String getcarID(){
    return carID;
}

public String getModel(){
    return carModel;
}

public String getBrand(){
    return carBrand;
}

public double calculatePrice(int rentalDays){
    return basePrice + rentalDays;
}

public boolean isAvailable(){
    return isAvailable;
}

public void rent(){
    isAvailable = false;
}

public void returnCar(){
    isAvailable = true;
}


}
class Customer{
    private String customerId;
    private String name;

public void Customer(String customerId, String name) {
    this.customerId = customerId;
    this.name = name;
}

public String getcustomerID(){
    return customerId;
}

public String getname(){
    return name;
}
}

class Rental{
    private Car car;
    private Customer customer;
    private int days;

    public Rental(Car car , Customer customer,int days){
        this.car = car;
        this.customer=customer;
        this.days = days; 
    }

    public Car getCar(){
        return car;
    }

    public Customer getCustomer(){
        return customer;
    }
     
    public int getDays(){
        return days;
    }
}
class CarRentalSystem{
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

public CarRentalSystem(){
    cars = new ArrayList<>();
    customers = new ArrayList<>();
    rentals = new ArrayList<>();
}

public void addCar(Car car){
    cars.add(car);
}

public void addCustomer(Customer customer){
    customers.add(customer);
}
 public void rentCar(Car car, Customer customer, int days){
    if(car.isAvailable()){
        car.rent();
        rentals.add(new Rental(car, customer, days));
    }
    else{
        System.out.println("Car is not available for rent");
    }
    }

    public void returnCar(Car car){
        car.returnCar();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove= rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);
            System.out.println("Car returned successfully");
        } else{
            System.out.println("Car was not rented");
        }
    }

    public void menu(){
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("*****Car Rental System*****");
            System.out.println("1. Rent a car");
            System.out.println("2. Return a car");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("\n== Rent a Car ==\n");
                System.out.println("Enter your name: ");
                String customerName = scanner.nextLine();

                System.out.println("\nAvailable Cars:");
                for (Car car : cars) {
                    if (car.isAvailable()) {
                        System.out.println(car.getcarID() + " - " + car.getBrand() + " " + car.getModel());
                    }
                }
                System.out.println("\nEnter the car ID you want to rent: ");
                String carID = scanner.nextLine();

                System.out.println("Enter the number of days for rental: ");
                int rentalDays = scanner.nextInt();
                scanner.nextLine();

                Customer newCustomer = new Customer();
                addCustomer(newCustomer);

                Car selectedCar = null;
                for (Car car : cars) {
                    if (car.getcarID().equals(carID) && car.isAvailable()) {
                        selectedCar = car;
                        break;
                    }
                }
                if (selectedCar != null) {
                    double totalPrice = selectedCar.calculatePrice(rentalDays);
                    System.out.println("\n== Rental Information ==\n");
                    System.out.println("Customer ID: " + newCustomer.getcustomerID());
                    System.out.println("Customer Name: " + newCustomer.getname());
                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price: $%.2f%n", totalPrice);

                    System.out.println("\nConfirm rental (Y/N): ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        rentCar(selectedCar, newCustomer, rentalDays);
                        System.out.println("\nCar rented succesfully");
                    } else {
                        System.out.println("\nRental canceled");
                    }
                } else {
                    System.out.println("\n Invalid car selection or car not available for rent");
                }
             } else if (choice == 2) {
                System.out.println("\n== Return a Car ==\n");
                System.out.println("Enter the car ID you want to return: ");
                String carID = scanner.nextLine();

                Car carToReturn = null;
                for(Car car : cars) {
                    if (car.getcarID().equals(carID) && !car.isAvailable()) {
                        carToReturn = car;
                        break;
                    }
                }

                if(carToReturn != null) {
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getCar() == carToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }

                    if (customer != null) {
                        returnCar(carToReturn);
                        System.out.println("Car returned successfully by " + customer.getname());
                    } else {
                        System.out.println("Invalid car ID or car is not rented");
                    }
                } else if (choice == 3) {
                    break;
                } else{
                    System.out.println("Invalid choice, Please enter a valid option");
                }
             }

             System.out.println("\nThank you for using the Car Rental System!");   
            }
        }
        public class Main{
            public static void main(String[] args) {
                CarRentalSystem rentalSystem = new CarRentalSystem();

                Car car1 = new Car("C001", "X3", "BMW", 64.3, true);
                Car car2 = new Car("C002", "Droptail", "Rolls Royce", 209, true);
                Car car3 = new Car("C003", "Thar", "Mahindra", 13.9, true);
                rentalSystem.addCar(car1);
                rentalSystem.addCar(car2);
                rentalSystem.addCar(car3);
                
                rentalSystem.menu();
            }
        }
    }