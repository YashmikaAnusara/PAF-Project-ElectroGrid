package com.electrogrid.PAF.model;

import jakarta.persistence.*;

@Entity
public class Payment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "paid")
    private String paid;
    @Basic
    @Column(name = "cvv")
    private String cvv;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "date")
    private String date;

    @Basic
    @Column(name = "amount")
    private String amount;

    public Payment(String paid, String cvv, String name, String date, String amount) {
        this.paid = paid;
        this.cvv = cvv;
        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    public Payment() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (id != payment.id) return false;
        if (paid != null ? !paid.equals(payment.paid) : payment.paid != null) return false;
        if (cvv != null ? !cvv.equals(payment.cvv) : payment.cvv != null) return false;
        if (name != null ? !name.equals(payment.name) : payment.name != null) return false;
        if (date != null ? !date.equals(payment.date) : payment.date != null) return false;
        if (amount != null ? !amount.equals(payment.amount) : payment.amount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (paid != null ? paid.hashCode() : 0);
        result = 31 * result + (cvv != null ? cvv.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
