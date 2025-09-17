package com.example.r3.Model;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

import java.util.ArrayList;

public class PatientDBConnector {
    static DBManager dbMan = new DBManager();
    private static String myIP = dbMan.getMyIP();
    private static final String PATIENT_PHP_SCRIPT_URL = "http://"+myIP+"/physiotherapyDBServices/Patient.php";
    private String actionName;
    private String url;

    public boolean insertUnConfirmedVisit(String AMKA, String provisionID , String doctorID, String date, String startTime, String endTime) {
        // Replacing with the current function name to understand the php which action to do
        actionName = "insertVisit";
        url = PATIENT_PHP_SCRIPT_URL+
                "?action="+actionName+
                "&PatientAMKA="+AMKA+
                "&DoctorID="+doctorID+
                "&VisitDate="+date+
                "&StartTime="+startTime+
                "&EndTime="+endTime+
                "&ProvisionID="+provisionID+
                "&VisitConfirmed=0";

        // Build the insertPatient request { buildRequest(..) }
        // Send the request and handle the response { getResponse(..) }
        return dbMan.getResponse(url);//TRUE or FALSE
    }

    public ArrayList<PatientMove> getPatientMovement(String AMKA){
        // Replacing with the current function name to understand the php which action to do
        actionName = "getPatientHistory";
        url = PATIENT_PHP_SCRIPT_URL+
                "?action="+actionName+
                "&PatientAMKA="+AMKA;

        // Build the insertPatient request { buildRequest(..) }
        // Send the request and handle the JSONData response { getJSONDataResponse(String) }
        String jsonData = dbMan.getJSONDataResponse(url);
        //Converting JSON data to ArrayList<HistoryDta>
        ArrayList<PatientMove> moveList = new ArrayList<>();
        if(jsonData != null){
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(jsonData, JsonArray.class);
            for (JsonElement element : jsonArray) {
                PatientMove patientMove = gson.fromJson(element, PatientMove.class);
                moveList.add(patientMove);
            }
            return moveList;
        }
        return null;
    }
}
