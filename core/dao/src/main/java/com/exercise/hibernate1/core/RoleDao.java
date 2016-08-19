package com.exercise.hibernate1.core;

import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import com.exercise.hibernate1.HibernateUtil;

public class RoleDao {

  //role option1 view roles
  public List <Role> getRolesDatabase(){
    Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
	  List <Role> roles = new ArrayList<Role>();
		try{
		    tx = session.beginTransaction();
        Criteria c = session.createCriteria(Role.class);
		    roles = c.list();
		    tx.commit();
		}catch (HibernateException e) {
		    if (tx!=null) tx.rollback();
		    e.printStackTrace();
		}finally {
		    session.close();
		}
		return roles;
	}

  //role option2
  public void addRoleDatabase(String role){
    Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.save(role);
      tx.commit();
    }catch (HibernateException e) {
      if (tx!=null) tx.rollback();
      e.printStackTrace();
    }finally {
      session.close();
    }
  }

  //role option3
  public void deleteRoleDatabase(long id){
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;
    try{
        tx = session.beginTransaction();
        Role role = (Role) session.get(Role.class, id);
        session.delete(role);
        tx.commit();
    }catch (HibernateException e) {
        if (tx!=null) tx.rollback();
        e.printStackTrace();
    }finally {
        session.close();
    }
  }

  //role option4
  public void updateRoleDatabase(long id, Role updatedRole){
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;
    try{
        tx = session.beginTransaction();
        Role role =(Role)session.get(Role.class, id);
        role.setRoleName(updatedRole.getRoleName());
        session.update(role);
        tx.commit();
    }catch (HibernateException e) {
        if (tx!=null) tx.rollback();
        e.printStackTrace();
    }finally {
        session.close();
    }
  }

  public Role getRole(long id){
    Session session = HibernateUtil.getSessionFactory().openSession();
		Role role= null;
    Transaction tx = null;
		try{
			tx = session.beginTransaction();
			role = (Role) session.get(Role.class, id);
			tx.commit();
		}catch (HibernateException e) {
      if (tx!=null) tx.rollback();
      e.printStackTrace();
    }finally {
      session.close();
    }
		return role;
  }

  

}
