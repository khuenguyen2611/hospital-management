package org.example.controller;

import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.model.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HospitalController {
    private static List<Person> hospitalLists = new ArrayList<>();
    
    private static Scanner sc = new Scanner(System.in);

    //Add new Patient from File
    public void importPatientFromFile(String fileName){
        try{
            FileReader fr = new FileReader("src/main/resources/" + fileName + ".txt");
            BufferedReader bf = new BufferedReader(fr);

            String line;
            while((line = bf.readLine()) != null){
                String[] patientData = line.split(",");
                Patient patient = new Patient(patientData[0], patientData[1], Integer.parseInt(patientData[2]),
                        Integer.parseInt(patientData[3]), patientData[4], patientData[5]);

                hospitalLists.add(patient);
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

    //Export Patient

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
}
