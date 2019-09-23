
/**
 * Queue interface
 * Code Fragment 6.9
 * from
 * Data Structures & Algorithms, 6th edition
 * by Michael T. Goodrich, Roberto Tamassia & Michael H. Goldwasser
 * Wiley 2014
 * Transcribed by
 * @author Luke Tollefson
 */
public interface Queue<E> {
    /** Returns the number of elements in the queue. */
    int size();
    /** Tests whether the queue is empty. */
    boolean isEmpty();
    /** Inserts an element at the rear of the queue. */
    void enqueue(E e);
    /** Returns, but does not remove, the first element of the queue (null if empty). */
    E first();
    /** Removes and returns the first element of the queue (null if empty). */
    E dequeue();
}
