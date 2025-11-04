/*  STEVE PAV
    This program demonstrates generic sorting using the comparable and comparator
    interfaces on two bubbleSort methods.  To demonstrate the methods, in the
    main method there are two arrays of different data types that will be sorted
    the same way with the comparable method, but will sort two different ways
    with the comparator method.  The string arrays will be sorted by string size
    and the integer array will be sorted in reverse order both calling the same
    generic function to be sorted in different ways.
 */
package genericsorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author steve
 */
public class GenericSorting{

    public static void main(String[] args) {
        
        // Two arrays of different data types
        String[] strArray = {"Bob", "Catherine", "Jill", "Sebastian", "Dean",
                            "Megan", "Tommy", "Ryan", "Andrew", "Nicholas"};
        
        Integer[] intArray = {12, 4, 18, 48, 39, 7, 98, 76, -5,  2, 12, 57};
        
        // Display unsorted arrays to user
        System.out.println("String array unsorted: " + Arrays.toString(strArray));
        System.out.println("Integer array unsorted: " + Arrays.toString(intArray));
        System.out.println();  // blank line
        
        // Sorty arrays using comparable method
        bubbleSort(strArray);
        bubbleSort(intArray);
        
        // Display sorted arrays to user
        System.out.println("String array sorted with comparable: " + 
                Arrays.toString(strArray));
        System.out.println("Integer array sorted with comparable: " +
                Arrays.toString(intArray));
        System.out.println();  // blank line
        
        // Sort the string array by string size and int array in reverse order
        bubbleSort(strArray, new StringSizeComparator());
        bubbleSort(intArray, Collections.reverseOrder());
        
        // Display the arrays sorted in a different order
        System.out.println("String array sorted by string size with comparator: " + 
                Arrays.toString(strArray));
        System.out.println("Integer array sorted in reverse order with comparator: " +
                Arrays.toString(intArray));
    }
    
    // Compares the sizes of two different strings
    static class StringSizeComparator implements Comparator<String> {

        @Override
        public int compare(String first, String second) {
            
            if (first.length() > second.length()) {
                return 1;
            }
            else if (first.length() == second.length()) {
                return 0;
            }
            
            return -1;  // else
        }
    }
       
    // Bubble sort with generic object that extends comparable
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        // Go through list up until length-1
        for (int i = 0; i < list.length - 1; ++i) {
            
            // Go through list again up until length-1-i
            for (int j = 0; j < list.length - 1 - i; ++j) {
                
                // Compare list[j] and list[j + 1] with comparable method
                if (list[j].compareTo(list[j+1]) > 0) {
                    
                    // Swap elements if list[j] > list[j + 1]
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
    }
    
    // Bubble sort with generic object that uses a comparator
    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        // Go through list up until length-1
        for (int i = 0; i < list.length - 1; ++i) {
            
            // Go through list again up until length-1-i
            for (int j = 0; j < list.length - 1 - i; ++j) {
                
                // Compare list[j] and list[j + 1] with comparator
                if (comparator.compare(list[j], list[j + 1]) > 0) {
                    
                    // Swap elements if list[j] > list[j + 1]
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
    }
}
