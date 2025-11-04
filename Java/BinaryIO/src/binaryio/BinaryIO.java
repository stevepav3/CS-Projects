/* STEVE PAV
    This program creates a file if it doesn't already exist, then appends
    50 random integers [0, 1000) to the end of the file with an output stream.
    After closing the file, it reopens it with an input stream and reads
    the entire file contents to the program which then displays stats
    including highest value, lowest value, sum of all values, and average.
*/
package binaryio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BinaryIO {

    public static void main(String[] args) {
        
        // Create input and output streams
        DataOutputStream output = null;
        DataInputStream input = null;
        
        try {
            // Try to create new data stream to new or exisiting file
            output = new DataOutputStream(new FileOutputStream("chapter17.dat", true));
            
            // Generate, display, and append (to file)
            // 50 random integers [0, 1000)
            System.out.print("Integers added to file: ");
            for (int i = 0; i < 50; ++i) {
                int random = (int)(Math.random() * 1000);
                System.out.print(random + " ");
                output.writeInt(random);
            }
            System.out.println();
            output.close();  // close file
            
            
            // Try to open file, read contents, and display stats about values
            input = new DataInputStream(new FileInputStream("chapter17.dat"));
            
            // Array list to store integers read from file
            ArrayList<Integer> fileContents = new ArrayList<>();
            
            // Read values from file until end of file and
            // copy contents into array list
            while (input.available() != 0) {
                fileContents.add(input.readInt());
            }
            
            // Display stats of file contents
            DisplayStats(fileContents);
            
            // Close file
            input.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Destination file not found.");
        } catch (IOException ex) {
                System.out.println("A problem occured writing to "
                        + "or reading from the file.");
        }
        
    }
    
    public static void DisplayStats(ArrayList<Integer> fileContents) {
        
        // Variables to keep track of highest, lowest, and sum of contents
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;
        int sum = 0;
        
        for (int i : fileContents) {
            // Set highest value to i if i is more than current highest value
            if (i > highest) {
                highest = i;
            }
            
            // Set lowest value to i if i is less than current lowest value
            if (i < lowest) {
                lowest = i;
            }
            
            // Add value to running sum
            sum += i;
            
        }
        
        // Display stats to user
        System.out.println("Highest: " + highest);
        System.out.println("Lowest: " + lowest);
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + (sum / fileContents.size()));
        
    }
    
}
