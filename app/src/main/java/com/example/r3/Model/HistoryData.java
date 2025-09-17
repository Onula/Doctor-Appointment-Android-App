package com.example.r3.Model;

public class HistoryData {
    private String ProvisionName;
    private String VisitDate;


    public HistoryData(String provision, String date) {
        this.ProvisionName = provision;
        this.VisitDate = date;
    }

    public String getDate() {
        return VisitDate;
    }

    public String getProvision() {
        return ProvisionName;
    }

    public void printData(){
        System.out.println("-Name: " + ProvisionName);
        System.out.println("-Date: " + VisitDate);
    }
}