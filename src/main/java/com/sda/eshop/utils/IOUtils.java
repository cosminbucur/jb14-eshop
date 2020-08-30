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
import java.util.stream.Collectors;

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

    // TODO: do not hardcode the path here (use it as a parameter for the method)
    public static List<User> loadUsers(String filePath) {

        // TODO: ADVANCED - read how to load yml files from resources folder
        // https://stackabuse.com/reading-and-writing-yaml-files-in-java-with-jackson/
        //Path path = Paths.get("C:\\Dev\\jb14-eshop\\src\\main\\resources\\users.txt");
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
