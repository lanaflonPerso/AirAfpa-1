/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.AccessSite;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 * @author Cedric DELHOME
 * @author Laure-Helene Soyeux
 */
public class AccessSiteDAOTest {

    /**
     * Test of create method, of class AccessSiteDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        // create object access_site    
        AccessSite access_site = new AccessSite(6, "E00006", "user6");
        AccessSiteDAO access_siteDAO = new AccessSiteDAO();
        // find access_site  create 
        AccessSite expResult = access_siteDAO.find(access_site.getUser_id());

        // if find aita is empty or null
        if (!access_siteDAO.isValid(expResult)) {           
            // insert access_site in table
            AccessSite result = access_siteDAO.create(access_site);
            // find access_site
            expResult = access_siteDAO.find(access_site.getUser_id());   
            // delete access_site test
            access_siteDAO.delete(result.getUser_id());
            assertEquals(expResult, result); 
         
        }

    }

    /**
     * Test of update method, of class AccessSiteDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        // create object access_site      
        AccessSite access_siteInsert = new AccessSite(8, "U00008", "user8");
        // instatied access_siteDAO
        AccessSiteDAO access_siteDAO = new AccessSiteDAO();
        // find access_site create 
        AccessSite findAccessSite = access_siteDAO.find(access_siteInsert.getUser_id());
        // if find access_site is empty
        if (!access_siteDAO.isValid(findAccessSite)) {
            // Insert access_site in table
            AccessSite resultAccessSite = access_siteDAO.create(access_siteInsert);  
            // find access_site
            findAccessSite = access_siteDAO.find(resultAccessSite.getUser_id());
        }
            // modify access_site
            findAccessSite.setNickname("toto");
            findAccessSite.setPassword("");        
        // update access_site
        boolean result = access_siteDAO.update(findAccessSite);  
        boolean expResult = true;     
        // delete access_site test
        access_siteDAO.delete(findAccessSite.getUser_id());
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class AccessSiteDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        AccessSiteDAO access_siteDAO = new AccessSiteDAO();

        // create object access_site	
        AccessSite access_siteInsert = new AccessSite(9, "U00009", "user9");
        // find access_site  create 
        AccessSite findAccessSite = access_siteDAO.find(access_siteInsert.getUser_id());
   
        // if find access_site is empty
        if (!access_siteDAO.isValid(findAccessSite)) {            
            // Insert access_site in table
            AccessSite resultAccessSite = access_siteDAO.create(access_siteInsert);   
            // find access_site
            findAccessSite = access_siteDAO.find(resultAccessSite.getUser_id());
            // delete access_site
            boolean result = access_siteDAO.delete(findAccessSite.getUser_id());
            boolean expResult = true;
            assertEquals(expResult, result);
        }
    }

    /**
     * Test of findAll method, of class AccessSiteDAO.
     */
    @Test
    public void testGetdAll() {
        System.out.println("findAll");
        AccessSiteDAO access_siteDAO = new AccessSiteDAO();
        ArrayList<AccessSite> arrayAccessSite = access_siteDAO.getAll();
        String expResult = "";
        String result = "";
        for (int i = 0; i < arrayAccessSite.size(); i++) {
            expResult += access_siteDAO.find(arrayAccessSite.get(i).getUser_id());
            result += arrayAccessSite.get(i);
        }
        assertEquals(expResult, result);

    }

    /**
     * Test of find method, of class AccessSiteDAO.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        AccessSiteDAO access_siteDAO = new AccessSiteDAO();
        // assign primary key
        long primary_key = 20;
        // assign result exemple
        String expResult = "AccessSite{user_id=20, nickname=accumsan, "
                + "password=4b6cfa124411971901869dc6e1b00e5d3de5f1cc}";

        // find access_site
        String result = access_siteDAO.find(primary_key).toString();
        assertEquals(expResult, result);
    }
}
