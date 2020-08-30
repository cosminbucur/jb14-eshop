package com.sda.eshop;

import com.sda.eshop.config.DataConfig;
import com.sda.eshop.ui.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.sda.eshop.utils.Constants.APPLICATION_STARTED;

public class Application {

    // read about log4j https://www.scalyr.com/blog/maven-log4j2-project/
    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        logger.debug(APPLICATION_STARTED);
        DataConfig.setInitialData();

        System.out.println("************");
        System.out.println("* Menu *");
        System.out.println("************");

        Menu.showMainMenu();
    }

}