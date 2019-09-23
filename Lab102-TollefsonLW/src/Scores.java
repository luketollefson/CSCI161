
import java.util.Random;


/**
 * Scores class. Numbers are stored a dynamically sized contiguous array
 * This implements the Bag interface
 * @author Luke Tollefson
 * @version January 26, 2019
 */
public class Scores implements Bag {
    private int[] list;
    private int count;
    
    /**
     * Scores constructor. The default capacity
     * is 50
     */
    public Scores() {
        list = new int[50];
    }
    
    /**
     * Scores constructor. If you know before hand about
     * how much capacity you need, use this
     * @param length the initial capacity of the list
     */
    public Scores(int length) {
        list = new int[length];
    }
    
    /**
     * Gives the number of elements in the Scores
     * @return the number of elements in the Scores
     */
    public int getCurrentSize() {
        return count;
    }
    
    /**
     * Returns true if there are no elements in the Scores, and false
     * if there are one or more elements in the Scores
     * @return true if there are no elements in the Scores
     */
    public boolean isEmpty() {
        return count == 0;
    }
    
    /**
     * Logically clears the bag
     */
    public void clear() {
        count = 0;
    }
    
    /**
     * Add an int to the Scores
     * @param num the number being added to the end of the Scores
     */
    public void add(int num) {
        // if the array is not large enough, an array of twice
        // the capacity is created
        if (count + 1 > list.length) {
            int[] temp = new int[list.length * 2];
            for (int i = 0; i < list.length; i++) {
                temp[i] = list[i];
            }
            list = temp;
            temp = null;
        }
        
        // add the element to the array and increment the count
        list[count++] = num;
    }
    
    /**
     * Returns the frequency of a particular number in the Scores
     * @param num the number being tested on
     * @return the number of times num appears in the Scores
     */
    public int getFrequencyOf(int num) {
        int freq = 0;
        for (int i = 0; i < count; i++) {
            if (list[i] == num) {
                freq++;
            }
        }
        
        return freq;
    }
    
    /**
     * checks to see if there is a particular number in the Scores
     * @param num the number being checked
     * @return true if the number is in the bag
     */
    public boolean contains(int num) {
        for (int i = 0; i < count; i++) {
            if (list[i] == num) {
                return true;
            }  
        }
        
        return false;
    }
    
    /**
     * Removes the first occurrence of num in the Scores
     * @param num the number being removed
     */
    public void remove(int num) {
        // find the index of num
        for (int i = 0; i < count; i++) {
            if (list[i] == num) {
                // shift the elements leftward and decrement count
                for (int j = 0; j < count - 1; j++) {
                    list[i+j] = list[i+j+1];
                }
                count--;
                return;
            }
        }
    }
    
    /**
     * Remove a number at a random index
     */
    public void remove() {
        if (count == 0) {
            return;
        }
        
        Random rand = new Random();
        int index = rand.nextInt(count);
        for (int i = index; i < count - 1; i++) {
            list[i] = list[i+1];
        }
        count--;  
    }
    
    /**
     * Finds the number at index I in Scores
     * @param i the index of the array
     * @return number at index i
     * @throws ArrayIndexOutOfBoundsException if the index is outside of the list
     */
    public int get(int i) throws ArrayIndexOutOfBoundsException {
        if (i >= 0 && i < count) {
            return list[i];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    
    /**
     * 
     * @return a string representation of Scores
     */
    public String toString() {
        String s = "Scores@count=" + count + ":list=[";
        for (int i = 0; i < count; i++) {
            s += list[i] + (i == count-1 ? "" : ",");
        }
        s += "]";
        return s;
    }
    
    /**
     * 
     * @param o an arbitrary object
     * @return true if the object is a Scores object and has identical content
     */
    public boolean equals(Object o) {
        if (!(o instanceof int[])) {
            return false;
        }
        Scores s = (Scores) o;
        if (!(count != s.count)) {
            return false;
        }
        for (int i = 0; i < s.count; i++) {
            if (list[i] != s.list[i]) {
                return false;
            }
        }
        return true;
    }
}
