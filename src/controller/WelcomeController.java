/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccessBackofficeDAO;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AccessBackoffice;


/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public class WelcomeController {

    private AccessBackofficeDAO accessBackofficeDAO;
    private AccessBackoffice currentUser;
    private String errorMessageContent;
    private String errorMessageTitle;
    private int numberTry;
    private int MAX_TRY = 3;
    private boolean hasChanged;

    public WelcomeController(AccessBackofficeDAO accessBackofficeDAO) {
        this.accessBackofficeDAO = accessBackofficeDAO;
        this.currentUser = new AccessBackoffice();
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
            this.errorMessageContent = "Veuillez saisir un identifiant et un mot de passe.";
        }// login is empty 
        else if (login.isEmpty()) {
            checkLoginPassword = false;
            this.errorMessageContent = "Veuillez saisir un identifiant.";
        } // password is empty 
        else if (password.isEmpty()) {
            checkLoginPassword = false;
            this.errorMessageContent = "Veuillez saisir un mot de passe.";

        } else {
            // find login in table Access Backoffie
            this.currentUser = this.accessBackofficeDAO.find(login);
            // login is not found
            if (!this.accessBackofficeDAO.isValid(this.currentUser)) {
                checkLoginPassword = false;
                this.errorMessageContent = "Identifiant ou Mot de passe incorrect.";
            }// session is blocked
            else if (this.currentUser.isIsBlocked()) {
                checkLoginPassword = false;
                
                this.errorMessageContent = "Votre compte est blocké, Veuillez contacter votre administrateur.";
                this.numberTry = MAX_TRY;

            }// incorrect password  
            else if (!this.currentUser.getPassword().equals(this.getMD5Hash(password))) {
                checkLoginPassword = false;
                this.errorMessageContent = "Identifiant ou Mot de passe incorrect.";
            }else{
              this.hasChanged = this.currentUser.isHasChanged();
                
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
                //current user is not in table
            if(!this.accessBackofficeDAO.isValid(this.currentUser)){
                   this.errorMessageContent = "Acces refuser.";
                   System.exit(0);
            }else{         
                this.errorMessageContent = "Votre compte est blocké, veuillez contacter votre administrateur.";   
                 // hasChanged is false
                 if(!this.currentUser.isHasChanged()){                    

                     // set hasChanged is true
                     this.currentUser.setHasChanged(true);  
                     this.currentUser.setPassword("");
                     // update current user
                    boolean isUpdate = this.accessBackofficeDAO.update(this.currentUser);
                    // not update change message error
                     if(!isUpdate){
                              this.errorMessageContent = "Echec votre compte est blocké, veuillez contacter votre administrateur.";
                     }
                 }
             
             }
            
        }
        return chekTryNumber;
    }
    /**
     * Change password
     * @param newPassword
     * @return true update password and hasChanged or false not update
     */
    public boolean updatePassword(String newPassword){
        boolean updatePassword = false;
        // set hasChanged false
        this.currentUser.setHasChanged(false);
        // set new password
        this.currentUser.setPassword(newPassword);
        // update
        updatePassword = accessBackofficeDAO.update(this.currentUser);        
        return updatePassword;
    }
}
