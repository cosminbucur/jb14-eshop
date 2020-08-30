package com.sda.eshop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Application {

    // TODO: fix logging
    // read about log4j https://www.scalyr.com/blog/maven-log4j2-project/
    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        logger.trace("We've just greeted the user!");
        logger.debug("We've just greeted the user!");
        logger.info("We've just greeted the user!");
        logger.warn("We've just greeted the user!");
        logger.error("We've just greeted the user!");
        logger.fatal("We've just greeted the user!");

        logger.debug("Application Started");

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
            // TODO: add an exit feature
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