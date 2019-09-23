
/**
 * An implementation of a dynamic array
 * @author Luke Tollefson
 * @version February 1, 2019
 */

import java.util.Random;

public class ArrayBag<T> implements Bag<T> {
    T[] list;
    int count;
    
    /**
     * The constructor defaults to an array length of 50
     */
    public ArrayBag() {
        list = (T[]) new Object[50];
    }
    
    /**
     * Constructor for an arbitrary initial array length
     * @param capacity the initial length of the array
     */
    public ArrayBag(int capacity) {
        list = (T[]) new Object[capacity];
    }
    
    /**
     * Gives the current number of elements in the ArrayBag
     * @return the number of elements in the ArrayBag
     */
    public int getCurrentSize() {
        return count;
    }
    
    /**
     * Checks to see if the array is empty or not
     * @return true if the array is empty
     */
    public boolean isEmpty() {
        return count == 0;
    }
    
    /**
     * Adds an element to the array
     * @param element the element being added
     */
    public void add(T element) {
        // the array needs to be resized if it cannot hold another element
        if (count >= list.length) {
            T[] temp = (T[]) new Object[list.length * 2];
            for (int i = 0; i < list.length; i++) {
                temp[i] = list[i];
            }
            list = temp;
            temp = null;
        } 
        list[count++] = element;
    }
    
    /**
     * Removes an element from the bag
     * @param element the element being removed
     * @return true if the element was in the array and was removed
     */
    public boolean remove(T element) {
        // find the index
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (list[i].equals(element)) {
                index = i;
                break;
            }
        }
        
        // remove the element
        if (index != -1) {
            for (int i = index; i < count - 1; i++) {
                list[i] = list[i+1];
            }
            // the removes the reference at count-1 so GC can free memory
            list[count-1] = null;
            count--;
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Remove a random element from the array
     * @return the element that was removed
     */
    public T remove() {
        if (count == 0) {
            return null;
        }
        
        Random rand = new Random();
        int index = rand.nextInt(count);
        T element = list[index];
        for (int i = index; i < count - 1; i++) {
            list[i] = list[i+1];
        }
        // the removes the reference at count-1 so GC can free memory
        list[count-1] = null;
        count--;
        return element;
    }
    
    /**
     * Empties the array
     */
    public void clear() {
        // the array needs its references removed to free memory
        for (int i = 0; i < count; i++) {
            list[i] = null;
        }
        count = 0;
    }
    
    /**
     * Gives the number of times an element appears in the array
     * @param element the element being checked
     * @return the number of times the element is in the bag
     */
    public int getFrequencyOf(T element) {
        int frequency = 0;
        for (int i = 0; i < count; i++) {
            if (list[i].equals(element)) {
                frequency++;
            }
        }
        return frequency;
    }
    
    /**
     * Tells you if an element is in a the array or not
     * @param element the element being checked
     * @return true if the element is in the bag
     */
    public boolean contains(T element) {
        for (int i = 0; i < count; i++) {
            if (list[i].equals(element)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Gives the element at a particular position in the array
     * @param index the index of the element
     * @return the element at index
     */
    public T elementAt(int index) {
        if (index >= 0 && index < count) {
            return list[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    
    /**
     * 
     * @return a string representation of the array
     */
    public String toString() {
        String s = getClass().getName() + "@" 
                + "count=" + count + ":" + "list=[";
        for (int i = 0; i < count; i++) {
            s += list[i];
            // we don't want a comma after the last element
            if (i != count - 1) {
                s += ",";
            }
        }
        return s += "]";
    }
    
    /**
     * 
     * @param o the object being tested for equality
     * @return true if the two objects have identical data
     */
    public boolean equals(Object o) {
        if (!(o instanceof ArrayBag)) {
            return false;
        }
        ArrayBag<T> a = (ArrayBag<T>) o;
        if (count != a.count) {
            return false;
        }
        for (int i = 0; i < count; i++) {
            if (!list[i].equals(a.list[i])) {
                return false;
            }
        }
        return true;
    }
}
