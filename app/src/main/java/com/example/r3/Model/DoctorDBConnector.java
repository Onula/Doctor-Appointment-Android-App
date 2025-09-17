package com.example.r3.Model;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

public class DoctorDBConnector {
    static DBManager dbMan = new DBManager();
    private static String myIP = dbMan.getMyIP();
    private static final String DOCTOR_PHP_SCRIPT_URL = "http://"+myIP+"/PHPDbServices/Doctor.php";
    private String actionName;
    private String url;

    //Insertions ( R3, R8)
    //
    public boolean insertPatient(String DoctorID , String firstName,String lastName, String address, String AMKA ) {
        // Replacing with the current function name to understand the php which action to do
        actionName = "insertPatient";
        url = DOCTOR_PHP_SCRIPT_URL+
                "?action="+actionName+
                "&PatientFirstname="+firstName+
                "&PatientLastname="+lastName+
                "&PatientAddress="+address+
                "&PatientAMKA="+AMKA+
                "&DoctorID="+DoctorID;

        // Build the insertPatient request { buildRequest(..) }
        // Send the request and handle the response { getResponse(..) }
        return dbMan.getResponse(url);
    }


    public boolean insertVisit(String AMKA, String provisionID , String doctorID, String date, String startTime, String endTime) {
        // Replacing with the current function name to understand the php which action to do
        actionName = "insertVisit";
        url = DOCTOR_PHP_SCRIPT_URL+
                "?action="+actionName+
                "&PatientAMKA="+AMKA+
                "&DoctorID="+doctorID+
                "&VisitDate="+date+
                "&StartTime="+startTime+
                "&EndTime="+endTime+
                "&ProvisionID="+provisionID+
                "&VisitConfirmed=1";

        // Build the insertPatient request { buildRequest(..) }
        // Send the request and handle the response { getResponse(..) }
        return dbMan.getResponse(url);
    }


    //Getters (R4, R5 , R6 , R7)

    public ArrayList<HistoryData> getPatientHistory(String AMKA){
        // Replacing with the current function name to understand the php which action to do
        actionName = "getPatientHistory";
        url = DOCTOR_PHP_SCRIPT_URL+
                "?action="+actionName+
                "&PatientAMKA="+AMKA;

        // Build the insertPatient request { buildRequest(..) }
        // Send the request and handle the JSONData response { getJSONDataResponse(String) }
        String jsonData = dbMan.getJSONDataResponse(url);
        //Converting JSON data to ArrayList<HistoryDta>
        ArrayList<HistoryData> historyList = new ArrayList<>();
        if(jsonData != null){
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(jsonData, JsonArray.class);
            for (JsonElement element : jsonArray) {
                HistoryData historyData = gson.fromJson(element, HistoryData.class);
                historyList.add(historyData);
            }
            return historyList;
        }
        return null;
    }

    public Patient getPatientWithAMKA(String AMKA){

        // Replacing with the current function name to understand the php which action to do
        actionName = "getPatientHistory";
        url = DOCTOR_PHP_SCRIPT_URL+
                "?action="+actionName+
                "&PatientAMKA="+AMKA;

        // Build the insertPatient request { buildRequest(..) }
        // Send the request and handle the JSONData response { getJSONDataResponse(String) }
        String jsonData = dbMan.getJSONDataResponse(url);
        //Converting JSON data to Patient
        Patient patient = null;
        if(jsonData != null){
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(jsonData, JsonArray.class);
            for (JsonElement element : jsonArray) {
                patient = gson.fromJson(element, Patient.class);
            }
        }
        return patient;


    }
    public ArrayList<Patient> getAllPatients(){

        // Replacing with the current function name to understand the php which action to do
        actionName = "getAllPatients";
        url = DOCTOR_PHP_SCRIPT_URL+
                "?action="+actionName;

        // Build the insertPatient request { buildRequest(..) }
        // Send the request and handle the JSONData response { getJSONDataResponse(String) }
        String jsonData = dbMan.getJSONDataResponse(url);
        //Converting JSON data to Patient
        ArrayList<Patient> patients = new ArrayList<>();
        if(jsonData != null){
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(jsonData, JsonArray.class);
            for (JsonElement element : jsonArray) {
                Patient patient = gson.fromJson(element, Patient.class);
                patients.add(patient);
            }

        }
        return patients;


    }

    public ArrayList<Visit> getWeeklyConfirmedVisits(String doctorID,String StartOfWeek, String EndOfWeek){
        // Replacing with the current function name to understand the php which action to do
        actionName = "getWeeklyConfirmedVisits";
        url = DOCTOR_PHP_SCRIPT_URL+
                "?action="+actionName+
                "&DoctorID="+doctorID+
                "&StartOfWeek="+StartOfWeek+
                "&EndOfWeek="+EndOfWeek;

        // Build the insertPatient request { buildRequest(..) }
        // Send the request and handle the JSONData response { getJSONDataResponse(String) }
        String jsonData = dbMan.getJSONDataResponse(url);
        //Converting JSON data to ArrayList<Visit>
        ArrayList<Visit> visitList = new ArrayList<>();
        if(jsonData != null){
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(jsonData, JsonArray.class);
            for (JsonElement element : jsonArray) {
                Visit visit = gson.fromJson(element, Visit.class);
                visitList.add(visit);
            }
            return visitList;
        }
        return null;
    }

    public boolean setVisitConfirmed(String visitID){
        // Replacing with the current function name to understand the php which action to do
        actionName = "setVisitConfirmed";
        url = DOCTOR_PHP_SCRIPT_URL+
                "?action="+actionName+
                "&VisitID="+visitID;

        // Build the insertPatient request { buildRequest(..) }
        // Send the request and handle the response { getResponse(..) }
        return dbMan.getResponse(url);

    }

    public ArrayList<Visit> getAllUnConfirmedVisits(){
        // Replacing with the current function name to understand the php which action to do
        actionName = "getPatientHistory";
        url = DOCTOR_PHP_SCRIPT_URL+
                "?action="+actionName;

        // Build the insertPatient request { buildRequest(..) }
        // Send the request and handle the JSONData response { getJSONDataResponse(String) }
        String jsonData = dbMan.getJSONDataResponse(url);
        //Converting JSON data to ArrayList<Visit>
        ArrayList<Visit> unConfirmedvisitList = new ArrayList<>();
        if(jsonData != null){
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(jsonData, JsonArray.class);
            for (JsonElement element : jsonArray) {
                Visit visit = gson.fromJson(element, Visit.class);
                unConfirmedvisitList.add(visit);
            }
            return unConfirmedvisitList;
        }
        return null;
    }

    public ArrayList<String> getDoctorActivity(String doctorID){

        // Replacing with the current function name to understand the php which action to do
        actionName = "getDoctorActivity";
        url = DOCTOR_PHP_SCRIPT_URL+
                "?action="+actionName+
                "&DoctorID="+doctorID;

        // Build the request { buildRequest(..) }
        // Send the request and handle the JSONData response { getJSONDataResponse(String) }

        String jsonData = dbMan.getJSONDataResponse(url);
        //Converting JSON data to ArrayList<Visit>
        ArrayList<String> docActList = new ArrayList<>();
        if(jsonData != null){
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(jsonData, JsonArray.class);
            for (JsonElement element : jsonArray) {
                DoctorActivity activity = gson.fromJson(element, DoctorActivity.class);
                docActList.add(activity.getName());

            }
            return docActList;
        }
        return null;

    }

}
