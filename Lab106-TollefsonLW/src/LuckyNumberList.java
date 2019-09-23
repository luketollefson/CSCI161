
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The LuckyNumberList class, a list of LuckyNumbers with some iterators
 * @author Luke Tollefson
 */
public class LuckyNumberList {

    private LinkedPositionalList<LuckyNumber> luckyNumbers;

    /**
     * Creates a new LuckyNumberList
     */
    public LuckyNumberList() {
        luckyNumbers = new LinkedPositionalList<LuckyNumber>();
    }

    /**
     * adds a LuckyNumber to the list
     * @param ln the LuckyNumber number being added
     */
    public void addLuckyNumber(LuckyNumber ln) {
        luckyNumbers.addLast(ln);
    }
    
    /**
     * 
     * @return an Iterable over all the LuckyNumberList elements
     */
    public Iterable<Position<LuckyNumber>> positions() {
        return new PositionIterable();
    }
    
    /**
     * Gives an iterable LuckyNumberList
     */
    private class PositionIterable implements Iterable<Position<LuckyNumber>> {
        public Iterator<Position<LuckyNumber>> iterator() { return new PositionIterator(); }
    }
    
    /**
     * An iterator over all numbers in luckyNumberList
     */
    private class PositionIterator implements Iterator<Position<LuckyNumber>> {
        private Position<LuckyNumber> cursor = luckyNumbers.first();
        private Position<LuckyNumber> recent = null;
        
        /**
         * 
         * @return true if there is another element
         */
        public boolean hasNext() { return cursor != null; }
        
        /**
         * 
         * @return the position of the next element
         * @throws NoSuchElementException if there are no elements left
         */
        public Position<LuckyNumber> next() throws NoSuchElementException {
            if (cursor == null) throw new NoSuchElementException("nothing left");
            recent = cursor;
            cursor = luckyNumbers.after(cursor);
            return recent;
        }
        
        /**
         * removes the current element
         * @throws IllegalStateException 
         */
        public void remove() throws IllegalStateException {
            if (recent == null) throw new IllegalStateException("nothing to remove");
            luckyNumbers.remove(recent);
            recent = null;
        }
    }
    
    /**
     * 
     * @return an Iterable over all the even numbers
     */
    public Iterable<Position<LuckyNumber>> evenNumbers() {
        return new EvenPositionIterable();
    }
    
    /**
     * gives the iterable over all the even numbers
     */
    private class EvenPositionIterable implements Iterable<Position<LuckyNumber>> {
        public Iterator<Position<LuckyNumber>> iterator() { return new EvenPositionIterator(); }
    }
    
    /**
     * the iterator over even number
     */
    private class EvenPositionIterator implements Iterator<Position<LuckyNumber>> {
        private Position<LuckyNumber> cursor = luckyNumbers.first();
        private Position<LuckyNumber> recent = null;
        
        /**
         * sets the cursor to the first even number
         */
        public EvenPositionIterator() {
            while (cursor != null && !isEven(cursor.getElement().getLuckyNumber()))
                cursor = luckyNumbers.after(cursor);
        }
        
        /**
         * 
         * @return true if there is another element
         */
        public boolean hasNext() { return cursor != null; }
        
        /**
         * 
         * @return the next Position
         * @throws NoSuchElementException if there is no next element
         */
        public Position<LuckyNumber> next() throws NoSuchElementException {
            if (recent == null) {
                while (cursor != null && !isEven(cursor.getElement().getLuckyNumber()))
                    cursor = luckyNumbers.after(cursor);
            }
        
            if (cursor == null) throw new NoSuchElementException("nothing left");
            recent = cursor;
            cursor = luckyNumbers.after(cursor);
            
            while (cursor != null && !isEven(cursor.getElement().getLuckyNumber()))
                cursor = luckyNumbers.after(cursor);
            
            return recent;
        }
        
        /**
         * removes the current element
         * @throws IllegalStateException if there is nothing to remove
         */
        public void remove() throws IllegalStateException {
            if (recent == null) throw new IllegalStateException("nothing to remove");
            luckyNumbers.remove(recent);
            recent = null;
        }
    }
    
    /**
     * 
     * @return an iterable of the prime numbers
     */
    public Iterable<Position<LuckyNumber>> primeNumbers() {
        return new PrimePositionIterable();
    }
    
    /**
     * an Iterable over prime numbers 
     */
    private class PrimePositionIterable implements Iterable<Position<LuckyNumber>> {
        public Iterator<Position<LuckyNumber>> iterator() { return new PrimePositionIterator(); }
    }
    
    /**
     * an Iterator over primes
     */
    private class PrimePositionIterator implements Iterator<Position<LuckyNumber>> {
        private Position<LuckyNumber> cursor = luckyNumbers.first();
        private Position<LuckyNumber> recent = null;
        
        /**
         * sets the first element to a prime
         */
        public PrimePositionIterator() {
            while (cursor != null && !isPrime(cursor.getElement().getLuckyNumber()))
                cursor = luckyNumbers.after(cursor);
        }
        
        /**
         * 
         * @return true if there another element
         */
        public boolean hasNext() { return cursor != null; }
        
        /**
         * 
         * @return the next position
         * @throws NoSuchElementException if there nothing left
         */
        public Position<LuckyNumber> next() throws NoSuchElementException {
            if (recent == null) {
                while (cursor != null && !isPrime(cursor.getElement().getLuckyNumber()))
                    cursor = luckyNumbers.after(cursor);
            }
            
            if (cursor == null) throw new NoSuchElementException("nothing left");
            recent = cursor;
            cursor = luckyNumbers.after(cursor);
            
            while (cursor != null && !isPrime(cursor.getElement().getLuckyNumber()))
                cursor = luckyNumbers.after(cursor);
            
            return recent;
        }
        
        /**
         * removes the current element
         * @throws IllegalStateException if there is nothing left to remove
         */
        public void remove() throws IllegalStateException {
            if (recent == null) throw new IllegalStateException("nothing to remove");
            luckyNumbers.remove(recent);
            recent = null;
        }
    }
    
    /**
     * determines if a number if prime (if its between 0-9)
     * @param x the number being tested
     * @return true if its prime
     */
    public static boolean isPrime(int x) {
        switch (x) {
            case 2: case 3: case 5: case 7:
                return true;
            default:
                return false;
        }
    }
    
    /**
     * 
     * @param x the number being tested
     * @return true if its prime
     */
    public static boolean isEven(int x) {
        return x % 2 == 0;
    }
    
    /**
     * 
     * @return a string representation of the list 
     */
    public String toString() {
        String s = "";
        
        Iterator<LuckyNumber> listIterator = luckyNumbers.iterator();
        
        while (listIterator.hasNext()) {
            s += listIterator.next();
            String del = (listIterator.hasNext() ? ";" : "");
            s += del;
        }
        
        return s;
    }
}
