import java.util.ArrayList;
import java.util.List;

class Car{
    private int carID;
    private String carModel;
    private String carBrand;
    private double basePrice;
    private boolean isAvailable;

public Car(int carID, String carModel , String carBrand, double basePrice, boolean isAvailable){
      this.carID = carID;
      this.carModel = carModel;
      this.carBrand = carBrand;
      this.basePrice = basePrice;
      this.isAvailable = true;
}
public int getcarID(){
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
    private int customerId;
    private String name;

public void Customer(int customerId, String name){
    this.customerId = customerId;
    this.name = name;
}

public int getcustomerID(){
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
 }