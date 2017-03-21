/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Airport;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 * @author Cedric DELHOME
 * @author Laure-Helene Soyeux
 */
public class AirportDAOTest {

    public AirportDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class AirportDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        // create object airport
        Airport airport = new Airport("ACE", "île de Lanzarote, Canaries", "Espagne");
        AirportDAO airportDAO = new AirportDAO();
        // find airport  create 
        Airport expResult = airportDAO.find(airport.getAita());
        
        // if find aita is empty or null
        if (!airportDAO.isValid(expResult)) {
            // insert airport in table
            Airport result = airportDAO.create(airport);   
            // find airport
            expResult = airportDAO.find(airport.getAita());
            // delete airport
            airportDAO.delete(result.getAita());
            assertEquals(expResult, result);          
        }

    }

    /**
     * Test of update method, of class AirportDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        // create object airport
        Airport airportInsert = new Airport("AEK", "Aseki", "Papouasie-Nouvelle-Guinée");
        AirportDAO airportDAO = new AirportDAO();
        // find airport create 
        Airport findAirport = airportDAO.find(airportInsert.getAita());
        
        // if find airport is empty
        if (!airportDAO.isValid(findAirport)) {
            // Insert airport in table
            Airport resultAirport = airportDAO.create(airportInsert);  
            // find airport
            findAirport = airportDAO.find(resultAirport.getAita());    
        }
            // modify airport
            findAirport.setCity("update Aseki");
            findAirport.setCountry("update Papouasie-Nouvelle-Guinée");         
            
   
        // update airport
        boolean result = airportDAO.update(findAirport);   
        boolean expResult = true;
        // delete airport
        airportDAO.delete(findAirport.getAita());
        assertEquals(expResult, result);
    }
    /**
     * Test of delete method, of class AirportDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        AirportDAO airportDAO = new AirportDAO();
        
        // create object airport
        Airport airportInsert = new Airport("AGA", "agadir", "Maroc");
        // find airport  create 
        Airport findAirport = airportDAO.find(airportInsert.getAita());
        
        // if find airport is empty
       if (!airportDAO.isValid(findAirport)) {
            // Insert airport in table
            Airport resultAirport = airportDAO.create(airportInsert);  
            // find airport
            findAirport = airportDAO.find(resultAirport.getAita());
            System.out.println("aaa " + findAirport);
            // delete airport
            boolean result = airportDAO.delete(findAirport.getAita());
                        System.out.println("aaa " + result);
            boolean expResult = true;
            assertEquals(expResult, result);
        }
    }
    /**
     * Test of findAll method, of class AirportDAO.
     */
    @Test
    public void testGetdAll() {
        System.out.println("findAll");
        AirportDAO airportDAO = new AirportDAO();
        ArrayList<Airport> arrayAirport = airportDAO.getAll();
        String expResult = "";
        String result = "";
        for (int i = 0; i < arrayAirport.size(); i++) {
            expResult += airportDAO.find(arrayAirport.get(i).getAita());
            result += arrayAirport.get(i);
        }
        assertEquals(expResult, result);

    }

    /**
     * Test of find method, of class AirportDAO.
     */
    @Test
    public void testFind() {
        System.out.println("find"); 
        AirportDAO airportDAO = new AirportDAO();
        // assign primary key
        String primary_key = "DXB";
        // assign result exemple
        String expResult = "Airport{aita=DXB, city=Dubaï, country=Émirats arabes unis}";
        // find airport
        String result = airportDAO.find(primary_key).toString();
        
        assertEquals(expResult, result);
    }
}
