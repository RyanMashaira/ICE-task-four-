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
    
    
}
