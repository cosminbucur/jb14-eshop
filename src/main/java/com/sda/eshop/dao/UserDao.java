package com.sda.eshop.dao;

import com.sda.eshop.config.HibernateUtils;
import com.sda.eshop.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class UserDao {

    //we can modify the constructor used to create the new User object later (create another constructor in User.class) and add more parameters to the method
    public void save(String name) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        User userToBeAdded = new User(name);
        Transaction transaction = session.beginTransaction();
        session.save(userToBeAdded);
        transaction.commit();
        session.close();
    }

    public User findById(Long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        String selectUserById = "from User where id= :id";
        Query<User> foundUserById = session.createQuery(selectUserById);
        foundUserById.setParameter("id", id);
        User foundUser = foundUserById.getResultStream().findFirst().orElse(null);
        session.close();
        return foundUser;
    }

    public List<User> findAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        String selectAllUsers = "from User";
        Query<User> foundUsers = session.createQuery(selectAllUsers);
        List<User> userList = foundUsers.getResultStream().collect(Collectors.toList());
        session.close();
        return userList;
    }

    public void update(Long id, String updatedName) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        User userToBeUpdated = findById(id);
        Transaction transaction = session.beginTransaction();
        userToBeUpdated.setName(updatedName);
        session.update(userToBeUpdated);
        transaction.commit();
        session.close();
    }

    public void delete(Long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        User userToBeDeleted = findById(id);
        Transaction transaction = session.beginTransaction();
        session.delete(userToBeDeleted);
        transaction.commit();
        session.close();
    }
}
