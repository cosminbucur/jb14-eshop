package com.sda.eshop.dao;

import com.sda.eshop.config.HibernateUtils;
import com.sda.eshop.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao {

    // TODO:
    public void save(User usertoSave) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(usertoSave);
        transaction.commit();
        session.close();
    }

    // TODO:
    public User findById(Integer id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        String query = "from User where id= :id";
        Query<User> foundUserById = session.createQuery(query);
        foundUserById.setParameter("id", id);
        User user = foundUserById.getResultStream().findFirst().orElse(null);
        session.close();
        return user;
    }

    // TODO:
    public List<User> findAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Query findAllQuery = session.createQuery("FROM User");
        List<User> usersList = findAllQuery.list();
        session.close();
        return usersList;
    }

    // TODO:
    public void update(long id, String name) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        User user = new User(name);
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();


    }

    // TODO:
    public void delete(long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User("invalid");
        session.delete(user);
        transaction.commit();
        session.close();
    }


}
