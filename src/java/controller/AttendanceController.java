/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Attendance;
import entity.AttendanceReport;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import service.AttendanceService;

@Path("/att")
public class AttendanceController {

    AttendanceService service = new AttendanceService();

    @GET
    @Path("/{classId}/{monthId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AttendanceReport> getAttendanceReports(@PathParam("classId") int classId, @PathParam("monthId") int monthId) {
        System.out.println("getAttendanceReports called.... classId: " + classId + " MonthId: " + monthId);
        int mId = monthId;
        if (mId <= 0) {
            mId = util.Util.getCurrentMonth();
        }

        return service.getAttendanceReports(classId, mId);
    }

    @GET
    @Path("/student/{studentId}/{monthId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Attendance> getAttendanceForStudent(@PathParam("studentId") int studentId, @PathParam("monthId") int monthId) {
        int mId = monthId;
        if (mId <= 0) {
            mId = util.Util.getCurrentMonth();
        }
        List<Attendance> aList= service.getAttendanceForStudent(studentId, mId);
        System.out.println("getAttendanceForStudent StudentId "+studentId+" monthId "+mId+" listSize "+aList.size());
        return aList;
    }

    @GET
    @Path("/exist/{classId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Attendance> getCuttentAttendanceIfExist(@PathParam("classId") int classId) {
        List<Attendance> aList = service.getCuttentAttendanceIfExist(classId, util.Util.getDate());
        System.out.println("getCuttentAttendanceIfExist");
        return null;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String saveAttendance(List<Attendance> attendanceList) {
        String result = "";
        System.out.println("save Attendance called...");
        System.out.println("Attendance: " + attendanceList.get(0).toString());
        int count = service.saveAttendance(attendanceList);
        if (count > 0) {
            result = count + " Attendance Saved";
        } else if (count == AttendanceService.ATTENDANCE_EXIST) {
            result = "Attendence Already Given";
        }
        System.out.println("result: " + result);
        return result;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String updateAttendance(List<Attendance> attendanceList) {
        String result = "";
        System.out.println("save Attendance called...");
        int count = service.updateAttendance(attendanceList);
        if (count > 0) {
            result = count + " Attendance Updated";
        } else if (count == AttendanceService.ATTENDANCE_EXIST) {
            result = "Something Wrong";
        }
        System.out.println("result: " + result);
        return result;
    }

}
