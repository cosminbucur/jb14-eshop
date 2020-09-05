package com.sda.eshop.dao;

import com.sda.eshop.config.HibernateUtils;
import com.sda.eshop.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class UserDao {

    private static final Logger logger = LogManager.getLogger(UserDao.class);

    /* CRUD */

    /**
     * Insert a new User in the database.
     *
     * @param user user to be saved
     */
    public void save(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void saveAll(List<User> users) {
        users.forEach(user -> save(user));
    }

    public User findById(Long id) {
        Session session = openSession();
        String selectUserById = "from User where id= :id";
        Query<User> foundUserById = session.createQuery(selectUserById);
        foundUserById.setParameter("id", id);
        User foundUser = foundUserById.getResultStream().findFirst().orElse(null);
        session.close();
        return foundUser;
    }

    public User findByIdSimple(Long id) {
        User user = null;
        Session session = openSession();
        try {

            session.find(User.class, id);

        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    // try with resources syntax
    // try ( open some resource ) { }
    // catch () {}
    // no need to close resources in finally
    public User findByIdSimplest(Long id) {
        User user = null;
        try (Session session = openSession()) {
            session.find(User.class, id);
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    public User findByUsername(String username) {
        User user = null;
        try (Session session = openSession()) {

            Query<User> query = session.createQuery("FROM User u WHERE u.username = :username");
            query.setParameter("username", username);
            List<User> foundUsers = query.list();

            if (foundUsers.isEmpty()) {
                return user;
            } else {
                user = foundUsers.get(0);
            }

        } catch (HibernateException e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    public boolean usernameExists(String username) {
        Session session = openSession();
        String hql = "FROM User u WHERE u.username = :username";
        Query<User> query = session.createQuery(hql);
        query.setParameter("username", username);

        User foundUser = null;
        try {
            foundUser = query.getSingleResult();
        } catch (Exception e) {
            // not found
        }

        return null != foundUser;
    }

    public List<User> findAll() {
        Session session = openSession();
        String selectAllUsers = "from User";
        Query<User> foundUsers = session.createQuery(selectAllUsers);
        List<User> userList = foundUsers.getResultStream().collect(Collectors.toList());
        session.close();
        return userList;
    }

    public void update(Long id, String updatedName) {
        Session session = openSession();
        User userToBeUpdated = findById(id);
        Transaction transaction = session.beginTransaction();
        userToBeUpdated.setName(updatedName);
        session.update(userToBeUpdated);
        transaction.commit();
        session.close();
    }

    public void delete(Long id) {
        Session session = openSession();
        User userToBeDeleted = findById(id);
        Transaction transaction = session.beginTransaction();
        session.delete(userToBeDeleted);
        transaction.commit();
        session.close();
    }

    /* HELPER METHODS */

    private Session openSession() {
        return HibernateUtils.getSessionFactory().openSession();
    }
}
