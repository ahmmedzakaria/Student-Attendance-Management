/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ListDao;
import dao.StudentDao;
import entity.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zakaria
 */
public class StudentService {
    
    public List<Month> getAllMonth(){
        List<Month> months= new ListDao().getMonthList();
        List<Month> monthList=new ArrayList();
        for(Month m:months){
            Month month=new Month();
            month.setMonthId(m.getMonthId());
            month.setMonthName(m.getMonthName());
            monthList.add(month);
        }
        return monthList;
    }
    
     public List<StudentClass> getAllStudentClasses(){
        List<StudentClass> clsses= new ListDao().getClassList();
        List<StudentClass> clsList=new ArrayList();
        for(StudentClass c:clsses){
            StudentClass cls=new StudentClass();
            cls.setClassId(c.getClassId());
            cls.setName(c.getName());
            clsList.add(cls);
        }
        return clsList;
    }
   public boolean saveStudent(Student s){
        Student student=new StudentDao().saveStudent(s);
        AttendanceReport sr=new AttendanceReport();
        Month mon=new Month();
        mon.setMonthId(util.Util.getCurrentMonth());
        sr.setStudent(student);
        sr.setStudentClass(student.getStudentClass());
        sr.setMonth(mon);
        new StudentDao().addAttendanceReport(sr);
        
    return true;
    }
    public int getMaxRoll(int classId){
        return new StudentDao().getMaxRoll(classId)+1;
    }
    
    public List<Student> getAllStudents(){
        List<Student> students= new ListDao().getStudentList();   
        return regenarateStudentList(students);
    }
    public List<Student> getAllStudents(int classId){
        List<Student> students= new ListDao().getStudentList(classId);   
        return regenarateStudentList(students);
    }

    private List<Student> regenarateStudentList(List<Student> students){
    List<Student> stList=new ArrayList();
        for(Student s:students){
            Student student=new Student();
            StudentClass cls=new StudentClass();
            cls.setClassId(s.getStudentClass().getClassId());
            student.setStudentName(s.getStudentName());
            student.setRoll(s.getRoll());
//            student.setStudentClass(s.getStudentClass());
            student.setStudentClass(cls);
            student.setStudentId(s.getStudentId());
            stList.add(student);
        }
        return stList;
    }
    
    public boolean updateStudent(Student s){
        new StudentDao().updateStudent(s);
        return true;
    }
    
  
    public boolean deleteStudent(int id){
        Student s=new StudentDao().getStudent(id);
        new StudentDao().deleteStudent(s);
        return true;
    }
     public Student getStudent(int id){
        Student s=new StudentDao().getStudent(id);
        return s;
    }
    
}
