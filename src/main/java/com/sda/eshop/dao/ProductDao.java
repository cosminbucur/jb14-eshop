package com.sda.eshop.dao;

import com.sda.eshop.config.HibernateUtils;
import com.sda.eshop.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDao {

    private static final Logger logger = LogManager.getLogger(ProductDao.class);

    /* CRUD */

    /**
     * Insert a new Product in the database.
     *
     * @param product product to be saved
     */
    public void save(Product product) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = openSession();
            transaction = session.beginTransaction();
            session.save(product);
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

    public List<Product> findAll() {
        Session session = openSession();
        String selectAllProducts = "from Product";
        Query<Product> foundProducts = session.createQuery(selectAllProducts);
        List<Product> productList = foundProducts.getResultStream().collect(Collectors.toList());
        session.close();
        return productList;
    }

    /* HELPER METHODS */

    private Session openSession() {
        return HibernateUtils.getSessionFactory().openSession();
    }

}
