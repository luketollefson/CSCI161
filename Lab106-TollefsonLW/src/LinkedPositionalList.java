
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Data Structures & Algorithms 6th Edition 
 * Goodrick, Tamassia, Goldwasser
 * Code Fragements 7.9, 7.10, 7.11, 7.12 & 7.14
 * 
 * toString method added by Latimer
 */

/** Implementation of a positional list stored as a doubly linked list. */
public class LinkedPositionalList<E> implements PositionalList<E> {
    
    //----- nested Node class -----
    
    private static class Node<E> implements Position<E> {
        
        private E element;      // reference to the element stored at this node
        private Node<E> prev;   // reference to the prevous node in the list
        private Node<E> next;   // reference to the subsequent node in the list
        
        public Node( E e, Node<E> p, Node<E> n ){
            element = e;
            prev = p;
            next = n;
        }
                
        @Override
        public E getElement( ) throws IllegalStateException 
        {
            if ( next == null )
                throw new IllegalStateException( "Position no longer valid." );
            return element;
        }
        
        public Node<E> getPrev( )
        {
            return prev;
        }
        
        public Node<E> getNext( )
        {
            return next;
        }
        
        public void setElemetn( E e )
        {
            element = e;
        }
        
        public void setPrev( Node<E> p )
        {
            prev = p;
        }
        
        public void setNext( Node<E> n )
        {
            next = n;
        }
    } //----- end of nested Node class -----
    
    /**
     * Data Structures & Algorithms 6th Edition
     * Goodrick, Tamassia, Goldwasser
     * Code Fragement 7.14
     */
    
    //----- nested PositionIterator class -----
    private class PositionIterator implements Iterator<Position<E>>{
        private Position<E> cursor = first();   // position of the next element to report
        private Position<E> recent = null;      // position of last reported element
        /** Tests whether the iterator has a next object. */
        @Override
        public boolean hasNext( ) { return ( cursor != null ); }
        /** Returns the next position in the iterator. */
        @Override
        public Position<E> next( ) throws NoSuchElementException {
            if ( cursor == null ) throw new NoSuchElementException( "nothing left " );
            recent = cursor;
            cursor = after( cursor );
            return recent;
        }
        /** Removes the element returned by most recent call to next. */
        @Override
        public void remove( ) throws IllegalStateException {
            if ( recent == null ) throw new IllegalStateException( "nothing to remove" );
            LinkedPositionalList.this.remove( recent );         // remove from outer list
            recent = null;              // do not allow remove again until next is called
        }
    } //----- end of nested PositionIterator class -----
    
    //----- nested PositionIterable class -----
    private class PositionIterable implements Iterable<Position<E>>{
        @Override
        public Iterator<Position<E>> iterator( ) { return new PositionIterator( ); }        
    } //----- end of nested PositionIterable class -----
    
    /** Returns an iterable representation of the list's positions.
     * @return  */
    public Iterable<Position<E>> positions( ) {
        return new PositionIterable( );  // create a new instance of the inner class
    }
    
    //----- nested ElementIterator class -----
    /* This class adapts the iteration produced by positions( ) to return elements. */
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = new PositionIterator( );
        @Override
        public boolean hasNext( ) { return posIterator.hasNext( ); }
        @Override
        public E next( ) { return posIterator.next( ).getElement( ); } // return element
        @Override
        public void remove( ) { posIterator.remove( ); }
    }
    
    /** Returns an iterator of the elements stored in the list */
    public Iterator<E> iterator( ) { return new ElementIterator( ); }
    
    
    // instance variables of the LinkedPositionalList
    
    private Node<E> header;             // header sentinel
    private Node<E> trailer;            // trailer sentinel
    private int size = 0;               // number of elements in the list
    
    public LinkedPositionalList( ){
        header = new Node<>( null, null, null );     // create header
        trailer = new Node<>( null, header, null );  // create trailer is preceded by header
        header.setNext(trailer);                     // header is followed by trailer
    }
    
    // private utilities
    /**
     * @param p position to validate
     * @return node if position is valid
     * @throws IllegalArgumentException if p no longer in list or p is not a position
     */
    private Node<E> validate( Position<E> p ) throws IllegalArgumentException {
        
        if( !(p instanceof Node )) throw new IllegalArgumentException( "Invalid p" );
        
        Node<E> node = ( Node<E> ) p;   // safe cast
        
        if ( node.getNext() == null )
            throw new IllegalArgumentException( "p is no longer in the list" );
        
        return node;
    }
    
    /**
     * @param node to be returned as position if not header or trailer
     * @return position of node
     */
    private Position<E> position( Node<E> node ){
       if ( node == header || node == trailer )
           return null;
       return node;
    }
    
    // public accessor methods
    
    /**
     * @return number of elements in linked list
     */
    @Override
    public int size( ){
        return size;
    }
    
    /**
     * @return true if list is empty, false other wise
     */
    @Override
    public boolean isEmpty( ){
        return ( size == 0 );
    }
    
    /**
     * @return the first position in linked list (null if empty).
     */
    @Override
    public Position<E> first( ){
        return position( header.getNext( ) );
    }
    
    /**
     * @return the last position in linked list (null if empty).
     */
    @Override
    public Position<E> last( ){
        return position( trailer.getPrev( ) );
    }
    
    /**
     * @param p position to get position immediately before
     * @return position before p
     * @throws IllegalArgumentException if p not valid
     */
    @Override
    public Position<E> before( Position<E> p ) throws IllegalArgumentException{
      Node<E> node = validate( p );
      return position( node.getPrev( ) );  
    }
          
    /**
     * @param p position to get immediately after
     * @return position after p
     * @throws IllegalArgumentException if p not valid
     */ 
    @Override
    public Position<E> after( Position<E> p ) throws IllegalArgumentException{
        Node<E> node = validate( p );
        return position( node.getNext( ) );
    } 
    
    // private utilities
    
    /**
     * @param e element to be added
     * @param pred node to add element after
     * @param succ node to add element before
     * @return position of newly added element
     */
    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ ){
        Node<E> newest = new Node<>(e, pred, succ);  // create and link new node
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }
    
    // public update methods
    
    /**
     * @param e element to be added just after header
     * @return  position of newly added element
     */
    @Override
    public Position<E> addFirst(E e) {
        return addBetween( e, header, header.getNext() );
    }
    
    /**
     * @param e element to be added just before trailer
     * @return position of newly added element
     */
    @Override
    public Position<E> addLast( E e ){
        return addBetween(e, trailer.getPrev( ), trailer );
    }
    
    /**
     * 
     * @param p position to add element before
     * @param e element to be added
     * @return position of newly added element
     * @throws IllegalArgumentException if p is not valid
     */
    @Override
    public Position<E> addBefore( Position<E> p, E e ) throws IllegalArgumentException {
        Node<E> node = validate( p );
        return addBetween(e, node.getPrev( ), node );
    }
    
    /**
     * @param p position to add element after
     * @param e element to be added
     * @return position of newly added element
     * @throws IllegalArgumentException if p is not valid
     */
    @Override
    public Position<E> addAfter( Position<E> p, E e ) throws IllegalArgumentException {
        Node<E> node = validate( p );
        return addBetween(e, node, node.getNext( ) );
    }
    
    /**
     * @param p position of node to update
     * @param e new element for node
     * @return old element in node before update
     * @throws IllegalArgumentException if p not valid
     */
    @Override
    public E set( Position<E> p, E e ) throws IllegalArgumentException {
        Node<E> node = validate( p );
        E answer = node.getElement( );
        node.setElemetn( e );
        return answer;
    }
    
    /**
     * @param p position to be removed
     * @return element that was removed
     * @throws IllegalArgumentException if p not valid 
     */
    public E remove( Position<E> p ) throws IllegalArgumentException {
        Node<E> node = validate( p );
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext( successor );
        successor.setPrev( predecessor );
        size--;
        E answer = node.getElement( );
        node.setElemetn( null );
        node.setNext( null );
        node.setPrev( null );
        return answer;
    }
}
