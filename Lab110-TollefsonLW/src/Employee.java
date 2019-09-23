
import java.util.Random;


/**
 * A class representing an employee
 * @author Luke Tollefson
 * @version 23 April 2019
 */
public class Employee {

    private final int id;         // random number between 0-99999999
    private final String name;    // random string of lowercase letters length 5-10
    private final int dept;       // random number between 1-5
    private final int hired;      // random number between 2008-2018

    /**
     * Constructor for a new random employee
     */
    public Employee() {
        Random rand = new Random();
        id = rand.nextInt(100000000);
        int nameLength = 5 + rand.nextInt(6);
        char[] charName = new char[nameLength];
        for (int i = 0; i < nameLength; i++) {
            charName[i] = (char) (97 + rand.nextInt(26));
        }
        name = new String(charName);
        dept = 1 + rand.nextInt(5);
        hired = 2008 + rand.nextInt(11);
    }
    
    /**
     * 
     * @param id employee id
     * @param name employee name
     * @param dept employee's department
     * @param hired employee's hire date
     */
    public Employee(int id, String name, int dept, int hired) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.hired = hired;
    }

    /**
     * 
     * @return employee's id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @return employee's name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return employee's department
     */
    public int getDept() {
        return dept;
    }

    /**
     * 
     * @return employee's hire year
     */
    public int getHired() {
        return hired;
    }

//    public static Comparator<Employee> compareById() {
//        return new Comparator<Employee>() {
//            public int compare(Employee e1, Employee e2) {
//                return e1.id - e1.id;
//            }
//        };
//    }
    
    /**
     * 
     * @return a comparator for employee's id
     */
    public static Comparator<Employee> compareById() {
        return (Employee e1, Employee e2) -> e1.id - e2.id;
    }
    
    /**
     * 
     * @return a comparator for employee's name
     */
    public static Comparator<Employee> compareByName() {
        return (Employee e1, Employee e2) -> e1.name.compareTo(e2.name);
    }
    
    /**
     * 
     * @return a comparator for employee's department
     */
    public static Comparator<Employee> compareByDept() {
        return (Employee e1, Employee e2) -> e1.dept - e2.dept;
    }
    
    /**
     * 
     * @return a comparator for employee's hired
     */
    public static Comparator<Employee> compareByHired() {
        return (Employee e1, Employee e2) -> e1.hired - e2.hired;
    }
    
    /**
     * 
     * @return a string representation of employee 
     */
    public String toString() {
        return String.format("%8d %-10s %1d %4d", id, name, dept, hired);
    }
}
