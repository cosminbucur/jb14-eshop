package com.sda.eshop.ui;

import com.sda.eshop.controller.UserController;
import com.sda.eshop.model.User;
import com.sda.eshop.service.UserService;

import java.util.List;
import java.util.Scanner;

public class Menu {

    public static final String SELECT_AN_OPTION = "Select an option:";
    public static final UserService userService = new UserService();
    public static final UserController userController = new UserController();

    /*
    TODO: build main menu
        4. Admin area
          1. CRUD user
            1. Find all - display all users
              0. Back - to CRUD user
     */

    private static Scanner in = new Scanner(System.in);

    public static void showMainMenu() {

        System.out.println(SELECT_AN_OPTION);
        System.out.println("1. Product catalog");
        System.out.println("2. My orders");
        System.out.println("3. My account");
        System.out.println("4. Admin area");
        System.out.println("5. LOGOUT");
        System.out.println("0. EXIT");

        int option = in.nextInt();

        switch (option) {
            case 4: {
                showAdminArea();
                break;
            }
            default: {
                showMainMenu();
                break;
            }
        }
    }

    private static void showAdminArea() {
        System.out.println(SELECT_AN_OPTION);
        System.out.println("1. CRUD user");

        int option = in.nextInt();

        switch (option) {
            case 1: {
                showCrudUser();
                break;
            }
            default: {
                showAdminArea();
                break;
            }
        }
    }

    private static void showCrudUser() {
        System.out.println(SELECT_AN_OPTION);
        System.out.println("1. Find all");
        System.out.println("0. BACK");

        int option = in.nextInt();

        switch (option) {
            case 1: {
                showAllUsers();
                break;
            }
            default: {
                showCrudUser();
                break;
            }
        }
    }

    private static void showAllUsers() {
        List<User> users = userController.findAll();
        users.forEach(user -> System.out.println(user));

//        userService.findAll()
//            .forEach(user -> System.out.println(user));
    }

}
