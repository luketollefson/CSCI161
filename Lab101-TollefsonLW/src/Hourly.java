
/**
 *
 * @author Luke Tollefson
 * @version January 15, 2019
 *
 * A class that represents an hourly employee of a company
 *
 */
public class Hourly extends Employee {

    private String position;
    private double hourlyRate;
    private int houCount;

    /**
     * Constructor for the Hourly employee
     *
     * @param id numerical ID of an employee
     * @param name the name of the hourly employee
     * @param position the hourly employee's position
     * @param hourlyRate the hourly pay of the employee
     */
    public Hourly(int id, String name, String position, double hourlyRate) {
        super(id, name);
        this.position = position;
        this.hourlyRate = hourlyRate;
        houCount++;
    }

    /**
     * Return the hourly paid employee's position
     *
     * @return the employee's position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Returns the hourly pay rate of the employee
     *
     * @return the hourly pay rate
     */
    public double getHourlyRate() {
        return hourlyRate;
    }

    /**
     * Returns the number of Hourly employees instantiated
     *
     * @return the number of Hourly objects created
     */
    public int getHouCount() {
        return houCount;
    }

    /**
     * Sets the employee a new position within the company
     *
     * @param position the new position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Changes the hourly rate of an employee
     *
     * @param hourlyRate the new hourly rate of an employee
     */
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    /**
     * Returns a string with the content of the object
     *
     * @return the content of the Hourly
     */
    public String toString() {
        return super.toString() + ":"
                + getClass().getName() + "@"
                + position + ":"
                + hourlyRate + ":"
                + houCount;
    }

    /**
     *
     * @param o any object
     * @return returns true if the two objects are identical in content
     */
    public boolean equals(Object o) {
        if (!(o instanceof Hourly)) {
            return false;
        }
        Hourly h = (Hourly) o;
        return super.equals(h)
                && position.equals(h.position)
                && Math.abs(hourlyRate - h.hourlyRate) < 0.01;
    }
}
