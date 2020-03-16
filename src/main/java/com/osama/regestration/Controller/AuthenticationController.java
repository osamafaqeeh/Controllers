package com.osama.regestration.Controller;

import com.osama.regestration.model.dao.LoginDAO;
import com.osama.regestration.model.dao.impl.LoginDAOImpl;
import com.osama.regestration.model.entity.Login;

import java.sql.Date;
import java.time.LocalDate;

public class AuthenticationController {
    private LoginDAO loginDAO;
    public AuthenticationController(LoginDAO loginDAO){
        this.loginDAO=loginDAO;
    }

    public boolean isAuthenticated(String idStudent,String KeyAuthenticated){
        Login login=new Login();
        login=loginDAO.WithdrawAuthentication(KeyAuthenticated);
        if (login!=null&&login.getIdStudent()==idStudent&&login.getKeyAuthenticated()==KeyAuthenticated){

            return true;

        }
        else{
            return false;
        }

    }
}
