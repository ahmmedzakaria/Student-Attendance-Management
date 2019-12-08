/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.*;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;
import util.Util;

/**
 *
 * @author Zakaria
 */
public class AttendanceDao {

    public int saveAttendance(List<Attendance> attendanceList) {
        int count = 0;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {

            session.beginTransaction();
            for (int i = 0; i < attendanceList.size(); i++) {
                session.save(attendanceList.get(i));
                count++;
            }
            session.getTransaction().commit();
            session.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return count;
        }
    }
    
    public int checkExistMonthReport(int monthId){
        int count = 0;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        session.beginTransaction();
        Criteria criteria = session.createCriteria(AttendanceReport.class);
        criteria.add(Restrictions.eq("month.monthId", monthId));
        Integer totalCount = criteria.setProjection(Projections.rowCount()).uniqueResult().hashCode();
        //List<Integer> pList=criteria.list();
        //List<Integer> pList = session.createQuery("SELECT s COUNT(*)  FROM AttendanceReport s WHERE s.month.monthId='" + monthId + "'").list();
        return totalCount;
    }
    
    public int updateAttendance(List<Attendance> attendanceList) {
        int count = 0;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {

            session.beginTransaction();
            for (int i = 0; i < attendanceList.size(); i++) {
                session.update(attendanceList.get(i));
                count++;
            }
            session.getTransaction().commit();
            session.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return count;
        }
    }

    public List<Attendance> getAttendance(int studentId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Attendance> pList = session.createQuery("SELECT s FROM Attendance s WHERE s.student.studentId" + studentId + "'").list();
        session.getTransaction().commit();
        session.close();
        return pList;
    }
    
     public List<Attendance> getAttendance(int classId, String date) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Attendance> pList = session.createQuery("SELECT s FROM Attendance s WHERE s.student.studentId'" + classId + "' and s.attendanceDate='" + date + "'").list();
        session.getTransaction().commit();
        session.close();
        return pList;
    }
    
     public int checkAttendenceForCurrentDate(int classId) {
         int listSize=-1;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Attendance> pList = session.createQuery("SELECT a FROM Attendance a WHERE a.attendanceDate='" + Util.getDate() + "' and a.studentClass.classId='"+classId+"'").list();
        listSize=pList.size();
        session.getTransaction().commit();
        session.close();
        return listSize;
    }
    
    
}
