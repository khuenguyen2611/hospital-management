package org.example.model;

public class Doctor extends Person{
    private String doctorId;
    private String roomCode;

    public Doctor(){

    }

    public Doctor(String doctorId, String roomCode) {
        this.doctorId = doctorId;
        this.roomCode = roomCode;
    }

    public Doctor(String name, String gender, int age, String doctorId, String roomCode) {
        super(name, gender, age);
        this.doctorId = doctorId;
        this.roomCode = roomCode;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    @Override
    public String showInfo() {
        return "Doctor{" +
                "doctorId='" + doctorId + '\'' +
                ", roomCode='" + roomCode + '\'' +
                "} " + super.showInfo();
    }
}
