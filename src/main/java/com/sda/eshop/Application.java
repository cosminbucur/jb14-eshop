package com.sda.eshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {

        logger.info("Application Started");
        Scanner input = new Scanner(System.in);
        int choice;
        String start;
        System.out.println("************");
        System.out.println("* Menu *");
        System.out.println("************");
        System.out.println("");
        printMenu();
        System.out.println("");
        System.out.println("type start");
        start = input.nextLine();

        while (start.equalsIgnoreCase("start")) {
            System.out.println("Type a command: ");
            choice = input.nextInt();
            if (choice == 1) {
                System.out.println("Product catalogue");
            } else if (choice == 2) {
                System.out.println("Order");
            } else if (choice == 3) {
                System.out.println("Your Account");
            } else if (choice == 4) {
                System.out.println("Admin");
            } else if (choice == 0) {
                printMenu();
            }
            // todo add a stop feature

        }
    }


    private static void printMenu() {
        System.out.println("enter 1 to see product catalogue");
        System.out.println("enter 2 to see your order");
        System.out.println("enter 3 to see your account");
        System.out.println("enter 4 to see the admin area");
        System.out.println("enter 0 to select another option");
        System.out.println("Now chose an option");
    }
}