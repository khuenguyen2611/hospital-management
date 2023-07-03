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
        System.out.println("1. Import Patient");
        System.out.println("2. Update Patient");
        System.out.println("3. Export Patient");
        System.out.println("4. List all Patient");
        System.out.println("5. Import Hospital Data");
        System.out.println("6. List all Doctors");
        System.out.println("7. Sort and print hospital data");
        System.out.print("Your choice: ");

        choice = sc.nextInt();
        switch (choice){
            case 1 -> {
                sc.nextLine();
                System.out.print("Please enter file name: ");
                hospitalController.importPatientFromFile(sc.nextLine());
                menu();
            }
            case 2 -> {
                sc.nextLine();
                hospitalController.updatePatient();
                menu();
            }
            case 3 -> {
                sc.nextLine();
                System.out.println("Please enter export file name: ");
                hospitalController.exportPatientIntoFile(sc.nextLine());
            }
            case 4 -> {
                hospitalController.listAllPatient();
                menu();
            }
            case 5 -> {
                sc.nextLine();
                System.out.print("Please enter file name: ");
                hospitalController.importPersonFromFile(sc.nextLine());
                menu();
            }
            case 6 -> {
               hospitalController.listAllDoctors();
               menu();
            }
            case 7 -> {
                hospitalController.sortHospitalData();
                menu();
            }
        }
    }
}