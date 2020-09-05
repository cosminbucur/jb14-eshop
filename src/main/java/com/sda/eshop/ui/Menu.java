package com.sda.eshop.ui;

import com.sda.eshop.controller.UserController;
import com.sda.eshop.dao.UserDao;
import com.sda.eshop.model.User;
import com.sda.eshop.service.AuthenticationService;
import com.sda.eshop.service.UserService;
import com.sda.eshop.service.UserServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Menu {

    public static final String SELECT_AN_OPTION = "Select an option:";

    public static final UserDao userDao = new UserDao();
    public static final UserService userService = new UserServiceImpl(userDao);
    public static final AuthenticationService authService = new AuthenticationService(userService);
    public static final UserController userController = new UserController(userService, authService);
    public static User loggedUser;

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
            case 0: {
                System.out.println("Thanks for buying. Bye!");
                break;
            }
            case 4: {
                showAdminArea();
                break;
            }
            case 5: {
                showLogout();
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
        System.out.println("2. Find one");
        System.out.println("3. Create");
        System.out.println("4. Edit existing user");
        System.out.println("5. Delete existing user");
        System.out.println("0. BACK");

        int option = in.nextInt();

        switch (option) {
            case 1: {
                showAllUsers();
                break;
            }
            case 2: {
                showFindUser();
                break;
            }
            case 3: {
                showSaveUser();
                break;
            }
            case 4: {
                showEditUser();
                break;
            }
            case 5: {
                showDeleteUser();
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
        showCrudUser();
    }

    private static void showFindUser() {
        System.out.println("Insert name to search");

        String username = in.next();
        User foundUser = userController.findByUsername(username);

        if (null != foundUser) {
            System.out.println(foundUser);
            showCrudUser();
        } else {
            System.out.println("User " + username + " not found");
            showCrudUser();
        }
    }

    private static void showEditUser() {
    }

    private static void showDeleteUser() {
        // find all, saw id
        // delete by id

        System.out.println("Insert the user ID to delete:");
        System.out.println("0. BACK");

        Integer userId = in.nextInt();

        if (userId.equals(0)) {
            showCrudUser();
        }
        Long id = Long.valueOf(userId);
        User userToDelete = userController.findById(id);

        // TODO: make delete return boolean to log messages
        // TODO: prevent delete current user
        if (userToDelete != null) {
            userController.deleteUser(id);
            System.out.println("User ID= " + userId + " deleted.");
            System.out.println("Going back...");
            showCrudUser();
        } else {
            System.out.println("User ID= " + userId + " not deleted!");
        }
    }

    /*
            3. Create
            Name
            Username
            Password
            1. SAVE
            2. RESET
            0. BACK
 */
    private static void showSaveUser() {
        System.out.println("Insert name");
        String name = in.next();
        System.out.println("Insert username");
        String username = in.next();
        System.out.println("Insert password");
        String password = in.next();

        System.out.println("1. SAVE");
        System.out.println("2. RESET");
        System.out.println("0. BACK");

        Integer option = in.nextInt();

        if (option.equals(0)) {
            showCrudUser();
        } else if (option.equals(1)) {
            // save
            User newUser = new User(name, username, password);
            userController.save(newUser);
            // back
            showCrudUser();
        } else if (option.equals(2)) {
            showSaveUser();
        }
    }

    public static void showLogin() {
        System.out.println("Welcome to Eshop.");
        System.out.println("Please login!");

        System.out.println("Insert username");
        String username = in.next();
        System.out.println("Insert password");
        String password = in.next();

        // check if authenticated
        Boolean isAuthenticated = userController.login(username, password);

        if (isAuthenticated) {
            // find user in db
            loggedUser = userController.findByUsername(username);
        }
    }

    public static void showLogout() {
        System.out.println("Are you sure you want to log out? y/n");

        String answer = in.next();

        if (answer.equals("y") && null != loggedUser) {
            loggedUser = null;
            System.out.println("Logged out");
        } else if (answer.equals("n")) {
            showMainMenu();
        }
    }
}
