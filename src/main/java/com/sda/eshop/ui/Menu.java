package com.sda.eshop.ui;

import com.sda.eshop.controller.ProductController;
import com.sda.eshop.controller.UserController;
import com.sda.eshop.dao.UserDao;
import com.sda.eshop.model.Product;
import com.sda.eshop.model.User;
import com.sda.eshop.service.AuthenticationService;
import com.sda.eshop.service.UserService;
import com.sda.eshop.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.sda.eshop.utils.Constants.GO_BACK;
import static com.sda.eshop.utils.Constants.INSERT_ID_TO_SEARCH;
import static com.sda.eshop.utils.Constants.SELECT_AN_OPTION;

public class Menu {

    public static final UserDao userDao = new UserDao();
    public static final UserService userService = new UserServiceImpl(userDao);
    public static final AuthenticationService authService = new AuthenticationService(userService);
    public static final UserController userController = new UserController(userService, authService);
    public static final ProductController productController = new ProductController();
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
            case 1: {
                showProducts();
                break;
            }
            case 3: {
                showMyAccount();
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

    private static void showProducts() {
        // print all
        List<Product> products = productController.findAll();
        products.forEach(product -> System.out.println(product));

        // select products by id
        List<Product> productsToBuy = new ArrayList<>();

        System.out.println("Select products by inserting the ID and press ENTER");
        System.out.println("When ready to place order, type 0 to return");
        // TODO: add option go cancel

        // get id
        Long option = -1L;
        while (option != 0) {
            option = in.nextLong();

            // find by id
            Product selectedProduct = productController.findById(option);
            if (null != selectedProduct) {
                // add to list
                productsToBuy.add(selectedProduct);
            } else {
                System.out.println("The selected product does not exist. Try again!");
                System.out.println(GO_BACK);
            }
        }
    }

    private static void showMyAccount() {
        System.out.println("Current user: " + loggedUser.toString());
        System.out.println(SELECT_AN_OPTION);
        System.out.println("1. Change password");
        System.out.println(GO_BACK);

        int option = in.nextInt();

        switch (option) {
            case 1: {
                System.out.println("Insert your current password: ");
                String oldPassword = "test";

                String newPassword = "test";
                String retypedNewPassword = "repeat";

                while (!oldPassword.equals(loggedUser.getPassword())) {
                    oldPassword = in.next();
                }

                while (!newPassword.equals(retypedNewPassword)) {
                    System.out.println("Insert new password: ");
                    newPassword = in.next();

                    System.out.println("Please insert new password again: ");
                    retypedNewPassword = in.next();
                }

                loggedUser.setPassword(newPassword);
                userController.update(loggedUser);

                showMyAccount();
                break;
            }
            case 0: {
                showMainMenu();
                break;
            }
            default: {
                showMyAccount();
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
        System.out.println(INSERT_ID_TO_SEARCH);

        Long id = Long.valueOf(in.nextInt());

        User foundUser = userController.findById(id);

        if (foundUser != null) {
            System.out.println(SELECT_AN_OPTION);
            System.out.println("1. Name: " + foundUser.getName());
            System.out.println("2. Username: " + foundUser.getUsername());
            System.out.println("3. Password: ****");
            System.out.println(GO_BACK);
        }

        int option = in.nextInt();
        String updateData;
        User updatedUser = null;

        switch (option) {
            case 0: {
                showCrudUser();
                break;
            }
            case 1: {
                System.out.println("Insert new name: ");
                updateData = in.next();

                foundUser.setName(updateData);
                updatedUser = userController.update(foundUser);
                break;
            }
            case 2: {
                System.out.println("Insert new username: ");
                updateData = in.next();

                foundUser.setUsername(updateData);
                updatedUser = userController.update(foundUser);
                break;
            }
            case 3: {
                System.out.println("Insert new password: ");
                updateData = in.next();

                foundUser.setPassword(updateData);
                updatedUser = userController.update(foundUser);
                break;
            }
            default: {
                System.out.println("Incorrect option. Going back...");
                showCrudUser();
                break;
            }
        }

        if (null != updatedUser) {
            System.out.println("User successfully updated.");
            System.out.println(updatedUser);
        } else {
            System.out.println("User not updated.");
        }

        System.out.println("Going back...");
        showCrudUser();
    }

    private static void showDeleteUser() {
        System.out.println("Insert the user ID to delete:");
        System.out.println(GO_BACK);

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
        System.out.println(GO_BACK);

        Integer option = in.nextInt();

        if (option.equals(0)) {
            showCrudUser();
        } else if (option.equals(1)) {
            User newUser = new User(name, username, password);
            userController.save(newUser);
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
            System.out.println("Logged in as " + loggedUser.getName());
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

    /*
    Used to bypass security
     */
    // TODO: remove this in production
    public static void autoLogin() {
        loggedUser = userController.findByUsername("admin");
        System.out.println("Logged in as " + loggedUser.getName());
    }
}
