// imports declaration 
package com.mycompany.datacenter;
// imports declaration 
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DataCenter {
    
    // creating the user list array list 
    static ArrayList<String> usernames = new ArrayList<>();
    static ArrayList<String> pins = new ArrayList<>();
    static ArrayList<Integer>roles = new ArrayList<>();   
    static ArrayList<String> createdAt = new ArrayList<>();
    static ArrayList<ArrayList<String>> loginLogs = new ArrayList<>(); 
    
    // Lockout tracking for the arrays 
    static ArrayList<Boolean> lockedOut = new ArrayList<>();
    static ArrayList<Integer> failedAttempts  = new ArrayList<>();
    
    static final int Max_Attempt = 3; // setting a static or contast number of tries for the users 
    static ArrayList<Integer> failedAttempts  = new ArrayList<>();
    
    static Scanner scanner = new Scanner(System.in); // creation of a scanner to accept the data 
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
     // creation of the main  method 
    
     public static void main(String[] args) {
    
     
     System.out.println( " DATA CENTER SECURITY SYSTEM ");
     
     // CREATING A WHILE LOOP TO PREVENT ANY OTHER USERS FROM CREATING USERS 
     
     while (true) {
        
         int choice = readInt("Enter choice");
         
         switch ( choice){
              case 1 -> handleLogin();
                case 2 -> System.out.println("Only logged-in Admins can create users.");
                case 3 -> System.out.println("Only logged-in Admins can view users.");
                case 4 -> {
                    System.out.println("Goodbye. Stay secure.");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option. Try again.");
             
         }
     }
     }
    
}
