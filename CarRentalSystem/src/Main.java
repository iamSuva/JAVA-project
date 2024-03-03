import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
class Car{
    private String carId;
    private String brand;
    private String model;
    private boolean isAvailable;
    private double basePricePerDay;
    public Car(String carId,String brand,String model,double basePricePerDay)
    {
        this.carId=carId;
        this.brand=brand;
        this.model=model;
        this.basePricePerDay=basePricePerDay;
        this.isAvailable=true;
    }
    public String getCarId()
    {
        return carId;
    }
    public String getBrand()
    {
        return brand;
    }
    public String getModel()
    {
        return model;
    }
    public boolean isAvailable()
    {
        return isAvailable;
    }
    public  double calculateRentCharge(int rentalday)
    {
        return rentalday*basePricePerDay;
    }
    public  void rentCar()
    {
        isAvailable=false;//car is not available
    }
    public void returnCar()
    {
        isAvailable=true; //back to available
    }
};
class Customer{
    private String customerName;
    private String customerId;
    public Customer(String customerId,String customerName)
    {
        this.customerId=customerId;
        this.customerName=customerName;
    }
   public String getCustomerId()
   {
       return customerId;
   }
   public String getCustomerName()
   {
       return customerName;
   }
};
class Rental{
    private Car  car;
    private Customer customer;
    private int days;
    public Rental(Car car,Customer customer,int days)
    {
        this.car=car;
       this.customer=customer;
       this.days=days;
    }
    public Car getCar()
    {
        return car;
    }
    public Customer getCustomer()
    {
        return customer;
    }
    public int getDays()
    {
        return days;
    }
};
class CarRentalSystem{
    List<Car>cars;
    List<Customer>customers;
    List<Rental>rentals;
    public CarRentalSystem()
    {
        cars=new ArrayList<>();
        customers=new ArrayList<>();
        rentals=new ArrayList<>();
    }
    public void addCar(Car car)
    {
        cars.add(car);
    }
    public void addCustomer(Customer customer)
    {
        customers.add(customer);
    }
    public void  giveRentCar(Car car,Customer customer,int days)
    {
        if(car.isAvailable())
        {
            car.rentCar();;
            rentals.add(new Rental(car,customer,days));
        }else {
            System.out.println("car is nor available for rent.");
        }
    }
    public void returnRentalCar(Car car)
    {
        Rental removeRentCar=null;
        for(Rental rental:rentals)
        {
            if(rental.getCar()==car)//find  rent car
            {
                removeRentCar=rental;
                break;
            }
        }
        if(removeRentCar!=null)
        {
            rentals.remove(removeRentCar);
            car.returnCar();
            System.out.println("Succefully car has been returned");
        }
        else {
            System.out.println("car was not returned.");
        }
    }
    public  void menu()
    {
      //  System.out.printf("Welcome to Car Rental System :-");
        Scanner sc=new Scanner(System.in);
        while(true) {
            System.out.println("**************Welcome to Car Rental System************* ");
            System.out.println("For Rent a car enter 1");
            System.out.println("For Return a car enter 2");
            System.out.println("For exit enter 3");
            System.out.println("Please enter your choice : ");
            int choice = sc.nextInt();
          sc.nextLine();//nextline buffer
           // Car selectedCar = null;
            if (choice == 1) {
                System.out.println("\n RENT a car : \n");

                System.out.println("\n Available cars: ");
                for (Car car : cars) {
                    if (car.isAvailable()) {
                        System.out.println(car.getCarId() + " - - - " + car.getBrand() + "- - - " + car.getModel());
                    }
                }
                System.out.println("Enter your customer name : ");
                String customerName = sc.nextLine();
                System.out.println("Enter the carid you want  ");
                String cardId = sc.nextLine();
                System.out.println("Enter the no days you want for rent : ");
                int days = sc.nextInt();
                sc.nextLine();
                Customer newCustomer = new Customer("CUS" + customers.size() + 1, customerName);
                addCustomer(newCustomer);
               Car selectedCar = null;
                for (Car car : cars) {

                    if (car.getCarId().equals(cardId) && car.isAvailable()) {
                        selectedCar = car;
                        break;
                    }
                }

                if (selectedCar != null) {
                    double totalRentPrice = selectedCar.calculateRentCharge(days);
                    System.out.println("Your rental information : ");
                    System.out.println("Customer ID : "+newCustomer.getCustomerId());
                    System.out.println("Customer Name : "+newCustomer.getCustomerName());
                    System.out.println("Car details "+selectedCar.getCarId()+" - "+selectedCar.getBrand()+" -  "+selectedCar.getModel());
                    System.out.println("Total rent days : "+days);
                    System.out.println("Total rent prices : "+totalRentPrice);
                    System.out.println("Please confirm (Y/N) ? ");
                    String confirm=sc.nextLine();
                    if(confirm.equalsIgnoreCase("Y"))
                    {
                        giveRentCar(selectedCar,newCustomer,days);
                        System.out.println("Car rented successfully by : "+newCustomer.getCustomerName()+"\n");

                    }
                    else {
                        System.out.println("Car rental cenceled.");
                    }

                }
                else {
                    System.out.println("Invalid car or Car is not available.Please check another.");
                }

            }
            else if(choice==2)
            {
                System.out.println("\n Return a car \n");
                System.out.println("\n Enter the the car id you want to return .");
                String cardId=sc.nextLine();
                Car returnedCar=null;
                for(Car car:cars)
                {
                    if(car.getCarId().equals(cardId) && car.isAvailable()==false)
                    {
                        returnedCar=car;
                        break;
                    }
                }
                if(returnedCar!=null)
                {
                    Customer customer=null;
                    for(Rental rental:rentals)
                    {
                        if(rental.getCar()==returnedCar)
                        {
                            customer=rental.getCustomer();
                            break;
                        }
                    }
                    if(customer!=null)
                    {
                        returnRentalCar(returnedCar);
                        System.out.println("Successfully car has been returned by "+customer.getCustomerName());

                    }
                    else {
                        System.out.println("Car was not rented or missing information.");

                    }
                }else {
                    System.out.println("Invalid car id or car is not rented.");
                }
            }
            else if(choice==3)
            {
                break;
            }
            else {
                System.out.println("Please enter a valid choice.");
            }
        }
        System.out.println("*************Thank You for using CAR RENTAL SYSTEM.*************");
    }
}
public class Main {
    public static void main(String[] args) {
       CarRentalSystem rentalSystem=new CarRentalSystem();
      Car car1=new Car("c001","Mahindra","Thar",500);
        Car car2=new Car("c002","Tata","Nano",200);
        Car car3=new Car("c001","Tyota","Swift",300);
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);
        rentalSystem.menu();
    }
}