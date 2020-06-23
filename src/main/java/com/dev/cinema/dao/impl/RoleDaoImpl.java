package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.exception.DataProcessingException;
import com.dev.cinema.model.Role;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class RoleDaoImpl implements RoleDao {
    private final SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role add(Role element) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(element);
            transaction.commit();
            log.info(element + " was inserted to DB");
            return element;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("There was an error inserting " + element, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Role> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Role> roles = session.createQuery(
                    "FROM Role", Role.class);
            return roles.getResultList();
        } catch (HibernateException e) {
            throw new DataProcessingException("Error retrieving all roles  ", e);
        }
    }

    @Override
    public Role get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Role.class, id);
        } catch (HibernateException e) {
            throw new DataProcessingException("Error retrieving role by id " + id, e);
        }
    }

    @Override
    public Role getRoleByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            Role.RoleName roleNameObject = Role.RoleName.valueOf(roleName);
            Query<Role> query = session.createQuery(
                    "FROM Role WHERE name = :name", Role.class);
            query.setParameter("name", roleNameObject);
            return query.getSingleResult();
        } catch (HibernateException e) {
            throw new DataProcessingException("Error retrieving role by " + roleName, e);
        }
    }
}
