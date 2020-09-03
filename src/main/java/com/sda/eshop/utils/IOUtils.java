package com.sda.eshop.utils;

import com.sda.eshop.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IOUtils {

    private static final Logger logger = LogManager.getLogger(IOUtils.class);

    public static List<String> readUsers() {
        List<String> usersList = new ArrayList<>();
        BufferedReader reader;
        File users = new File("C:\\Working\\dev\\jb14-eshop\\src\\main\\resources\\users.txt");
        try {
            reader = new BufferedReader(new FileReader(users));
            String nextLine = reader.readLine();
            while (nextLine != null) {
                usersList.add(nextLine);
                nextLine = reader.readLine();
            }
            reader.close();
        } catch (Exception FileNotFound) {
            FileNotFound.printStackTrace();
        }
        return usersList;
    }

    // TODO: enhance the method for loading users - DONE
    public static List<User> readUsersEnhanced() {
        String password = "admin";
        String sourceFile = "src/main/resources/users.txt";
        List<User> resultedUsers = new ArrayList<>();
        List<String> firstThreeLettersOfFirstName = null;
        List<String> readFirstThreeLettersOfLastName = null;
        List<String> fullName = null;
        Random random = new Random();
        int min = 1;
        int max = 9;

        try
                (Stream<String> stream = Files.lines(Paths.get(sourceFile))) {
            fullName = stream
                    .map(s -> s.split(","))
                    .map(s -> s[0])
                    .collect(Collectors.toList());


        } catch (IOException e) {
            e.printStackTrace();
        }

        try
                (Stream<String> stream = Files.lines(Paths.get(sourceFile))) {
            firstThreeLettersOfFirstName = stream
                    .map(s -> s.split(","))
                    .map(s -> s[0])
                    .map(s -> s.split(" "))
                    .map(s -> s[0])
                    .map(s -> s.substring(0, 3).trim())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try
                (Stream<String> stream = Files.lines(Paths.get(sourceFile))) {
            readFirstThreeLettersOfLastName = stream
                    .map(s -> s.split(","))
                    .map(s -> s[0])
                    .map(s -> s.split(" "))
                    .map(s -> s[1])
                    .map(s -> s.substring(0, 3).trim())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < fullName.size(); i++) {

            User user = new User(fullName.get(i), (firstThreeLettersOfFirstName.get(i)
                    + readFirstThreeLettersOfLastName.get(i)).toLowerCase()
                    + random.nextInt((max - min) + 1) + min, password);
            resultedUsers.add(user);
        }
        return resultedUsers;
    }

    /*
    Read from file line by line:

    Given "Radu Chirila, admin"

    For each line create a new user like this:

    name: Radu Chirila
    username radchi76 (first 3 letters of each word and append a random 2 digit number)
    password: admin

     */
    public static List<User> loadUsers(String filePath) {
        // TODO: ADVANCED - read how to load yml files from resources folder
        // https://stackabuse.com/reading-and-writing-yaml-files-in-java-with-jackson/
        Path path = Paths.get(filePath);
        List<User> result = new ArrayList<>();
        try {
            List<String> names = Files.readAllLines(path);
            result = names.stream()
                    .map(name -> new User(name))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Failed read from file! {}", path);
        }
        return result;
    }
}
