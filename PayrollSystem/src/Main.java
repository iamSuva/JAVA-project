import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

abstract class Employee{
    private String name;
    private int id;
    public Employee(String name,int id)
    {
        this.name=name;
        this.id=id;
    }
    public String getName()
    {
        return  name;
    }
    public  int getId()
    {
        return id;
    }
   public abstract  double calculateSalary(); //only declaration will defined in derived class
   //override polymorphism
    @Override
    public   String toString()  //overiding
    {
        return "Employee [name= "+name+" , id= "+id+" , Salary= "+calculateSalary()+" ]";
    }
};
 class FullTimeEmployee extends Employee{
    private double monthlySalary;
    public FullTimeEmployee(String name,int id,double monthlySalary)
    {
        super(name,id);
        this.monthlySalary=monthlySalary;
    }
    @Override
     public double calculateSalary() //polymorphism
    {
        return monthlySalary;
    }
}
class  PartTimeEmployee extends Employee{
     private int hoursWorked;
     private double hourlyRate;
     public PartTimeEmployee(String name,int id,int hoursWorked,double hourlyRate)
     {
         super(name,id);
         this.hoursWorked=hoursWorked;
         this.hourlyRate=hourlyRate;
     }
     @Override
    public double calculateSalary()
     {
         return hourlyRate*hoursWorked; //per hour --500rs
     }
}
class PayrollSystem{
     private ArrayList<Employee> employeeList; //list contain each employee where each employee has name id salary
     public PayrollSystem()
     {
         employeeList=new ArrayList<>();
     }
     public void addEmployee(Employee employee)
     {
         employeeList.add(employee);
     }
     public void removeEmployee(int empid)
     {
         Employee employeeToRemove=null;//variable
         for(Employee employee:employeeList)
         {
             if(employee.getId()==empid)
             {
                 employeeToRemove=employee;
                 break;
             }
         }
         if(employeeToRemove!=null)
         {
             employeeList.remove(employeeToRemove);
         }
     }
     public void displayEmployees()
     {
         for(Employee employee:employeeList)
         {
             System.out.println(employee);
          }
     }

 }
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello and welcome to PayrollSystem !");
          PayrollSystem payrollSystem=new PayrollSystem();
          FullTimeEmployee fullemp1=new FullTimeEmployee("Suvadip",2,80000);
          PartTimeEmployee partemp1=new PartTimeEmployee("Jeet",5,40,500);
          payrollSystem.addEmployee(fullemp1);//add emp to payrollsystem;
          payrollSystem.addEmployee(partemp1);
          System.out.println("Initial Employee Details : ");
          payrollSystem.displayEmployees();
        System.out.println("Removing employee : ");
         payrollSystem.removeEmployee(5);
         payrollSystem.displayEmployees();

    }
}