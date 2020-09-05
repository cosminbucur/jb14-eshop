package com.sda.eshop.utils;

// check imports
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

// check class name
public class IOUtils {

    private static final Logger logger = LogManager.getLogger(IOUtils.class);

    // check order of methods

    // check method name, return type, parameter name
    // check number of parameters (create object)
    // check empty spaces
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

    public static List<User> readUsersSimple() {
        // TODO: implement this
        return null;
    }

    // TODO: enhance the method for loading users - DONE
    public static List<User> readUsersEnhanced() {
        String sourceFile = "src/main/resources/users.txt";

        List<String> fullNameList = setFullNames(sourceFile);
        List<String> firstThreeLettersOfFirstNameList = setFirstThreeLettersOfFirstName(sourceFile);
        List<String> firstThreeLettersOfLastNameList = setFirstThreeLettersOfLastName(sourceFile);

        return createUsernames(fullNameList, firstThreeLettersOfFirstNameList, firstThreeLettersOfLastNameList);
    }

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

    private static List<String> setFullNames(String sourceFile) {
        List<String> result = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(sourceFile))) {
            result = lines
                .map(line -> line.split(","))
                .map(line -> line[0])
                .collect(Collectors.toList());
        } catch (IOException e) {
            logger.fatal(Constants.UNABLE_TO_OPEN_FILE, sourceFile, e);
            e.printStackTrace();
        }
        return result;
    }

    private static List<String> setFirstThreeLettersOfFirstName(String sourceFile) {
        List<String> result = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(sourceFile))) {
            result = stream
                .map(s -> s.split(","))
                .map(s -> s[0])
                .map(s -> s.split(" "))
                .map(s -> s[0])
                .map(s -> s.substring(0, 3).trim())
                .collect(Collectors.toList());
        } catch (IOException e) {
            logger.fatal(Constants.UNABLE_TO_OPEN_FILE, sourceFile, e);
            e.printStackTrace();
        }
        return result;
    }

    private static List<String> setFirstThreeLettersOfLastName(String sourceFile) {
        List<String> result = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(sourceFile))) {
            result = stream
                .map(s -> s.split(","))
                .map(s -> s[0])
                .map(s -> s.split(" "))
                .map(s -> s[1])
                .map(s -> s.substring(0, 3).trim())
                .collect(Collectors.toList());
        } catch (IOException e) {
            logger.fatal(Constants.UNABLE_TO_OPEN_FILE, sourceFile, e);
            e.printStackTrace();
        }
        return result;
    }

    private static List<User> createUsernames(
        List<String> fullNameList,
        List<String> firstThreeLettersOfFirstName,
        List<String> firstThreeLettersOfLastName
    ) {
        int min = 10;
        int max = 99;
        List<User> result = new ArrayList<>();

        // check protections
        if (null != fullNameList && !fullNameList.isEmpty()) {
            for (int i = 0; i < fullNameList.size(); i++) {
                int randomNumber = (int) (Math.random() * ((max - min) + 1) + min);

                User user = new User(
                    fullNameList.get(i),
                    (firstThreeLettersOfFirstName.get(i)
                        + firstThreeLettersOfLastName.get(i)).toLowerCase()
                        + randomNumber,
                    "admin");
                result.add(user);
            }
        }
        return result;
    }
    /*
    Read from file line by line:

    Given "Radu Chirila, admin"

    For each line create a new user like this:

    name: Radu Chirila
    username radchi76 (first 3 letters of each word and append a random 2 digit number)
    password: admin

     */
}
