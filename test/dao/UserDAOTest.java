/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.User;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 * @author Cedric DELHOME
 * @author Laure-Helene Soyeux
 */
public class UserDAOTest {

    /**
     * Test of create method, of class UserDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        // create object user    
        User user = new User(0, "Pierre", "Durant", "2 rue de la liberte", 
                "Paris", "France", "0633468855", "p.durant@gmail.com", 0);
        UserDAO userDAO = new UserDAO();
        // find user  create 
        User expResult = userDAO.find(user.getId());

        // if find aita is empty or null
        if (!userDAO.isValid(expResult)) {           
            // insert user in table
            User result = userDAO.create(user);
            // find user
            expResult = userDAO.find(user.getId());   
            // delete user test
            userDAO.delete(result.getId());
            assertEquals(expResult, result); 
         
        }

    }

    /**
     * Test of update method, of class UserDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        // create object user      
        User userInsert = new User(0, "Pierre", "Durant", "2 rue de la liberte", 
                "Paris", "France", "0633468855", "p.durant@hotmail.com", 0);
        // instatied userDAO
        UserDAO userDAO = new UserDAO();
        // find user create 
        User findUser = userDAO.find(userInsert.getId());
        // if find user is empty
        if (!userDAO.isValid(findUser)) {
            // Insert user in table
            User resultUser = userDAO.create(userInsert);             
            // find user
            findUser = userDAO.find(resultUser.getId());
        }
            // modify user
            findUser.setFirstname("Cedric");
            findUser.setLastname("David");
            findUser.setAddress("26 BD de Victor Hugo");
            findUser.setCity("Marseille");
            findUser.setCountry("France");
            findUser.setTel("0612345678");
            findUser.setTel("c.david@free.fr");        
            findUser.setFunction(1);

        // update user
        boolean result = userDAO.update(findUser);
        boolean expResult = true;
        // delete user test
        userDAO.delete(findUser.getId());
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class UserDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        UserDAO userDAO = new UserDAO();

        // create object user	
        User userInsert = new User(0, "Pierre", "Durant", "2 rue de la liberte", 
                "Paris", "France", "0633468855", "p.durant@durant.com", 0);
        // find user  create 
        User findUser = userDAO.find(userInsert.getId());

        // if find user is empty
        if (!userDAO.isValid(findUser)) {
            // Insert user in table
            User resultUser = userDAO.create(userInsert);
            // find user
            findUser = userDAO.find(resultUser.getId());
            // delete user
            boolean result = userDAO.delete(findUser.getId());
            boolean expResult = true;
            assertEquals(expResult, result);
        }
    }

    /**
     * Test of findAll method, of class UserDAO.
     */
    @Test
    public void testGetdAll() {
        System.out.println("findAll");
        UserDAO userDAO = new UserDAO();
        ArrayList<User> arrayUser = userDAO.getAll();
        String expResult = "";
        String result = "";
        for (int i = 0; i < arrayUser.size(); i++) {
            expResult += userDAO.find(arrayUser.get(i).getId());
            result += arrayUser.get(i);
        }
        assertEquals(expResult, result);

    }

    /**
     * Test of find method, of class UserDAO.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        UserDAO userDAO = new UserDAO();
        // assign primary key
        long primary_key = 1;
        // assign result exemple
        String expResult = "User{id=1, firstname=Jim, lastname=Lovell, "
                + "address=2 Avenue du beau moulin, city=Paris, country=France, "
                + "tel=0147200001, mail=jim.lovell@disney.com, function=1}";

        // find user
        String result = userDAO.find(primary_key).toString();
        assertEquals(expResult, result);
    }
}
