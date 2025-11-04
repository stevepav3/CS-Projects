/* STEVE PAV
    This generic class extends java.util.LinkedList and has methods to
    enqueue(add to back), deque(remove front element), and getSize that
    returns size of the GenericQueue.
*/
package testgenericqueue;

/**
 *
 * @author steve
 * @param <E>
 */
public class GenericQueue<E> extends java.util.LinkedList {
    private final java.util.LinkedList<E> list = new java.util.LinkedList<>();
    
    // Adds element to end of queue
    public void enqueue(E obj) {
        list.add(obj);
    }
    
    // Removes and returns first element in queue
    public E dequeue() {
        return list.removeFirst();
    }
    
    // Returns size of queue 
    public int getSize() {
        return list.size();
    }
}
