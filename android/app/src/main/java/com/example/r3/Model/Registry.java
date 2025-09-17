package com.example.r3.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registry {
    public static boolean correct;
    private Map<String, Patient> patientMap;
    private List<Patient> patientList;


    public Registry(){
        patientMap=new HashMap<>();
        this.patientList=new ArrayList<>();
    }

    public void addPatient(Patient patient){
        String amka=patient.getPatientAMKA();
        if (patientMap.containsKey(amka)) {
            System.out.println("Patient with AMKA " + amka + " already exists!");
            correct=false;
        }
        else{
            patientList.add(patient);
            patientMap.put(String.valueOf(amka), patient);
            correct=true;
        }
    }
}
