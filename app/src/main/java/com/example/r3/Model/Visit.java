package com.example.r3.Model;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Visit {


    private int VisitID;
    private String PatientAMKA;
    private String DoctorID;
    private String provisionID;
    private String VisitDate;
    private String StartTime;
    private String EndTime;
    private int  VisitConfirmed;//1 if comfirmed else 0


    public int getVisitID() {
        return VisitID;
    }

    public String getPatientAMKA() {
        return PatientAMKA;
    }
    public String getDoctorID() {
        return DoctorID;
    }

    public String getProvisionID() {
        return provisionID;
    }

    public String getVisitDate() {
        return VisitDate;
    }

    public String getStartTime() {
        return StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public int getVisitConfirmed() {
        return VisitConfirmed;
    }

}

