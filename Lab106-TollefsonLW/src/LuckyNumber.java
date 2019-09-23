
import java.util.Random;


/**
 * The LuckyNumber class, it is a name with random number (0-9)
 * @author Luke Tollefson
 */
public class LuckyNumber {
    private String name;
    private int luckyNumber;
    
    /**
     * 
     * @param name the name of the lucky number
     */
    public LuckyNumber(String name) {
        this.name = name;
        Random rand = new Random();
        luckyNumber = rand.nextInt(10);
    }
    
    /**
     * 
     * @return the name associated with the LuckyNumber
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @return the lucky number
     */
    public int getLuckyNumber() {
        return luckyNumber;
    }
    
    /**
     * 
     * @return a string representation of the the LuckyNumber
     */
    public String toString() {
        return getClass().getName() + "@" + name + ":" + luckyNumber;
    }
    
    /**
     * 
     * @param o any object
     * @return true if the two objects have identical content
     */
    public boolean equals(Object o) {
        if (!(o instanceof LuckyNumber)) {
            return false;
        }
        LuckyNumber ln = (LuckyNumber) o;
        return luckyNumber == ln.luckyNumber && name == ln.name;
    }
}
