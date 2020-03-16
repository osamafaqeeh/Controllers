package com.osama.regestration.Controller;

import com.osama.regestration.model.dao.CourseDAO;
import com.osama.regestration.model.dao.ScheduleDAO;
import com.osama.regestration.model.dao.StudentDAO;
import com.osama.regestration.model.dao.impl.CourseDAOImpl;
import com.osama.regestration.model.dao.impl.StudentDAOImpl;
import com.osama.regestration.model.entity.Course;
import com.osama.regestration.model.entity.Schedule;
import com.osama.regestration.model.entity.Student;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

public class RegisterController {


    private ScheduleDAO scheduleDAO;
    private CourseDAO   courseDAO;
    private StudentDAO  studentDAO;

    public RegisterController(ScheduleDAO scheduleDAO, CourseDAO courseDAO, StudentDAO studentDAO){
        this.scheduleDAO=scheduleDAO;
        this.courseDAO=courseDAO;
        this.studentDAO=studentDAO;
    }

    public void register(Schedule schedule) {

        String IdSchedule =schedule.getIdSchedule();
        String idStudent=schedule.getIdStudent();
        String courseId=schedule.getIdCourse();

        Student student=this.studentDAO.findById(idStudent);
        if (student==null){
            throw new RuntimeException("Student  dose not exist");
        }
        Course course=this.courseDAO.findById(courseId);
        if (course==null){
            throw new RuntimeException("Course  dose not exist");
        }

        java.sql.Date startingDate =course.getStartingDate();


        if (startingDate.compareTo(java.sql.Date.valueOf(LocalDate.now()))<0){
            throw new RuntimeException("Course   not available");
        }

        int Capacity=course.getCapacity();
        List<Student>listStudent =this.scheduleDAO.findStudentsByIDCourse(courseId);
        if (Capacity<=listStudent.size()){
            throw new RuntimeException("Course  is full");
        }

        List<Course>listCourse=this.scheduleDAO.findCoursesByIDStudent(idStudent);

        for (Course c : listCourse){

            if (c.getId()==idStudent){
                throw new RuntimeException("Course  dose not exist");
            }
        }
        this.scheduleDAO.createSchedule(schedule);
    }
}
