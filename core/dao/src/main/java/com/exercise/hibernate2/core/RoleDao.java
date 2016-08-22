package com.exercise.hibernate2.core;

import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import com.exercise.hibernate2.HibernateUtil;
import org.hibernate.criterion.Restrictions;

public class RoleDao {


    public List<Role> getRoles() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Role> roles = new ArrayList<Role>();
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Role.class);
            roles = cr.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return roles;
    }

    public Role getRole(long roleId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Role role = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            role = (Role) session.get(Role.class, roleId);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return role;
    }

    public List<Role> getPersonRoles(long personId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List <Role> roles = null;
        try {
            tx = session.beginTransaction();
            Person person = (Person) session.get(Person.class,personId);
            roles = new ArrayList<Role>();
            roles.addAll(person.getRoles());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return roles;
    }

    public Role getPersonRole(long personId, long roleId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Set<Role> personRoles = null;
        Person person = null;
        Role role = null;
        try {
            tx = session.beginTransaction();
            person = (Person) session.get(Person.class, personId);
            personRoles = person.getRoles();
            for (Role r : personRoles) {
                if (r.getId() == roleId) {
                    role = r;
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return role;
    }

    //option2 done
    public void addPersonRole(long personId, Role role) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Person person = (Person) session.get(Person.class, personId);
            person.getRoles().add(role);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //option 3 done
    public void deletePersonRole(long personId, long roleId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Set<Role> personRoles = null;
        Role role = null;
        try {
            tx = session.beginTransaction();
            Person person = (Person) session.get(Person.class, personId);
            personRoles = person.getRoles();
            for (Role r : personRoles) {
                if (r.getId() == roleId) {
                    role = r;
                    break;
                }
            }
            person.getRoles().remove(role);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //option 5
    public void addRole(Role role) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(role);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //option 6
    public void deleteRole(long roleId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Role role = (Role) session.get(Role.class, roleId);
            session.delete(role);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //option 7
    public void updateRole(long roleId,String updatedRole) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Role role = (Role) session.get(Role.class, roleId);
            role.setRoleName(updatedRole);
            session.update(role);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


}
