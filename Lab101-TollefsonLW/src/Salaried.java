
/**
 *
 * @author Luke Tollefson
 * @version January 15, 2019
 *
 * A class that represents an salaried employee of a company
 *
 */
public class Salaried extends Employee {

    private String title;
    private int salary;
    private static int salCount;

    /**
     * A Salaried object represents an employee who has a salary
     *
     * @param id The ID number of an employee
     * @param name The name of an employee
     * @param title The employee's title within the company
     * @param salary The employee's salary
     */
    public Salaried(int id, String name, String title, int salary) {
        super(id, name);
        this.title = title;
        this.salary = salary;
        salCount++;
    }

    /**
     * This retrieves an employees title in a company
     *
     * @return The employee's title in a company
     */
    public String getTitle() {
        return title;
    }

    /**
     * This retrieves an employees salary in a company
     *
     * @return The employee's salary in a company
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Returns the number of Salaried objects that have been created
     *
     * @return the number of Salaried employees
     */
    public int getSalCount() {
        return salCount;
    }

    /**
     * Sets a new title for the salaried employee
     *
     * @param title the new title for the salaried employee
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets a new salary for the employee
     *
     * @param salary the new salary for the salaried employee
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     *
     * @return the attributes of the salaried employee
     */
    public String toString() {
        return super.toString() + ":"
                + getClass().getName() + "@"
                + title + ":"
                + salary + ":"
                + salCount;
    }

    /**
     *
     * @param o any object
     * @return wether or not the two objects are identical in content
     */
    public boolean equals(Object o) {
        if (!(o instanceof Salaried)) {
            return false;
        }
        Salaried s = (Salaried) o;
        return super.equals(s)
                && title.equals(s.title)
                && salary == s.salary;
    }
}
