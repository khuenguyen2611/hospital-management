package org.example.view;

import org.example.controller.HospitalController;

import java.util.Scanner;

public class Menu {
    private static HospitalController hospitalController = new HospitalController();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static void menu(){
        int choice;
        System.out.println("===== HOSPITAL MANAGEMENT =====");
        System.out.println("1. Patient Services");
        System.out.println("2. Import Hospital Data");
        System.out.println("3. List all Doctors");
        System.out.println("4. Sort and print hospital data");
        System.out.print("Your choice: ");

        choice = sc.nextInt();
        switch (choice){
            case 1 -> {
                patientService();
            }
            case 2 -> {
                sc.nextLine();
                System.out.print("Please enter file name: ");
                hospitalController.importPersonFromFile(sc.nextLine());
                menu();
            }
            case 3 -> {
                hospitalController.listAllDoctors();
                menu();
            }
            case 4 -> {
                hospitalController.sortHospitalData();
                menu();
            }
            default -> {
                System.exit(0);
            }
        }
    }

    private static void patientService(){
        System.out.println("\n===== PATIENT SERVICE =====");
        System.out.println("1. Add/Import Patient");
        System.out.println("2. Update Patient");
        System.out.println("3. Delete Patient by Id");
        System.out.println("4. Export Patients");
        System.out.println("5. List all Patients");
        System.out.println("6. Back to menu");
        System.out.print("Your choice : ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                importPatient();
                System.out.println(" ");
                menu();
            }
            case 2 -> {
                sc.nextLine();
                hospitalController.updatePatient();
                System.out.println(" ");
                menu();
            }
            case 3 -> {
                hospitalController.deletePatientById();
                menu();
            }
            case 4 -> {
                hospitalController.listAllPatient();
                System.out.println(" ");
                menu();
            }
            case 5 -> {
                hospitalController.listAllPatient();
                System.out.println(" ");
                menu();
            }
            case 6 -> {
                menu();
            }
            default -> {
                menu();
            }
        }
    }
    private static void importPatient(){
        int choice;
        System.out.println("\n===== IMPORT PATIENT =====");
        System.out.println("1. Import by code");
        System.out.println("2. Import by file");
        System.out.println("3. Back to menu");
        System.out.println("Your choice : ");
        choice = sc.nextInt();
        switch (choice){
            case 1 -> {
//                sc.nextLine();
                hospitalController.addPatient();
                menu();
            }
            case 2 -> {
                sc.nextLine();
                System.out.print("Please enter file name: ");
                hospitalController.importPatientFromFile(sc.nextLine());
                menu();
            }
            default -> {
                menu();
            }
        }
    }
}