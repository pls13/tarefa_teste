package model;

import entities.*;
import java.io.*;
import java.util.*;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;


@SuppressWarnings("unchecked")
public abstract class AbstractModel<T> {

    private Class<T> entityClass;
    protected final SessionFactory sessionFactory = HibernateUtil
            .getSessionFactory();

    public AbstractModel(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public AbstractModel() {
    }

    public List<T> findAll() {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive())
                sessionFactory.getCurrentSession().getTransaction().begin();            
            return sessionFactory.getCurrentSession().createQuery("from " + entityClass.getName()).list();
        } catch (RuntimeException re) {
            return null;
        }
    }
    public List<T> findAllWhere(String where) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()){
                sessionFactory.getCurrentSession().getTransaction().begin();     
            }
            return sessionFactory.getCurrentSession().createQuery("from " + entityClass.getName()+" where "+where).list();
            
        } catch (RuntimeException re) {
            return null;
        }
    }

    public void update(T instance) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive())
                sessionFactory.getCurrentSession().getTransaction().begin();            
            sessionFactory.getCurrentSession().merge(instance);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (RuntimeException re) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw re;
        }
    }

    public void delete(T instance) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive())
                sessionFactory.getCurrentSession().getTransaction().begin();            
            sessionFactory.getCurrentSession().delete(instance);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (RuntimeException re) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw re;
        }
    }

    public void create(T instance) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive())
                sessionFactory.getCurrentSession().getTransaction().begin();            
            sessionFactory.getCurrentSession().persist(instance);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (RuntimeException re) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw re;
        }
    }

    public T find(Object primarykey) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive())
                sessionFactory.getCurrentSession().getTransaction().begin();            
            return (T) sessionFactory.getCurrentSession().get(entityClass,
                    (Serializable) primarykey);
        } catch (RuntimeException re) {
            return null;
        }
    }
    public T findWhere(String where) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive())
                sessionFactory.getCurrentSession().getTransaction().begin();            
            //return (T) sessionFactory.getCurrentSession().get(entityClass, (Serializable) primarykey);
            return (T) sessionFactory.getCurrentSession().createQuery("from " + entityClass.getName()+" where "+where).uniqueResult();
        } catch (RuntimeException re) {
            return null;
        }
    }
    public T findCriteria(String field, String value) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()){
                sessionFactory.getCurrentSession().getTransaction().begin();     
            }
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
            return(T) criteria.add(Restrictions.eq(field, value)).uniqueResult();
            //return (T) sessionFactory.getCurrentSession().createQuery("from " + entityClass.getName()+" where "+where).uniqueResult();
        } catch (RuntimeException re) {
            return null;
        }
    }
}
