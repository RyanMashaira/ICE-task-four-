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
     seedUsers();
     
     System.out.println( " DATA CENTER SECURITY SYSTEM ");
     
     // CREATING A WHILE LOOP TO PREVENT ANY OTHER USERS FROM CREATING USERS 
     
     while (true) {
        showMainMenu();
        
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
     // Seeding users 
     static void seedUsers() {
        addUser("admin","1234", 1);
        addUser("alice","5678", 2);
        addUser("visitor","0000", 3);
    }
     // adding main menu
     static void showMainMenu() {
         System.out.println("               ");
         System.out.println( " Main Menu " );
         System.out.println("               ");
         System.out.println("1. Login ");
         System.out.println("2. Create User");
         System.out.println("3. View Users");
         System.out.println("4. Exit ");
       
     }
     // the log in process 
     static void handleLogin() {
        System.out.println("\n── LOGIN ──");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        
        int idx = usernames.indexOf(username);
        
         if (idx == -1) {
            System.out.println("User not found.");
            return;
        }
         if (lockedOut.get(idx)) {
            System.out.println("Account locked. Please wait " + LOCKOUT_MINUTES + " minutes or ask an Admin to unlock.");
            return;
        }
         System.out.print("PIN: ");
        String pin = scanner.nextLine().trim();

        String timestamp = LocalDateTime.now().format(dtf);
        loginLogs.get(idx).add(timestamp + " – attempt");
        
        if (pin.equals(pins.get(idx))) {
            failedAttempts.set(idx, 0);
            loginLogs.get(idx).add(timestamp + " – SUCCESS");
            System.out.println(" Login successful! Welcome, " + username + ".");
            showUserMenu(idx);
        } else {
             int attempts = failedAttempts.get(idx) + 1;
            failedAttempts.set(idx, attempts);
            loginLogs.get(idx).add(timestamp + " – FAILED (" + attempts + "/" + Max_Attempt + ")");
                if (attempts >= Max_Attempt) {
                // Admins cannot be auto-locked — this protects the system
                if (roles.get(idx) == 1) {
                    System.out.println("Incorrect PIN. Admin accounts cannot be locked to protect system access.");
                    System.out.println("   Attempts reset. Please try again.");
                    failedAttempts.set(idx, 0);
                } else {
                     lockedOut.set(idx, true);
                    System.out.println("System Locked. Too many failed attempts for user: " + username);
                    System.out.println("   Account will auto-unlock after " + LOCKOUT_MINUTES + " minutes.");
                    startUnlockTimer(idx);
                }
            } else {
                System.out.println("Incorrect PIN. " + (Max_Attempt - attempts) + " attempt(s) remaining.");
            }
        }
    }
        // user menu creation 
     static void showUserMenu(int idx) {
        int role = roles.get(idx);
        boolean running = true;
        
        while (running) {
            System.out.println();
            switch (role) {
                case 1 -> showAdminMenu();
                case 2 -> showStaffMenu();
                case 3 -> showVisitorMenu();
            }

            int choice = readInt("Enter choice: ");
            // Admin menu
             if (role == 1) { 
                switch (choice) {
                    case 1 -> createUser();
                    case 2 -> viewUsers();
                    case 3 -> viewLoginLogs();
                    case 4 -> unlockUser();
                    case 5 -> { System.out.println("Logged out."); running = false; }
                    default -> System.out.println("Invalid option.");
                } // Staff menu
                 } else if (role == 2) { 
                switch (choice) {
                    case 1 -> System.out.println(" Opening  staff files ");
                    case 2 -> { System.out.println("Logged out."); running = false; }
                    default -> System.out.println("Invalid option.");
                } 
                // visitor menu 
                switch (choice) {
                    case 1 -> System.out.println(" Opening public files ");
                    case 2 -> { System.out.println("Logged out."); running = false; }
                    default -> System.out.println("Invalid option.");
                }
            }
        }
        
             static void showAdminMenu() {
                 System.out.println("ADMIN MENU ");
                 System.out.println("├──────────────────────────┤");
                 System.out.println("1. Create User ");
                 System.out.println("2. View All Users ");
                 System.out.println("3. View Login Logs ");
                 System.out.println("4. Unlock a User ");
                 System.out.println("5. Logout");
                 
                 }
             static void showStaffMenu() {
                 System.out.println("│       STAFF MENU ");
                 System.out.println("├──────────────────────────┤");
                 System.out.println("1. Access Resources ");
                 System.out.println("2. Logout ");
        
             }
             

              static void showVisitorMenu() {
                 System.out.println("     VISITOR MENU ");
                 System.out.println("├──────────────────────────┤");
                 System.out.println("1. View Public Info ");
                 System.out.println("2. Logout ");
        
             }
              
            
                
        
     

    

