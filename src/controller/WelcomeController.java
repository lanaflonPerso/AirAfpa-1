/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccessBackofficeDAO;
import dao.UserDAO;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AccessBackoffice;
import model.User;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public class WelcomeController {

    private AccessBackofficeDAO accessBackofficeDAO;
    private String errorMessageContent;
    private String errorMessageTitle;
    private int numberTry;
    private int MAX_TRY = 3;
    private boolean hasChanged;

    public WelcomeController(AccessBackofficeDAO accessBackofficeDAO) {
        this.accessBackofficeDAO = accessBackofficeDAO;
        this.errorMessageContent = "";
        this.errorMessageTitle = "Login | Password";
        this.numberTry = 0;
        this.hasChanged = false;
   
    }
/**
 * Check Login and Password 
 * @param login Entered by the user
 * @param password Entered by the user
 * @return login and password is valid return true or false not valid
 */
    public boolean checkLoginPassword(String login, String password) {
        boolean checkLoginPassword = true; // init check login
        // login and password is empty
        if (login.isEmpty() && password.isEmpty()) {
            checkLoginPassword = false;
            this.errorMessageContent = "Veuillez saisir un identifiant et un mot de passe";
        }// login is empty 
        else if (login.isEmpty()) {
            checkLoginPassword = false;
            this.errorMessageContent = "Veuillez saisir un identifiant";
        } // password is empty 
        else if (password.isEmpty()) {
            checkLoginPassword = false;
            this.errorMessageContent = "Veuillez saisir un mot de passe";

        } else {
            // find login in table Access Backoffie
            AccessBackoffice loginAccessBackoffice = this.accessBackofficeDAO.find(login);
            // login is not found
            if (!this.accessBackofficeDAO.isValid(loginAccessBackoffice)) {
                checkLoginPassword = false;
                this.errorMessageContent = "Identifiant inconnu !!!";
            }// session is blocked
            else if (loginAccessBackoffice.isIsBlocked()) {
                checkLoginPassword = false;
                this.errorMessageContent = "Trop de tentative !!!";
                this.numberTry = MAX_TRY;

            }// incorrect password  
            else if (!loginAccessBackoffice.getPassword().equals(this.getMD5Hash(password))) {
                checkLoginPassword = false;
                this.errorMessageContent = "Identifiant ou Mot de passe incorrect !!!";
            }else{
              this.hasChanged = loginAccessBackoffice.isHasChanged();
                
            }

        }
        // increment number try if not valide login or password
        if (!checkLoginPassword) {
            this.numberTry++;
        }

        return checkLoginPassword;
    }
/**
 * Hash password entered by the user for compared by password access back office
 * @param passwordClear password is clear Entered by the user
 * @return password Hash 1
 */
    public static String getMD5Hash(String passwordClear) {

        String passwordHashMD5 = passwordClear;

            try {
                MessageDigest md = MessageDigest.getInstance("MD5"); // or "SHA-1"
                md.update(passwordClear.getBytes());
                BigInteger hash = new BigInteger(1, md.digest());
                passwordHashMD5 = hash.toString(16);
                while (passwordHashMD5.length() < 32) { // 40 for SHA-1
                    passwordHashMD5 = "0" + passwordHashMD5;
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return passwordHashMD5;
    }

    public String getErrorMessageContent() {
        return errorMessageContent;
    }

    public String getErrorMessageTitle() {
        return errorMessageTitle;
    }
    
    public boolean getHasChanged(){       
         return this.hasChanged;
    }

/**
 * check try number
 * @return number try if number try  == max try  return false or true 
 */
    public boolean checkTryNumber() {
        boolean chekTryNumber = true;
        if (this.numberTry == MAX_TRY) {
            chekTryNumber = false;
            this.errorMessageContent = "Trop de tentative !!!";
        }
        return chekTryNumber;
    }

}
