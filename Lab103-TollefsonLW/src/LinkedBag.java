
import java.util.Random;

/**
 * Using the bag interface on a linked list
 * @author Luke Tollefson
 * @version February 1, 2019
 */

public class LinkedBag<T> implements Bag<T> {
    private SinglyLinkedList<T> list;
    private int count;
    
    /**
     * Constructor for LinkedBag
     */
    public LinkedBag() {
        list = new SinglyLinkedList<T>();
    }
    
    /**
     * Gives the number of elements in the bag
     * @return the number of elements in the bag
     */
    public int getCurrentSize() {
        return count;
    }
    
    /**
     * Checks if there are no elements in the bag
     * @return true if there are no elements in the bag
     */
    public boolean isEmpty() {
        return count == 0;
    }
    
    /**
     * Adds an element to the list
     * @param e the element being added
     */
    public void add(T e) {
        list.addFirst(e);
        count++;
    }
    
    /**
     * Remove a particular element from the bag
     * @param e the element being removed
     * @return true if the element was in the bag and was removed
     */
    public boolean remove(T e) {
        boolean isRemoved = false;
        for (int i = 0; i < count; i++) {
            // !isRemoved is present so that only one is removed
            // it is also there so the .equals() methoed is not
            // called unessarly
            if (!isRemoved && list.first().equals(e)) {
                list.removeFirst();
                count--;
                isRemoved = true;
            } else {
                list.addLast(list.removeFirst());
            }
        }
        
        return isRemoved;
    }
    
    /**
     * Remove a random element from the bag
     * @return the element removed
     */
    public T remove() {
        Random rand = new Random();
        int index = rand.nextInt(count);
        
        T element = null;
        for (int i = 0; i < count; i++) {
            if (i == index) {
                element = list.removeFirst(); 
            } else {
                list.addLast(list.removeFirst());   
            }
        } 
        count--;
        
        return element;
    }
    
    /**
     * Clears the bag
     */
    public void clear() {
        list = new SinglyLinkedList<T>();
        count = 0;
    }
    
    /**
     * Gives the frequency of an element
     * @param element the element being tested
     * @return the number of times the element appears in the bag
     */
    public int getFrequencyOf(T element) {
        int frequency = 0;
        for (int i = 0; i < count; i++) {
            if (list.first().equals(element)) {
                frequency++;
            }
            list.addLast(list.removeFirst());
        }
        return frequency;
    }
    
    /**
     * Checks to see if an element is in the bag
     * @param element the element being tested
     * @return true if the element is in the bag
     */
    public boolean contains(T element) {
        boolean member = false;
        for (int i = 0; i < count; i++) {
            // if !member is true, than we don't need to check .equals()
            if (!member && list.first().equals(element)) {
                member = true;
            }
            list.addLast(list.removeFirst());
        }

        return member;
    }
    
    /**
     * Gets an element at an index
     * @param index the index of the element
     * @return the element at index
     */
    public T elementAt(int index) {
        // if the method is called with an invalid index
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        T element = null;
        for (int i = 0; i < count; i++) {
            if (i == index) {
                element = list.first();
            }
            list.addLast(list.removeFirst());
        }
        return element;
    }
    
    /**
     * 
     * @return a string representation of the bag
     */
    public String toString() {
        String s = getClass().getName() + "@" 
                + "count=" + count + ":elements=[";
        for (int i = 0; i < count; i++) {
            s += list.first();
            // we don't want a comma after the last element
            if (i != count - 1) {
                s += ",";
            }
            list.addLast(list.removeFirst());
        }

        s += "]";
        return s;
    }
    
    /**
     * 
     * @param o the object being tested
     * @return true if the bags are not equal
     */
    public boolean equals(Object o) {
        if (!(o instanceof LinkedBag)) {
            return false;
        }
        LinkedBag<T> l = (LinkedBag<T>) o;
        if (count != l.count) {
            return false;
        }
        boolean equal = true;

        for (int i = 0; i < count; i++) {
            if (equal && !(list.first().equals(l.list.first()))) {
                equal = false;
            }
            list.addLast(list.removeFirst());
            l.list.addLast(l.list.removeFirst());
        }
        return equal;
    }
}
