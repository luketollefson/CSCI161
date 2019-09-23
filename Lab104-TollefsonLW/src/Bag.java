
/**
 * The Bag interface
 * A general interface for a variety of data structures
 * @author Luke Tollefson
 * @version February 1, 2019
 */

public interface Bag<T> {
    /**
     * Gives the number of elements in the bag
     * @return the number of elements in the bag
     */
    public int getCurrentSize();
    /**
     * Checks if the bag is empty
     * @return true if there are no elements in the bag
     */
    public boolean isEmpty();
    /**
     * Adds an element to the bag
     * @param element element being added to the bag
     */
    public void add(T element);
    /**
     * Removes a particular element from the bag
     * @param element element being removed
     * @return true if the element was removed
     */
    public boolean remove(T element);
    /**
     * Removes a random element from the bag
     * @return the element removed
     */
    public T remove();
    /**
     * Empties the bag of its elements
     */
    public void clear();
    /**
     * Gives the number of times an element is in the bag
     * @param element the element being checked
     * @return the number of times the element appears in the bag
     */
    public int getFrequencyOf(T element);
    /**
     * Checks to see if there is an element in the bag
     * @param element the element being checked
     * @return true if the element is in the bag
     */
    public boolean contains(T element);
    /**
     * 
     * @return a string representation of the contents of the bag
     */
    public String toString();
    /**
     * 
     * @param o object being compared
     * @return true if the content of the objects are identical
     */
    public boolean equals(Object o);
}
