import java.util.Scanner;
public class Car extends Showroom implements Utility{
    String carName;
    String carColor;
    String carType;
    int carPrice;

    @Override
    public void get_details()
    {
        System.out.println("NAME: "+carName);
        System.out.println("COLOR: "+carColor);
        System.out.println("PRICE: "+carPrice);
        System.out.println("CAR TYPE: "+carType);
    }
    @Override
    public void set_details()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("======================= *** ENTER CAR DETAILS *** =======================");
        System.out.println();
        System.out.print("CAR NAME: ");
        carName = sc.nextLine();
        System.out.print(("CAR COLOR: "));
        carColor = sc.nextLine();
        System.out.print("CAR PRICE: ");
        carPrice = sc.nextInt();
        sc.nextLine();
        System.out.print("CAR TYPE(SEDAN/SUV/HATCHBACK): ");
        carType = sc.nextLine();
        totalCarInShowroom++;
    }
}
