package com.sda.eshop.config;

import com.sda.eshop.dao.OrderDao;
import com.sda.eshop.dao.ProductDao;
import com.sda.eshop.dao.UserDao;
import com.sda.eshop.model.Order;
import com.sda.eshop.model.Product;
import com.sda.eshop.model.User;
import com.sda.eshop.utils.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

// used to create predefined data
public class DataConfig {

    private static final Logger logger = LogManager.getLogger(DataConfig.class);

    public static final String USERS_SOURCE = "src/main/resources/users.txt";

    public static void setInitialData() {
        logger.info("Setting initial data...");

        // create admin user
        UserDao userDao = new UserDao();
        User user = new User("admin", "admin", "admin");
        userDao.save(user);

        // create users
        List<User> users = IOUtils.loadUsers(USERS_SOURCE);
        //userDao.saveAll(users);
        userDao.saveAll(IOUtils.readUsersEnhanced());

        // create products
        Product product1 = new Product("TV", 500d, 20);
        Product product2 = new Product("Laptop", 4000d, 10);

        ProductDao productDao = new ProductDao();
        productDao.save(product1);
        productDao.save(product2);
        List<Product> products = Arrays.asList(product1, product2);

        // create order
        OrderDao orderDao = new OrderDao();
        Order order = new Order(user, products);

        orderDao.save(order);
    }
}
