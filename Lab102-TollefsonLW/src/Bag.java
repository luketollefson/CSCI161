
/**
 * Bag interface. A Bag holds some numbers
 * 
 * @author Luke Tollefson
 * @version January 26, 2019
 */
public interface Bag {
    /**
     * 
     * @return the number of elements in the bag
     */
    public int getCurrentSize();
    
    /**
     * 
     * @return true if the bag is empty
     */
    public boolean isEmpty();
    
    /**
     * 
     * @param num the number being added to the bag
     */
    public void add(int num);
    
    /**
     * Removes the first occurrence of a number in the bag 
     * @param num the number being removed from the bag
     */
    public void remove(int num);
    
    /**
     * Removes a random element from the bag
     */
    public void remove();
    
    /**
     * Removes all elements from the bag
     */
    public void clear();
    
    /**
     * Gives the number of times a number is in the bag
     * @param num the number being tested
     * @return the number of num in the bag
     */
    public int getFrequencyOf(int num);
    
    /**
     * 
     * @param num the number being tested
     * @return true if num appears at least once in the bag
     */
    public boolean contains(int num);
    
    /**
     * 
     * @return a String representation of the contents of the bag
     */
    public String toString();
    
    /**
     * 
     * @param o an arbitrary Object
     * @return true if the two bags are identical in content
     */
    public boolean equals(Object o);
}
