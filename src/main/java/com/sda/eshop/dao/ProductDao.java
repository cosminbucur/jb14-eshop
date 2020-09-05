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
        List<Product> productList = foundProducts.list();
        session.close();
        return productList;
    }

    public Product findById(Long id) {
        Session session = openSession();
        String selectProductById = "from Product where id= :id";
        Query<Product> foundProductById = session.createQuery(selectProductById);
        foundProductById.setParameter("id", id);
        Product foundProduct = foundProductById.getResultStream().findFirst().orElse(null);
        session.close();
        return foundProduct;
    }

    /* HELPER METHODS */

    private Session openSession() {
        return HibernateUtils.getSessionFactory().openSession();
    }
}
