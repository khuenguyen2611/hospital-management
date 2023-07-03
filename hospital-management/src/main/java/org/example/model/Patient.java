package org.example.model;

public class Patient extends Person{
    private int patientId;
    private String joinTime;
    private String exitTime;

    public Patient(){

    }

    public Patient(int patientId, String joinTime, String exitTime) {
        this.patientId = patientId;
        this.joinTime = joinTime;
        this.exitTime = exitTime;
    }

    public Patient(String name, String gender, int age, int patientId, String joinTime, String exitTime) {
        super(name, gender, age);
        this.patientId = patientId;
        this.joinTime = joinTime;
        this.exitTime = exitTime;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    @Override
    public String showInfo() {
        return "Patient{" +
                "patientId='" + patientId + '\'' +
                ", joinTime='" + joinTime + '\'' +
                ", exitTime='" + exitTime + '\'' +
                "} " + super.showInfo();
    }
}
