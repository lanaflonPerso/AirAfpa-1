/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airafpa;

import controller.WelcomeController;
import dao.AccessBackofficeDAO;
import view.WelcomeView;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import model.AccessBackoffice;
import view.FlightView;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 * @author Cedric DELHOME
 * @author Laure-Helene Soyeux
 */
public class AirAfpa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            AccessBackofficeDAO accessBackofficeDAO = new AccessBackofficeDAO();
            WelcomeController welcomeController = new WelcomeController(accessBackofficeDAO);
           new WelcomeView(welcomeController);  

 
    }
}
