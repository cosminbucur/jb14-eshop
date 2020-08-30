# Eshop

CLI application for e-commerce. 

# Technology stack

- hibernate 5 - ORM framework
- mysql 8 - database
- log4j2 - logging framework

        OFF
        FATAL
        ERROR
        WARN
        INFO
        DEBUG
        TRACE
        ALL
- junit 5 - testing framework

# How to run
Just run the Application main class using Maven or Intellij.

### User stories

- as an admin user I want to create more users
    - user model
    - user dao
    - user service
    - user controller

- as an admin I want to load users from file

- as an admin I want to create producers so that I know my brands

- as an admin I want to create products so that my users can buy them

- as an user I want to place orders so that I can make my wife happy

# Menu

    1. Product catalog - display all products. select multiple ids and place order
    2. My orders - display placed orders
        0. BACK
    3. My account - display account info
        1. Change password - change password (retype for confirmation)
        0. BACK 
    4. Admin area
    
        1. CRUD user
            1. Find all
                0. BACK
            2. Find one
                ID to search
                0. BACK
            3. Create
                Name
                Username
                Password
            4. Update
                ID to search
                    Name
                    Username
                    Password (not a good idea)
                    0. BACK
            5. Delete
                ID to delete
                0. BACK
                
        2. CRUD product
            1. Find all
                0. BACK
            2. Find one
                ID to search
                0. BACK
            3. Create
                Name
                Price
                Quantity
                1. SAVE
                2. RESET
            4. Update
                ID to search
                    Name
                    Username
                    Password (not a good idea)
                    0. BACK
            5. Delete
                ID to delete
                0. BACK
                
        3. CRUD order
        0. BACK
    5. LOGOUT
    6. EXIT 