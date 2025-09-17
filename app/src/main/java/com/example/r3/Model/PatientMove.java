package com.example.r3.Model;

public class PatientMove {
    String ProvisionName;
    String ProvisionPrice;

    public PatientMove(String name,String  price){
        this.ProvisionName = name;
        this.ProvisionPrice = price;
    }

    public String getProvisionName() {
        return ProvisionName;
    }

    public String getProvisionPrice() {
        return ProvisionPrice;
    }

    public void printData(){
        System.out.println("Name: " + ProvisionName);
        System.out.println("Name: " + ProvisionPrice);
    }
}