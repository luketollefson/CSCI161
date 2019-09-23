
import java.util.Scanner;

/**
 *
 * @author Luke Tollefson
 * @version January 15, 2019
 *
 * A class that tests the functionality of the Employee, Salaried, and Hourly
 * classes
 *
 */
public class Client {

    /**
     * The main method which is ran at the start
     */
    public static void main(String[] args) {       
        
        Employee[] employeeList = new Employee[10];
        Scanner scan = new Scanner(System.in);

        // Ask the user for a total of six employees
        int id, salary;
        double hourlyRate;
        String name, title, position;
        String response = "";
        for (int i = 0; i < 6; i++) {
            System.out.print("Would you like to enter a salaried employee? [y/n] ");
            response = scan.nextLine();
            if (response.charAt(0) == 'y' || response.charAt(0) == 'Y') {
                System.out.print("Enter a Salaried id: ");
                id = scan.nextInt();
                scan.nextLine();
                System.out.print("Enter a Salaried name: ");
                name = scan.nextLine();
                System.out.print("Enter a Salaried title: ");
                title = scan.nextLine();
                System.out.print("Enter a Salaried salary: ");
                salary = scan.nextInt();
                scan.nextLine();
                employeeList[i] = new Salaried(id, name, title, salary);
            } else {
                System.out.print("Enter an Hourly id: ");
                id = scan.nextInt();
                scan.nextLine();
                System.out.print("Enter an Hourly name: ");
                name = scan.nextLine();
                System.out.print("Enter an Hourly position: ");
                position = scan.nextLine();
                System.out.print("Enter an Hourly hourly rate: ");
                hourlyRate = scan.nextDouble();
                scan.nextLine();
                employeeList[i] = new Hourly(id, name, position, hourlyRate);
            }
        }

        System.out.println();

        // print every Employee. null entries are printed as blanks
        for (int i = 0; i < employeeList.length; i++) {
            System.out.println(employeeList[i]);
        }
            

        // give all Employees a 10% raise
        for (int i = 0; i < employeeList.length; i++) {
            if (employeeList[i] instanceof Salaried) {
                Salaried s = (Salaried) employeeList[i];
                s.setSalary((int) (s.getSalary() * 1.1));
            } else if (employeeList[i] instanceof Hourly) {
                Hourly h = (Hourly) employeeList[i];
                h.setHourlyRate(h.getHourlyRate() * 1.1);
            }
        }

        // print every Employee. null entries are not printed at all
        System.out.println("\nList of Employees - post raise");
        for (Employee e : employeeList) {
            if (e != null) {
                System.out.println(e);
            }
        }

        System.out.println("\nTesting the equals method");

        // objects that will be tested for equality
        Salaried salaried1 = new Salaried(543, "George", "Junior Dev", 40001);
        Salaried salaried2 = new Salaried(943, "Sandra", "C Dev", 40002);
        Hourly hourly1 = new Hourly(903, "Jacob", "Manager", 70000);
        Hourly hourly2 = new Hourly(8240, "John", "Lead", 425422);

        System.out.println("salaried1 = " + salaried1);
        System.out.println("salaried2 = " + salaried2);
        System.out.println("hourly1 = " + hourly1);
        System.out.println("hourly2 = " + hourly2);

        System.out.println();

        // testing objects for equality
        System.out.println("salaried1.equals(salaried1) = "
                + salaried1.equals(salaried1));
        System.out.println("salaried1.equals(salaried2) = "
                + salaried1.equals(salaried2));
        System.out.println("hourly1.equals(hourly1) = "
                + hourly1.equals(hourly1));
        System.out.println("hourly1.equals(hourly2) = "
                + hourly1.equals(hourly2));
    }
}
