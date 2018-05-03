package entity;
// Generated Apr 21, 2018 4:02:45 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Attendance generated by hbm2java
 */
@Entity
@Table(name="attendance"
    ,catalog="db_school_attendance"
)
public class Attendance  implements java.io.Serializable {


     private Integer attendanceId;
     private Month month;
     private Student student;
     private StudentClass studentClass;
     private boolean attendanceStatus;
     private String attendanceDate;

    public Attendance() {
    }

    public Attendance(Month month, Student student, StudentClass studentClass, boolean attendanceStatus, String attendanceDate) {
       this.month = month;
       this.student = student;
       this.studentClass = studentClass;
       this.attendanceStatus = attendanceStatus;
       this.attendanceDate = attendanceDate;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="attendance_id", unique=true, nullable=false)
    public Integer getAttendanceId() {
        return this.attendanceId;
    }
    
    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="month_id", nullable=false)
    public Month getMonth() {
        return this.month;
    }
    
    public void setMonth(Month month) {
        this.month = month;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="student_id", nullable=false)
    public Student getStudent() {
        return this.student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="class_id", nullable=false)
    public StudentClass getStudentClass() {
        return this.studentClass;
    }
    
    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }

    
    @Column(name="attendance_status", nullable=false)
    public boolean isAttendanceStatus() {
        return this.attendanceStatus;
    }
    
    public void setAttendanceStatus(boolean attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    
    @Column(name="attendance_date", nullable=false, length=45)
    public String getAttendanceDate() {
        return this.attendanceDate;
    }
    
    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    @Override
    public String toString() {
        return "Attendance{" + "attendanceId=" + attendanceId + ", month=" + month + ", student=" + student + ", studentClass=" + studentClass + ", attendanceStatus=" + attendanceStatus + ", attendanceDate=" + attendanceDate + '}';
    }




}


