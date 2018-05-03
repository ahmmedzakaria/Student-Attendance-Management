/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.*;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

/**
 *
 * @author Zakaria
 */
public class ListDao {

    public List<Month> getMonthList() {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<Month> months = session.createQuery("SELECT m FROM Month m").list();
            session.getTransaction().commit();
            session.close();
            return months;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
     public List<StudentClass> getClassList() {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<StudentClass> classes = session.createQuery("SELECT m FROM StudentClass m").list();
            session.getTransaction().commit();
            session.close();
            return classes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     
     public List<Student> getStudentList() {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<Student> classes = session.createQuery("SELECT s FROM Student s").list();
            session.getTransaction().commit();
            session.close();
            return classes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
      public List<Student> getStudentList(int classId) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<Student> classes = session.createQuery("SELECT s FROM Student s where s.studentClass.classId='"+classId+"'").list();
            session.getTransaction().commit();
            session.close();
            return classes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
