/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import model.User;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public class WelcomeController {
    
    private User loginCurrent;
    private UserDAO userDao;
    private String errorMessage;
    private int numberTry;
    private int MAX_TRY = 3;

    public WelcomeController(User loginCurrent) {
        this.loginCurrent = loginCurrent;
        this.errorMessage = "";
        this.numberTry = 0;
    }

    
    public boolean checkLogin(String login, String password){
        boolean checkLogin = true;
        // login and password is empty
        if(login.isEmpty() && password.isEmpty()){
            checkLogin = false;
            this.errorMessage = "Veuillez saisir un identifiant et un mot de passe";            
        }else if(login.isEmpty()){
            checkLogin = false;
            this.errorMessage = "Veuillez saisir un identifiant";
        }else if(password.isEmpty()){
            checkLogin = false;
            this.errorMessage = "Veuillez saisir un mot de passe";
        } else {
           
           
            
            
            
        }
        
       return checkLogin;
    }
    
    
    
    
    public User getLoginCurrent() {
        return loginCurrent;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getNumberTry() {
        return numberTry;
    }

    public int getMAX_TRY() {
        return MAX_TRY;
    }

    public void setLoginCurrent(User loginCurrent) {
        this.loginCurrent = loginCurrent;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setNumberTry(int numberTry) {
        this.numberTry = numberTry;
    }

    public void setMAX_TRY(int MAX_TRY) {
        this.MAX_TRY = MAX_TRY;
    }
    
    
    
}
