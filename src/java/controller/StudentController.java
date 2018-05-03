/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.*;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import service.StudentService;

@Path("/students")
public class StudentController {

    StudentService service = new StudentService();
    String result="Something Wrong !!!";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }
    
     @GET
     @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudentsByClass(@PathParam("id") int id) {
        return service.getAllStudents(id);
    }
    
    @GET
    @Path("/month")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Month> getAllMonths() {
        System.out.println("Called...");

        List<Month> months = service.getAllMonth();
        months.forEach((m) -> {
            System.out.println("month: " + m.toString());
        });

        return months;
    }

    @GET
    @Path("/classes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StudentClass> getAllClasses() {
        return service.getAllStudentClasses();
    }

    @GET
    @Path("/maxroll/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public int getMaxRoll(@PathParam("id") int id) {
        return service.getMaxRoll(id);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String saveSutdent(Student s) {
        
        System.out.println("Student: "+s.toString());
        if(service.saveStudent(s)){
            result=s.getStudentName()+" "+"Is Admited in "+s.getStudentClass().getName();
        }
        return result;
    }
    
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Student updateStudent(Student s){
        if(service.updateStudent(s)){
            result=s.getStudentName()+" "+"Is Updated ";
        }
        service.updateStudent(s);
        return s;
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteStudent(@PathParam("id") int id){
        if(service.deleteStudent(id)){
            result="Student Deleted Successfully";
        }
        return result;
    }
}
