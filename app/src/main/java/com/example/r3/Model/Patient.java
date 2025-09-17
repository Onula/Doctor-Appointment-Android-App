package com.example.r3.Model;

public class Patient {
    private String PatientAMKA;
    private String UserType;
    private String PatientFirstname;
    private String PatientLastname;
    private String PatientAddress;
    private String PatientBirth;
    public Patient(String aname, String alastname, String anaddress, String anamka){
        this.PatientFirstname=aname;
        this.PatientLastname=alastname;
        this.PatientAddress=anaddress;
        this.PatientAMKA=anamka;

    }
    public String getPatientAMKA() {
        return PatientAMKA;
    }

    public String getUserType() {
        return UserType;
    }

    public String getPatientFirstname() {
        return PatientFirstname;
    }

    public String getPatientLastname() {
        return PatientLastname;
    }

    public String getPatientAddress() {
        return PatientAddress;
    }

    public String getPatientBirth() {
        return PatientBirth;
    }
}
