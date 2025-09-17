package com.example.r3.Model;

public class DoctorActivity {
    String ActivityID;
    String ActivityName;
    String ActivityDate;

    public DoctorActivity(String ActividyID, String ActivityName, String ActivityDate){
        ActivityID = ActividyID;
        ActivityName = ActivityName;
        ActivityDate = ActivityDate;
    }
    public String getID() {
        return ActivityID;
    }

    public String getName() {
        return ActivityName;
    }

    public String getDate() {
        return ActivityDate;
    }

    public void setID(String ID) {
        this.ActivityID = ID;
    }

    public void setName(String name) {
        this.ActivityName = name;
    }

    public void setDate(String date) {
        this.ActivityDate = date;
    }

}
