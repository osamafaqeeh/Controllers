package com.osama.regestration.Controller;

import com.osama.regestration.Polinng.PolingConnection;
import com.osama.regestration.model.dao.LoginDAO;
import com.osama.regestration.model.dao.StudentDAO;
import com.osama.regestration.model.dao.impl.LoginDAOImpl;
import com.osama.regestration.model.dao.impl.StudentDAOImpl;
import com.osama.regestration.model.entity.Login;
import com.osama.regestration.model.entity.Student;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class LoginController {
    private LoginDAO loginDAO;
    private StudentDAO studentDAO;

    public LoginController(LoginDAOImpl loginDAO, StudentDAOImpl studentDAO){
        this.loginDAO=loginDAO;
        this.studentDAO=studentDAO;
    }

    public void login(String idStudent,String password){

        Student student=studentDAO.findById(idStudent);

        if (student==null){
           throw new UnsupportedOperationException("the user is not defined please sign in");
        }
        else{
            if (student.getPassword()==password&&student.getId()==idStudent){
                this.login(student);
            }
            else {
                throw new RuntimeException("the id or password is error");
            }
        }


    }
    private void login(Student student){
        Login login=new Login();
        login.setIdStudent(student.getId());
        login.setKeyAuthenticated(String.valueOf(1000*Math.random()));
        login.setLastAccess(Date.valueOf(LocalDate.now()));
        loginDAO.creatAuthentication(login);
    }
}
