/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 * @author Cedric DELHOME
 * @author Laure-Helene Soyeux
 */
public class RolesRights {
    private long user_id;
    private boolean admin;
    private boolean blocked;
    public RolesRights() {
        this.user_id = 0;
        this.admin = false;
        this.blocked = false;
    }
    public RolesRights(long user_id, boolean admin, boolean blocked) {
        this.user_id = user_id;
        this.admin = admin;
        this.blocked = blocked;
    }

    public long getUser_id() {
        return user_id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        return "RolesRights{" + "user_id=" + user_id + ", admin=" + admin + ", blocked=" + blocked + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + (int) (this.user_id ^ (this.user_id >>> 32));
        hash = 73 * hash + (this.admin ? 1 : 0);
        hash = 73 * hash + (this.blocked ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RolesRights other = (RolesRights) obj;
        if (this.user_id != other.user_id) {
            return false;
        }
        if (this.admin != other.admin) {
            return false;
        }
        if (this.blocked != other.blocked) {
            return false;
        }
        return true;
    }
    
}
