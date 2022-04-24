package com.electrogrid.PAF.model;

import jakarta.persistence.*;

@Entity
public class Bill {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nic")
    private String nic;
    @Basic
    @Column(name = "price")
    private String price;
    @Basic
    @Column(name = "date")
    private String date;
    @Basic
    @Column(name = "meterReader")
    private String meterReader;

    public Bill(String nic, String price, String date, String meterReader) {
        this.nic = nic;
        this.price = price;
        this.date = date;
        this.meterReader = meterReader;
    }

    public Bill() {

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

    public String getMeterReader() {
        return meterReader;
    }

    public void setMeterReader(String meterReader) {
        this.meterReader = meterReader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bill bill = (Bill) o;

        if (id != bill.id) return false;
        if (nic != null ? !nic.equals(bill.nic) : bill.nic != null) return false;
        if (price != null ? !price.equals(bill.price) : bill.price != null) return false;
        if (date != null ? !date.equals(bill.date) : bill.date != null) return false;
        if (meterReader != null ? !meterReader.equals(bill.meterReader) : bill.meterReader != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nic != null ? nic.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (meterReader != null ? meterReader.hashCode() : 0);
        return result;
    }
}
