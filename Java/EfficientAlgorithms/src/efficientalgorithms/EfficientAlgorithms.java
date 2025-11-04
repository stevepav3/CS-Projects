/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efficientalgorithms;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author steve
 */
public class EfficientAlgorithms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<Long> list225 = test22_5();
        ArrayList<Long> list226 = test22_6();
        ArrayList<Long> list227 = test22_7();
    
        displayTable(list225, list226, list227);
        
        //System.out.println(list225.toString());
        //System.out.println(list226.toString());
        //System.out.println(list227.toString());
    }
    
    /*
        LISTING 22.5 TEST
    */
    public static ArrayList<Long> test22_5() {
        
        ArrayList<Long> times = new ArrayList<>();
        int factor = 2000000;  // 2,000,000
        int max = 18000000;  // 18,000,000
        
        for (int i = 8000000; i <= max; i += factor) {
            
            long startTime = System.currentTimeMillis();
            listing22_5(i);
            long endTime = System.currentTimeMillis();
            
            times.add(endTime - startTime);
        }
        
        return times;
    }
    
    public static void listing22_5 (int max) {
        // Test listing 22.5 algorithm to find prime numbers up to "max"
        
        int count = 0;
        int number = 2;
        
        // Repeatedly find prime numbers
        while (number < max) {
            
            // Assume number is prime
            boolean isPrime = true;  // Is the current number prime?
            
            // Test if number is prime
            for (int divisor = 2; divisor <= (int)(Math.sqrt(number));
                    ++divisor) {
                
                // If true, number is not prime
                if (number % divisor == 0) {
                    isPrime = false;
                    break;  // Exit for loop
                }        
            }
            
            // Increase the count
            if (isPrime) {
                ++count;           
            }
            ++number;  // Check next number
        }
        
    }
    
    /*
        LISTING 22.6 TEST
    */
    public static ArrayList<Long> test22_6() {
        
        ArrayList<Long> times = new ArrayList<>();
        int factor = 2000000;  // 2,000,000
        int max = 18000000;  // 18,000,000
        
        for (int i = 8000000; i <= max; i += factor) {
            
            long startTime = System.currentTimeMillis();
            listing22_6(i);
            long endTime = System.currentTimeMillis();
            
            times.add(endTime - startTime);
        }
        
        return times;
    }
    
    public static void listing22_6 (int max) {
        // Test listing 22.6 algorithm to find prime numbers up to "max"
        
        int count = 0;
        int number = 2;
        int squareRoot = 1;
        
        List<Integer> list = new ArrayList<>();
        
        while (number <= max) {
            
            // Assume number is prime
            boolean isPrime = true;  // Is the current number prime?
            
            if (squareRoot * squareRoot < max) squareRoot++;
            
            // Test whether number is prime
            for (int k = 0; k < list.size()
                            && list.get(k) <= squareRoot; k++) {
            
                if (number % list.get(k) == 0) {  // If true, not prime
                    isPrime = false;
                    break;  // Exit for loop
                }
            }
            
            // Increase count and add prime number to list
            if (isPrime) {
                count++;
                list.add(number);
            }
            
            number++;  // Check next number
        }
    }
    
    
    /*
        LISTING 22.7 TEST
    */
    public static ArrayList<Long> test22_7() {
        
        ArrayList<Long> times = new ArrayList<>();
        int factor = 2000000;  // 2,000,000
        int max = 18000000;  // 18,000,000
        
        for (int i = 8000000; i <= max; i += factor) {
            
            long startTime = System.currentTimeMillis();
            listing22_7(i);
            long endTime = System.currentTimeMillis();
            
            times.add(endTime - startTime);
        }
        
        return times;
    }
    
    public static void listing22_7 (int max) {
        // Test listing 22.7 algorithm to find prime numbers up to "max"
        boolean[] primes = new boolean[max + 1];
        
        // Initialize primes[i] to true
        for (int i = 0; i < primes.length; i++) {
            primes[i] = true;
        }
        
        for (int k = 2; k <= max / k; k++) {
            if (primes[k]) {
                for (int i = k; i <= max / k; i++) {
                    primes[k * i] = false;  // k * i is not prime
                }
            }
        }
    }
    
    // Display times of each algorithm in table format
    public static void displayTable(ArrayList<Long> list225,
                                    ArrayList<Long> list226,
                                    ArrayList<Long> list227) {
        
        System.out.format("%-20s%-15s%-15s%-15s%-15s%-15s%-15s",
                        "", "8,000,000", "10,000,000",
                        "12,000,000", "14,000,000",
                        "16,000,000", "18,000,000");
        
        System.out.println();  // Blank line
        System.out.format("%-20s%-15s%-15s%-15s%-15s%-15s%-15s",
                        "Listing 22.5", list225.get(0), list225.get(1),
                        list225.get(2), list225.get(3),
                        list225.get(4), list225.get(5));
    
        System.out.println();  // Blank line
        System.out.format("%-20s%-15s%-15s%-15s%-15s%-15s%-15s",
                        "Listing 22.6", list226.get(0), list226.get(1),
                        list226.get(2), list226.get(3),
                        list226.get(4), list226.get(5));
        
        System.out.println();  // Blank line
        System.out.format("%-20s%-15s%-15s%-15s%-15s%-15s%-15s",
                        "Listing 22.7", list227.get(0), list227.get(1),
                        list227.get(2), list227.get(3),
                        list227.get(4), list227.get(5));

        System.out.println();  // Blank line        
    }
 }