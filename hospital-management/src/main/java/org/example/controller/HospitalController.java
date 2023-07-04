package org.example.controller;

import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.model.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class HospitalController {
    private static List<Person> hospitalLists = new ArrayList<>();
    
    private static Scanner sc = new Scanner(System.in);

    //Add Patient
    public void addPatient(){
        Patient patient = new Patient();
        System.out.println("\n===== ADD NEW PATIENT =====");

        System.out.print("Please enter patient name : ");
        patient.setName(sc.nextLine());

        boolean isGenderValid = false;
        while (!isGenderValid){
            System.out.print("Please enter patient gender : ");
            String gender = sc.nextLine();
            isGenderValid = validateGender(gender);
            if(isGenderValid == true){
                patient.setGender(gender.toUpperCase());
            }
        }

        System.out.print("Please enter patient age : ");
        patient.setAge(sc.nextInt());

        //Unique
        boolean isIdValid = false;
        while (!isIdValid){
            System.out.print("Please enter patient id : ");
            int id = sc.nextInt();
            isIdValid = validateId(id);
            if(isIdValid){
                patient.setPatientId(id);
            }
            else {
                System.out.println("Id has existed. Please enter other id!!!");
            }
        }


        sc.nextLine();

        boolean isJoinTimeValid = false;
        while (!isJoinTimeValid){
            System.out.print("Please enter patient Join Time: ");
            String joinTime = sc.nextLine();
            isJoinTimeValid = validateDate(joinTime);
            if(isJoinTimeValid == true){
                patient.setJoinTime(joinTime);
            }
            else {
                System.out.println("Date are not in dd/MM/yyyy format.");
            }
        }


        System.out.print("Please enter patient Exit Time: ");
        patient.setExitTime(sc.nextLine());

        hospitalLists.add(patient);
    }

    //Import new Patient from File
    public void importPatientFromFile(String fileName){
        try{
            FileReader fr = new FileReader("src/main/resources/" + fileName + ".txt");
            BufferedReader bf = new BufferedReader(fr);

            String line;
            while((line = bf.readLine()) != null){
                String[] patientData = line.split(",");
                Patient patient = new Patient(patientData[0], patientData[1], Integer.parseInt(patientData[2]),
                        Integer.parseInt(patientData[3]), patientData[4], patientData[5]);

                if(validateId(patient.getPatientId()) == true){
                    hospitalLists.add(patient);
                }
                else{
                    System.out.println("Patient ID has existed");
                    System.out.println("Existed patient: " + getPatientById(patient.getPatientId()).showInfo());
                    System.out.println("Imported patient: " + patient.showInfo());
                    System.out.print("Do you want to override old data (y/N): ");
                    String choice = sc.nextLine();
                    switch (choice.trim().toLowerCase()){
                        case "y" -> {
                            hospitalLists.remove(getPatientById(patient.getPatientId()));
                            hospitalLists.add(patient);
                        }
                        case "n" -> {

                        }
                    }
                }
            }

            fr.close();
        }
        catch(IOException e){
            System.out.println("File not found!");
        }
        finally {
            System.out.println("Add patient successfully!\n");
        }
    }

    //Export Patients into File
    public void exportPatientIntoFile(String fileName){
        try{
            FileWriter fw = new FileWriter("src/main/resources/" + fileName + ".txt");
            for (Person person : hospitalLists){
                if(person instanceof Patient){
                    fw.write(person.getName() + "," + person.getGender() + "," + person.getAge() + "," + ((Patient) person).getPatientId() +
                            "," + ((Patient) person).getJoinTime() + "," + ((Patient) person).getExitTime() + "\n");
                }
            }
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            System.out.println("Export successfully!\n");
        }
    }
    
    //Search patient
    public static int searchPatient(int patientID){
        for (Person person : hospitalLists){
            if(person instanceof Patient){
                if(((Patient) person).getPatientId() == patientID){
                    return patientID;
                }
            }
        }
        return -1;
    }

    //Get Patient by Id
    public static Patient getPatientById(int patientID){
        for (Person person : hospitalLists){
            if(person instanceof Patient){
                //If found Patient ?
                if(((Patient) person).getPatientId() == patientID){
                    return (Patient) person;
                }
            }
        }
        return null;
    }

    //Update Patient
    public void updatePatient(){
        //Check if patient exist ?
        System.out.print("Please enter patient ID: ");
        int result = searchPatient(sc.nextInt());

        if(result != -1){
            for (Person person : hospitalLists){
                if(person instanceof Patient){
                    if(((Patient) person).getPatientId() == result){
                        sc.nextLine();
                        System.out.print("Please enter new patient name: ");
                        person.setName(sc.nextLine());
                        System.out.print("Please enter new age: ");
                        person.setAge(sc.nextInt());
                    }
                }
            }
        }
        else{
            System.out.println("Patient not found!");
        }
    }

    //List all Patient
    public void listAllPatient(){
        for (Person person : hospitalLists){
            if(person instanceof Patient){
                System.out.println(person.showInfo());
            }
        }
    }

    //Import Persons from File
    public void importPersonFromFile(String fileName){
        int count = 0;
        try{
            FileReader fr = new FileReader("src/main/resources/" + fileName + ".txt");
            BufferedReader bf = new BufferedReader(fr);

            String line;
            while((line = bf.readLine()) != null){
                String[] personData = line.split(",");

                if(personData.length == 6){
                    Patient patient = new Patient(personData[0], personData[1], Integer.parseInt(personData[2]),
                            Integer.parseInt(personData[3]), personData[4], personData[5]);
                    hospitalLists.add(patient);
                }
                else if(personData.length == 5){
                    Doctor doctor = new Doctor(personData[0], personData[1], Integer.parseInt(personData[2]), personData[3], personData[4]);
                    hospitalLists.add(doctor);
                }
                else{
                    count++;
                }
            }

            fr.close();
        }
        catch(IOException e){
            System.out.println("File not found!");
        }
        finally {
            if(count == 0){
                System.out.println("Add patient successfully!\n");
            }
            else {
                System.out.println(count + " line(s) are not in format !!!");
            }
        }
    }

    //List all Doctors
    public void listAllDoctors(){
        for (Person person : hospitalLists){
            if(person instanceof Doctor){
                System.out.println(person.showInfo());
            }
        }
    }

    public void sortHospitalData(){
        Collections.sort(hospitalLists, new Comparator<Person>() {
            //Compare name
//            @Override
//            public int compare(Person o1, Person o2) {
//                String name1 = o1.getName();
//                String name2 = o2.getName();
//
//                String[] person1 = name1.split(" ");
//                String[] person2 = name2.split(" ");
//
//                name1 = person1[person1.length - 1];
//                name2 = person2[person2.length - 1];
//                return name1.compareTo(name2);
//            }

            //Compare họ
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        for (Person person : hospitalLists){
            System.out.println(person.showInfo());
        }
        System.out.println("");
    }

    public void deletePatientById(){
        System.out.print("Please enter patient id to delete: ");
        Patient patient = getPatientById(sc.nextInt());

        if(patient == null){
            System.out.println("Patient is not existed !!!\n");
        }
        else {
            sc.nextLine();
            System.out.println("Delete " + patient.showInfo());
            System.out.print("[Y/n] :");
            String choice = sc.nextLine().trim().toLowerCase();
            switch (choice){
                case "y" -> {
                    hospitalLists.remove(patient);
                    System.out.println("Delete patient successfully!!!\n");
                }
                case "n" -> {

                }
                default -> {

                }
            }
        }
    }

    //Validate
    private static boolean validateGender(String gender){
        if(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")){
            return true;
        }
        return false;
    }

    private static boolean validateId(int id){
        for (Person person : hospitalLists){
            if(person instanceof Patient){
                if(((Patient) person).getPatientId() == id){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean validateDate(String date){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate.parse(date, dateFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
