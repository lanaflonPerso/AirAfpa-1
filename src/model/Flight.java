/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 * @author Cedric DELHOME
 * @author Laure-Helene Soyeux
 */
public class Flight {
    // name columns in table flights
    private long id;
    private String departing_aita;
    private String arrival_aita;
    private String departing_hour;
    private int duration;
    private double price;
    private long id_pilot;
    private long id_copilot;
    private long id_staff1;
    private long id_staff2;
    private long id_staff3;
    private boolean planned;
    
    // this construct create flight empty 
    public Flight() {
        this.id = 0;
        this.departing_aita = "";
        this.arrival_aita = "";
        this.departing_hour = "";
        this.duration = 0;
        this.price = 0;
        this.id_pilot = 0;
        this.id_copilot = 0;
        this.id_staff1 = 0;
        this.id_staff2 = 0;
        this.id_staff3 = 0;
        this.planned = false;
    }
     // this construct create flight full
    public Flight(
            long id, String departing_aita, String arrival_aita, 
            String departing_hour, int duration, double price, long id_pilot, 
            long id_copilot, long id_staff1, long id_staff2, long id_staff3, 
            boolean planned) {
        this.id = id;
        this.departing_aita = departing_aita;
        this.arrival_aita = arrival_aita;
        this.departing_hour = departing_hour;
        this.duration = duration;
        this.price = price;
        this.id_pilot = id_pilot;
        this.id_copilot = id_copilot;
        this.id_staff1 = id_staff1;
        this.id_staff2 = id_staff2;
        this.id_staff3 = id_staff3;
        this.planned = planned;
    }

    public long getId() {
        return id;
    }

    public String getDeparting_aita() {
        return departing_aita;
    }

    public String getArrival_aita() {
        return arrival_aita;
    }

    public String getDeparting_hour() {
        return departing_hour;
    }

    public int getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }

    public long getId_pilot() {
        return id_pilot;
    }

    public long getId_copilot() {
        return id_copilot;
    }

    public long getId_staff1() {
        return id_staff1;
    }

    public long getId_staff2() {
        return id_staff2;
    }

    public long getId_staff3() {
        return id_staff3;
    }

    public boolean getPlanned() {
        return planned;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setDeparting_aita(String departing_aita) {
        this.departing_aita = departing_aita;
    }

    public void setArrival_aita(String arrival_aita) {
        this.arrival_aita = arrival_aita;
    }

    public void setDeparting_hour(String departing_hour) {
        this.departing_hour = departing_hour;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId_pilot(long id_pilot) {
        this.id_pilot = id_pilot;
    }

    public void setId_copilot(long id_copilot) {
        this.id_copilot = id_copilot;
    }

    public void setId_staff1(long id_staff1) {
        this.id_staff1 = id_staff1;
    }

    public void setId_staff2(long id_staff2) {
        this.id_staff2 = id_staff2;
    }

    public void setId_staff3(long id_staff3) {
        this.id_staff3 = id_staff3;
    }

    public void setPlanned(boolean planned) {
        this.planned = planned;
    }

    @Override
    public String toString() {
        return "Flight{" + "id=" + id + ", departing_aita=" + departing_aita 
                + ", arrival_aita=" + arrival_aita + ", departing_hour=" + 
                departing_hour + ", duration=" + duration + ", price=" + price 
                + ", id_pilot=" + id_pilot + ", id_copilot=" + id_copilot 
                + ", id_staff1=" + id_staff1 + ", id_staff2=" + id_staff2 
                + ", id_staff3=" + id_staff3 + ", planned=" + planned + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + Objects.hashCode(this.departing_aita);
        hash = 29 * hash + Objects.hashCode(this.arrival_aita);
        hash = 29 * hash + Objects.hashCode(this.departing_hour);
        hash = 29 * hash + this.duration;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.price) ^ 
                            (Double.doubleToLongBits(this.price) >>> 32));
        hash = 29 * hash + (int) (this.id_pilot ^ (this.id_pilot >>> 32));
        hash = 29 * hash + (int) (this.id_copilot ^ (this.id_copilot >>> 32));
        hash = 29 * hash + (int) (this.id_staff1 ^ (this.id_staff1 >>> 32));
        hash = 29 * hash + (int) (this.id_staff2 ^ (this.id_staff2 >>> 32));
        hash = 29 * hash + (int) (this.id_staff3 ^ (this.id_staff3 >>> 32));
        hash = 29 * hash + (this.planned ? 1 : 0);
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
        final Flight other = (Flight) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.duration != other.duration) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (this.id_pilot != other.id_pilot) {
            return false;
        }
        if (this.id_copilot != other.id_copilot) {
            return false;
        }
        if (this.id_staff1 != other.id_staff1) {
            return false;
        }
        if (this.id_staff2 != other.id_staff2) {
            return false;
        }
        if (this.id_staff3 != other.id_staff3) {
            return false;
        }
        if (this.planned != other.planned) {
            return false;
        }
        if (!Objects.equals(this.departing_aita, other.departing_aita)) {
            return false;
        }
        if (!Objects.equals(this.arrival_aita, other.arrival_aita)) {
            return false;
        }
        if (!Objects.equals(this.departing_hour, other.departing_hour)) {
            return false;
        }
        return true;
    }

    
    
}
