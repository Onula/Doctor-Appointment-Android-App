package com.example.r3.Model;

public class PhysiotherapyClinic {
    int AFM,associationID;
    String name;
    String adress;

    public PhysiotherapyClinic(int PhysiotherapyAFM, int AssociationID, String PhysiotherapyName, String PhysiotherapyAdress){
        AFM = PhysiotherapyAFM;
        associationID = AssociationID;
        name = PhysiotherapyName;
        adress = PhysiotherapyAdress;
    }

    public int getAFM() {
        return AFM;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }
}
