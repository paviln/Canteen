package presentation.models;

import java.util.Scanner;

public class Employee {
    int balance;
    String employeeName;
    int employee_ID;

    public Employee(int balance, String employeeName, int employee_ID) {
        this.balance = balance;
        this.employeeName = employeeName;
        this.employee_ID = employee_ID;
    }

    void deposit(int amount) {
        balance += amount;
        System.out.println("Your new balance: " + balance);
    }

    void showMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter employee ID");
        int ID = sc.nextInt();
        while (ID != 2255) {
            System.out.println("Invalid ID");
            ID = sc.nextInt();
        }
        System.out.println("Enter amount to be deposited");
        int amount = sc.nextInt();
        deposit(amount);
    }
    }




