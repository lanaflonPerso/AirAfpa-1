/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Function;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 * @author Cedric DELHOME
 * @author Laure-Helene Soyeux
 */
public class FunctionDAOTest {

    /**
     * Test of create method, of class FunctionDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        // create object function    
        Function function = new Function(0,"Développeur");
        FunctionDAO functionDAO = new FunctionDAO();
        // find function  create 
        Function expResult = functionDAO.find(function.getId());

        // if find aita is empty or null
        if (!functionDAO.isValid(expResult)) {           
            // insert function in table
            Function result = functionDAO.create(function);
            // find function
            expResult = functionDAO.find(function.getId());   
            // delete function test
            functionDAO.delete(result.getId());
            assertEquals(expResult, result); 
         
        }

    }

    /**
     * Test of update method, of class FunctionDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        // create object function      
        Function functionInsert = new Function(0,"Cammercial");
        // instatied functionDAO
        FunctionDAO functionDAO = new FunctionDAO();
        // find function create 
        Function findFunction = functionDAO.find(functionInsert.getId());
        // if find function is empty
        if (!functionDAO.isValid(findFunction)) {
            // Insert function in table
            Function resultFunction = functionDAO.create(functionInsert);             
            // find function
            findFunction = functionDAO.find(resultFunction.getId());
        }
            // modify function
            findFunction.setDefinition("Commercial");

        // update function
        boolean result = functionDAO.update(findFunction);
        boolean expResult = true;
        // delete function test
        functionDAO.delete(findFunction.getId());
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class FunctionDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        FunctionDAO functionDAO = new FunctionDAO();

        // create object function	
        Function functionInsert =  new Function(0,"agent de sécurité");
        // find function  create 
        Function findFunction = functionDAO.find(functionInsert.getId());

        // if find function is empty
        if (!functionDAO.isValid(findFunction)) {
            // Insert function in table
            Function resultFunction = functionDAO.create(functionInsert);
            // find function
            findFunction = functionDAO.find(resultFunction.getId());
            // delete function
            boolean result = functionDAO.delete(findFunction.getId());
            boolean expResult = true;
            assertEquals(expResult, result);
        }
    }

    /**
     * Test of findAll method, of class FunctionDAO.
     */
    @Test
    public void testGetdAll() {
        System.out.println("findAll");
        FunctionDAO functionDAO = new FunctionDAO();
        ArrayList<Function> arrayFunction = functionDAO.getAll();
        String expResult = "";
        String result = "";
        for (int i = 0; i < arrayFunction.size(); i++) {
            expResult += functionDAO.find(arrayFunction.get(i).getId());
            result += arrayFunction.get(i);
        }
        assertEquals(expResult, result);

    }

    /**
     * Test of find method, of class FunctionDAO.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        FunctionDAO functionDAO = new FunctionDAO();
        // assign primary key
        int primary_key = 1;
        // assign result exemple
        String expResult ="Function{id=1, definition=Pilote}";
        // find function
  
        String result = functionDAO.find(primary_key).toString();
        assertEquals(expResult, result);
    }
}
