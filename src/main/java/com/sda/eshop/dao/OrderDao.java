package com.sda.eshop.dao;

import com.sda.eshop.config.HibernateUtils;
import com.sda.eshop.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDao {

    private static final Logger logger = LogManager.getLogger(OrderDao.class);

    /* CRUD */

    /**
     * Insert a new Order in the database.
     *
     * @param order order to be saved
     */
    public void save(Order order) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = openSession();
            transaction = session.beginTransaction();
            session.save(order);
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

    public List<Order> findAll() {
        Session session = openSession();
        String selectAllOrders = "from Order";
        Query<Order> foundOrders = session.createQuery(selectAllOrders);
        List<Order> orderList = foundOrders.getResultStream().collect(Collectors.toList());
        session.close();
        return orderList;
    }

    /* HELPER METHODS */

    private Session openSession() {
        return HibernateUtils.getSessionFactory().openSession();
    }

}
