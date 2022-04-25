package com.electrogrid.PAF.model;

import jakarta.persistence.*;

@Entity
public class Payment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nic")
    private String nic;
    @Basic
    @Column(name = "month")
    private String month;
    @Basic
    @Column(name = "price")
    private String price;
    @Basic
    @Column(name = "date")
    private String date;

    public Payment(String nic, String month, String price, String date) {
        this.nic = nic;
        this.month = month;
        this.price = price;
        this.date = date;
    }

    public Payment() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (id != payment.id) return false;
        if (nic != null ? !nic.equals(payment.nic) : payment.nic != null) return false;
        if (month != null ? !month.equals(payment.month) : payment.month != null) return false;
        if (price != null ? !price.equals(payment.price) : payment.price != null) return false;
        if (date != null ? !date.equals(payment.date) : payment.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nic != null ? nic.hashCode() : 0);
        result = 31 * result + (month != null ? month.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
