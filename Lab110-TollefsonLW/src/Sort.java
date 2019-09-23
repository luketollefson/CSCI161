
import java.util.Arrays;


/**
 *
 * @author Luke Tollefson
 * @version 23 April 2019
 */
public class Sort {

    /**
     * swaps index
     * @param <K>
     * @param S the array
     * @param i1 the index
     * @param i2 the other index
     */
    private static <K> void swap(K[] S, int i1, int i2) {
        K temp = S[i1];
        S[i1] = S[i2];
        S[i2] = temp;
    }
    
    /**
     * Merge sort, stable
     * @param <K>
     * @param S the array being sorted
     * @param comp the way its being arranged
     * @param timeGiven the amount of time given to sort something
     * @throws TimedOutException 
     */
    public static <K> void mergeSort(K[] S, Comparator<K> comp, long timeGiven) throws TimedOutException {
        mergeSortHelper(S, comp, timeGiven, System.currentTimeMillis());
    }
    
    /**
     * recursive algorithm for merge sort
     * @param <K>
     * @param S the arraying being sorted
     * @param comp the comparator being used to sort
     * @param timeGiven
     * @param startTime
     * @throws TimedOutException 
     */
    private static <K> void mergeSortHelper(K[] S, Comparator<K> comp, long timeGiven, long startTime) throws TimedOutException {
        if (System.currentTimeMillis() - startTime > timeGiven) {
                throw new TimedOutException("TimeOut");
        }
        int n = S.length;
        if (n < 2) return;      // array is trivially sorted
        // divide
        int mid = n / 2;
        K[] S1 = Arrays.copyOfRange(S, 0, mid);     // copy of first half
        K[] S2 = Arrays.copyOfRange(S, mid, n);     // copy of second half
        // conquer (with recursion)
        mergeSortHelper(S1, comp, timeGiven, startTime);        // sort copy of first half
        mergeSortHelper(S2, comp, timeGiven, startTime);        // sort copy of second half
        // merge results
        merge(S1, S2, S, comp, timeGiven, startTime);     // merge sorted halves back into original
                
    }
    
    /**
     * Merge contents of arrays S1 and S2 into properly sized array S.
     */
    private static <K> void merge(K[] S1, K[] S2, K[] S, Comparator<K> comp, long timeGiven, long startTime) throws TimedOutException {
        if (System.currentTimeMillis() - startTime > timeGiven) {
                throw new TimedOutException("TimeOut");
        }
        int i = 0, j = 0;
        while (i + j < S.length) {
            if (j == S2.length || (i < S1.length && comp.compare(S1[i], S2[j]) > 0)) {
                S[i + j] = S1[i++];   // copy ith element of S1 and increment i
            } else {
                S[i + j] = S2[j++];   // copy jth element of S2 and increment j
            }
        }
    }

    /**
     * bubbleSort implemented by Latimer
     */
    public static <K> void simpleBubbleSort(K[] data, Comparator<K> comp, long timeGiven) throws TimedOutException {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - 1; j++) {
                if (comp.compare(data[j], data[j + 1]) < 0) {
                    swap(data, j, j + 1);
                }
            }
            if (System.currentTimeMillis() - startTime > timeGiven) {
                throw new TimedOutException("TimeOut");
            }
        }
    }
    
    /**
     * a better bubble sort
     * @param <K>
     * @param data the array being sorted
     * @param comp the comparator being used 
     * @param timeGiven the amount of time allowed to be sorted
     * @throws TimedOutException 
     */
    public static <K> void enhancedBubbleSort(K[] data, Comparator<K> comp, long timeGiven) throws TimedOutException {
        long startTime = System.currentTimeMillis();
        boolean sorted = true;
        for (int i = 0; i < data.length; i++) {
            sorted = true;
            for (int j = 0; j < data.length - 1; j++) {
                if (comp.compare(data[j], data[j + 1]) < 0) {
                    swap(data, j, j + 1);
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
            if (System.currentTimeMillis() - startTime > timeGiven) {
                throw new TimedOutException("TimeOut");
            }
        }
    }
    
    /**
     * insertion sort
     * @param <K>
     * @param data the array being sorted
     * @param comp the comparator being used
     * @param timeGiven the amount of time allowed for the sorting algorithm
     * @throws TimedOutException 
     */
    public static <K> void insertionSort(K[] data, Comparator<K> comp, long timeGiven) throws TimedOutException {
        long startTime = System.currentTimeMillis();
        int j;
        K temp;
        
        for (int i = 1; i < data.length; i++) {
            j = i;
            temp = data[i];
            
            while (j != 0 && comp.compare(data[j - 1], temp) < 0) {
                data[j] = data[j - 1];
                j--;
            }
            
            data[j] = temp;
            if (System.currentTimeMillis() - startTime > timeGiven) {
                throw new TimedOutException("TimeOut");
            }
        }
    }
    
    /**
     * selection sort
     * @param <K>
     * @param data the array being sorted
     * @param comp the comparator used for sorting
     * @param timeGiven the allowed time
     * @throws TimedOutException 
     */
    public static <K> void selectionSort(K[] data, Comparator<K> comp, long timeGiven) throws TimedOutException {
        long startTime = System.currentTimeMillis();
        int maxIndex = 0;
        
        for (int i = 0; i < data.length; i++) {
            // find the maximum
            for (int j = 0; j < data.length - i; j++) {
                if (comp.compare(data[j], data[maxIndex]) < 0) {
                    maxIndex = j;
                }
            }
            
            swap(data, maxIndex, data.length - i - 1);
            if (System.currentTimeMillis() - startTime > timeGiven) {
                throw new TimedOutException("TimeOut");
            }
        }
    }
    
    /**
     * helper for quick sort
     * @param <K>
     * @param S the queue
     * @param comp the comparator being used
     * @param timeGiven the allowed time to sort
     * @param startTime the time the sort started
     * @throws TimedOutException 
     */
    private static <K> void quickSortHelper(Queue<K> S, Comparator<K> comp, long timeGiven, long startTime) throws TimedOutException {
        if (System.currentTimeMillis() - startTime > timeGiven) {
                throw new TimedOutException("TimeOut");
        }
        int n = S.size();
        if (n < 2) return;      // queue is trivially sorted
        // divide
        K pivot = S.first();    // using first as arbitrary pivot
        Queue<K> L = new LinkedQueue<>();
        Queue<K> E = new LinkedQueue<>();
        Queue<K> G = new LinkedQueue<>();
        while (!S.isEmpty()) {  // divide original into L, E, and G
            K element = S.dequeue();
            int c = comp.compare(element, pivot);
            if (c < 0)          // element is less than pivot
                L.enqueue(element);
            else if (c == 0)    // element is equal to pivot
                E.enqueue(element);
            else                // element is greater than pivot
                G.enqueue(element);
        }
        // conquer
        quickSortHelper(L, comp, timeGiven, startTime);     // sort elements less than pivot
        quickSortHelper(G, comp, timeGiven, startTime);     // sort elements greater than pivot
        // concatenate results
        while (!G.isEmpty())
            S.enqueue(G.dequeue());
        while (!E.isEmpty())
            S.enqueue(E.dequeue());
        while (!L.isEmpty())
            S.enqueue(L.dequeue());
    }
    
    /**
     * 
     * @param <K>
     * @param S the array being used
     * @param comp the comparator being used
     * @param timeGiven the time given for the sort
     * @throws TimedOutException 
     */
    public static <K> void quickSort(K[] S, Comparator<K> comp, long timeGiven) throws TimedOutException {
        Queue<K> Q = new LinkedQueue<>();
        for (int i = 0; i < S.length; i++) {
            Q.enqueue(S[i]);
        }
        
        quickSortHelper(Q, comp, timeGiven, System.currentTimeMillis());
        
        for (int i = 0; i < S.length; i++) {
            S[i] = Q.dequeue();
        }
    }
    
    /**
     * 
     * @param <K>
     * @param S the array being sorted
     * @param timeGiven the time allowed for the sort
     * @param comp1 the comparator being used
     * @throws TimedOutException 
     */
    public static <K> void radixSort(K[] S, long timeGiven, Comparator<K> comp1) throws TimedOutException {
        mergeSort(S, comp1, timeGiven);
    }
    
    /**
     * 
     * @param <K>
     * @param S the array being sorted
     * @param timeGiven the time allowed for the sort
     * @param comp1 most significant comparator
     * @param comp2 least significant comparator
     * @throws TimedOutException 
     */
    public static <K> void radixSort(K[] S, long timeGiven, Comparator<K> comp1, Comparator<K> comp2) throws TimedOutException {
        mergeSort(S, comp2, timeGiven);
        radixSort(S, timeGiven, comp1);
    }
    
    /**
     * 
     * @param <K>
     * @param S the array being sorted
     * @param timeGiven the time allowed for the sort
     * @param comp1 most significant comparator
     * @param comp2 middle significant comparator
     * @param comp3 least significant comparator
     * @throws TimedOutException 
     */
    public static <K> void radixSort(K[] S, long timeGiven, Comparator<K> comp1, Comparator<K> comp2,
        Comparator<K> comp3) throws TimedOutException {
        mergeSort(S, comp3, timeGiven);
        radixSort(S, timeGiven, comp1, comp2);
    }
    
    /**
     * 
     * @param <K>
     * @param S the array being sorted
     * @param timeGiven the time allowed for the sort
     * @param comp1 most significant comparator
     * @param comp2 second most significant comparator
     * @param comp3 second least significant comparator
     * @param comp4 least significant comparator
     * @throws TimedOutException 
     */
    public static <K> void radixSort(K[] S, long timeGiven, Comparator<K> comp1, Comparator<K> comp2,
        Comparator<K> comp3, Comparator<K> comp4) throws TimedOutException {
        mergeSort(S, comp4, timeGiven);
        radixSort(S, timeGiven, comp1, comp2, comp3);
    }
}
