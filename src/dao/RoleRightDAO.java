/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import model.RoleRight;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 * @author Cedric DELHOME
 * @author Laure-Helene Soyeux
 */
public class RoleRightDAO extends DAO<RoleRight, Long> {

    public RoleRightDAO() {
        super();
    }

    /**
     *
     * Create role_right
     *
     * @param role_right
     * @return role_right object
     */
    @Override
    public RoleRight create(RoleRight role_right) {

        RoleRight role_rightCreate = new RoleRight();
        if (this.bddmanager.connect()) {
            try {

                // create requete 
                String requete = "INSERT INTO roles_rights ( "
                        + "user_id,\n"
                        + " admin,\n"
                        + " blocked\n"
                        + ") VALUES (?,?,?)";
                // prepared requete and get return generated key
                PreparedStatement pst = this.bddmanager.getConnectionManager()
                        .prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
                // insert value in requete
                pst.setLong(1, role_right.getUser_id());
                pst.setBoolean(2, role_right.isAdmin());
                pst.setBoolean(3, role_right.isBlocked());
                // excute insert row in table
                int insert = pst.executeUpdate();
                // if insert in table 
                if (insert != 0) {
  
                        // assign object role_right in object return
                        role_rightCreate = role_right;
              

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return role_rightCreate;
            }

        } else {
            return role_rightCreate;
        }

        return role_rightCreate;
    }

    /**
     * Update role_right
     *
     * @param role_right
     * @return true is update role_right, false isn't update
     */
    @Override
    public boolean update(RoleRight role_right) {
        boolean success = false;

        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "Update roles_rights set"
                        + " admin = ?,\n"
                        + " blocked = ?\n"
                        + " WHERE user_id = ?";
                // prepared requete 
                PreparedStatement pst = this.bddmanager
                        .getConnectionManager().prepareStatement(requete);
                // insert value in requete
                pst.setBoolean(1, role_right.isAdmin());
                pst.setBoolean(2, role_right.isBlocked());
                pst.setLong(3, role_right.getUser_id());
                // excute update row in table
                int insert = pst.executeUpdate();
                // if insert in table 
                if (insert != 0) {
                    success = true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return success;
            }

        } else {
            return success;
        }
        return success;
    }

    /**
     * delete role_right
     *
     * @param primary_key
     * @return true is delete role_right, false isn't delete
     */
    @Override
    public boolean delete(Long primary_key) {
        boolean success = false;

        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "DELETE FROM roles_rights WHERE user_id = ?";
                // prepared requete 
                PreparedStatement pst = this.bddmanager.getConnectionManager()
                        .prepareStatement(requete);
                // insert value in requete
                pst.setLong(1, primary_key);
                // excute delete row in table
                int insert = pst.executeUpdate();
                // if delete in table 
                if (insert != 0) {
                    success = true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return success;
            }

        } else {
            return success;
        }
        return success;

    }

    /**
     * get all role_rights
     *
     * @return all role_rights
     */
    @Override
    public ArrayList getAll() {
        // create array list role_right empty
        ArrayList<RoleRight> listRoleRight = new ArrayList<>();
        if (this.bddmanager.connect()) {

            try {
                // create statement 
                Statement st = this.bddmanager
                        .getConnectionManager()
                        .createStatement();
                // create requete 
                String requete = "SELECT * FROM roles_rights";
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // insert all rorl in array object role_right

                while (rs.next()) {
                    RoleRight el = new RoleRight(
                            rs.getLong("user_id"),
                            rs.getBoolean("admin"),
                            rs.getBoolean("blocked")
                    );
                    listRoleRight.add(el);

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return listRoleRight;
            }

        } else {
            return listRoleRight;
        }

        return listRoleRight;
    }

    /**
     * find role_right
     *
     * @param primary_key
     * @return role_right
     */
    @Override
    public RoleRight find(Long primary_key) {
        // create array role_right empty
        RoleRight role_right = new RoleRight();
        //check if connect db
        if (this.bddmanager.connect()) {

            try {
                // create statement for find 
                Statement st = this.bddmanager.getConnectionManager()
                        .createStatement();
                // create requete add primary key
                String requete = "SELECT * FROM roles_rights WHERE user_id = " + primary_key;
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // if result is full
                if (rs.next()) {
                    // insert role_right in object                 
                    role_right.setUser_id(rs.getLong("user_id"));
                    role_right.setAdmin(rs.getBoolean("admin"));
                    role_right.setBlocked(rs.getBoolean("blocked"));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return role_right;
            }

        } else {
            return role_right;
        }

        return role_right;
    }

    /**
     * It checks if the object role_right is filled or empty
     *
     * @param role_right
     * @return false is empty and true is full
     */
    public boolean isValid(RoleRight role_right) {
        boolean isValid = true;

        // if id is empty
        if (role_right.getUser_id()== 0) {
            isValid = false;
        }

        return isValid;
    }

}
