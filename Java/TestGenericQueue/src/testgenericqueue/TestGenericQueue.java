/* STEVE PAV
    This program tests the GenericQueue class by using it to track patients
    waiting to see the doctor.  8 paitiens are enqueued to the GenericQueue
    and then they are dequeued displaying each patient followed by ", the
    doctor is ready to see you now."
 */
package testgenericqueue;

/**
 *
 * @author steve
 */
public class TestGenericQueue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create new GenericQueue object
        GenericQueue<String> queue = new GenericQueue<>();
        
        // Enqueue 8 different names to be seen by doctor
        queue.enqueue("Tom");
        queue.enqueue("George");
        queue.enqueue("Peter");
        queue.enqueue("Jean");
        queue.enqueue("Jane");
        queue.enqueue("Michael");
        queue.enqueue("Michelle");
        queue.enqueue("Daniel");
        
        // Dequeue all elements and display them to user
        while (queue.getSize() > 0) {
            System.out.println(queue.dequeue() + ", the doctor will see you now.");
        }
        
    }
    
}
