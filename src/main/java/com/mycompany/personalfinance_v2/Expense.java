package com.mycompany.personalfinance_v2;
import java.io.Serializable;
import java.util.Date;

public class Expense implements Serializable{
    private int id;
    private String name;
    private Date date;
    private double amount;

    public Expense(int id, String name, Date date, double amount) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.amount = amount;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    public double getAmount() {
        return amount;
    }


    public void setAmount(double amount) {
        this.amount = amount;
    }

   
}
