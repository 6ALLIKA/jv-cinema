package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.exception.DataProcessingException;
import com.dev.cinema.model.Order;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            LOGGER.info(order + " was inserted to DB");
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("There was an error inserting " + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> query = session.createQuery(
                    "SELECT DISTINCT o FROM Order o JOIN FETCH o.tickets t "
                            + "WHERE o.user.id = :id", Order.class);
            query.setParameter("id", userId);
            return query.list();
        } catch (HibernateException e) {
            throw new DataProcessingException("Error retrieving user  ", e);
        }
    }

    @Override
    public Order getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Order.class, id);
        } catch (HibernateException e) {
            throw new DataProcessingException("Error retrieving movie by id " + id, e);
        }
    }
}
