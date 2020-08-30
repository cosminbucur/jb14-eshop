package com.sda.eshop.dao;

import com.sda.eshop.config.HibernateUtils;
import com.sda.eshop.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao {

    // TODO:
    public void save(User userToBeSaved) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        System.out.println("Hibernate session started.");
        Transaction transaction = session.beginTransaction();
        session.save(userToBeSaved);
        transaction.commit();
        session.close();
        System.out.println("Hibernate session ended. " + userToBeSaved + " was added to 'users' table.");

    }

    // TODO:
    public User findById(Integer id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        System.out.println("Hibernate session started.");
        String selectQuery = "from Project where projectId= :id";
        Query<User> foundUserById = session.createQuery(selectQuery);
        foundUserById.setParameter("id", id);
        User foundUser = foundUserById.getResultStream().findFirst().orElse(null);
        session.close();
        System.out.println("Hibernate session ended.");
        return foundUser;
    }

    // TODO:
    public List<User> findAll() {

        Session session = HibernateUtils.getSessionFactory().openSession();
        System.out.println("Hibernate session started.");
        Query findAllQuery = session.createQuery("FROM User");
        List<User> userList = findAllQuery.list();
        System.out.println("Found " + userList.size() + " projects.");
        //projectList.forEach(System.out::println);
        userList.forEach(e -> System.out.println(e));
        session.close();
        System.out.println("Hibernate session ended.");

        return userList;
    }

    // TODO:
    public void update(Integer id, String newUserName) {
        User user = findById(id);
        if (user != null) {
            user.setName(newUserName);

            Session session = HibernateUtils.getSessionFactory().openSession();
            System.out.println("Hibernate session started.");
            Transaction transaction = session.beginTransaction();
            session.update(user);

            transaction.commit();
            session.close();
        } else {
            System.out.println("Project with id= " + id + " not found");
        }
    }

    // TODO:
    public void delete(User userToBeDeleted) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        System.out.println("Hibernate session started.");
        Transaction transaction = session.beginTransaction();
        session.delete(userToBeDeleted);
        transaction.commit();
        session.close();
        System.out.println("Hibernate session ended. " + userToBeDeleted + " was deleted from 'users' table.");

    }

}
