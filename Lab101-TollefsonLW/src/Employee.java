
/**
 *
 * @author Luke Tollefson
 * @version January 15, 2019
 *
 * A class that represents an employee of a company
 *
 */
public class Employee {

    private int id;
    private String name;
    private static int empCount;

    /**
     *
     * @param id The ID number of an employee
     * @param name The name of an employee
     */
    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
        empCount++;
    }

    /**
     * Gets the ID of an employee
     *
     * @return The numerical ID of an employee
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of an employee
     *
     * @return The name of an employee
     */
    public String getName() {
        return name;
    }

    /**
     * This gives the total number of employee objects made
     *
     * @return The total number of Employee objects made
     */
    public static int getEmpCount() {
        return empCount;
    }

    /**
     * Mutates an employee's ID number
     *
     * @param id The new ID for the employee
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Mutates the name of an employee
     *
     * @param name The new name of the employee
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gives the content of an employee
     *
     * @return The state of an employee object
     */
    public String toString() {
        return this.getClass().getName() + "@"
                + id + ":"
                + name + ":"
                + empCount;

    }

    /**
     * Checks to see if an employee is identical to another
     *
     * @param o Another employee
     * @return A true of false value
     */
    public boolean equals(Object o) {
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee e = (Employee) o;
        return id == e.id
                && name.equals(e.name);
    }
}
