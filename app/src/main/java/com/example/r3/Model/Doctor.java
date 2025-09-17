package com.example.r3.Model;

public class Doctor {
    private int AMKA ;
    private String name ;
    private String password;
    private PhysiotherapyClinic PhysClinic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PhysiotherapyClinic getPhysClinic() {
        return PhysClinic;
    }

    public void setPhysClinic(PhysiotherapyClinic physClinic) {
        PhysClinic = physClinic;
    }

    public int getAMKA() {
        return AMKA;
    }

    public void setAMKA(int AMKA) {
        this.AMKA = AMKA;
    }
}
