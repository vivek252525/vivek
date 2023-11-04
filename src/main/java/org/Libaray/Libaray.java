package org.Libaray;

import java.util.List;
import java.util.Scanner;

public class Libaray {
    public static void main(String[] args) {
        LibraryFunctions lb = new LibraryFunctions();
        LabDatabase la = new LabDatabase();
        System.out.println("Welcome to online Library");
        System.out.println("A : Admin   \t\tS : Student");
        Scanner sc = new Scanner(System.in);
        String sa = sc.nextLine();
        switch (sa.toUpperCase()) {
            case "A":
                System.out.println("L : login   \t\tN : new admin");
                String c = sc.nextLine();
                if (c.equalsIgnoreCase("n")) {
                    la.CreatTableOfUser();
                    System.out.println("Enter your password");
                    String pass = sc.nextLine();
                    System.out.println("Enter your user name");
                    String user = sc.nextLine();
                    UserAccount ua = new UserAccount(user, pass);
                    la.InsertInToUserTable(ua);
                    List<UserAccount> mpp = la.getAllAdminDetails();
                    for (int i = 0; i < mpp.size(); i++) {
                        if (pass.equalsIgnoreCase(mpp.get(i).getPassword())) {
                            System.out.println("Welcome");
                            lb.start();
                        }
                    }
                } else if (c.equalsIgnoreCase("l")) {
                    la.CreatTableOfUser();
                    System.out.println("Enter your password");
                    String pass = sc.nextLine();
                    System.out.println("Enter your user name");
                    String user = sc.nextLine();
                    la.loginUser(user, pass);
                }
                break;
// ==========================================STUDENT CODE====================================================================
            case "S":
                System.out.println("L : login   \t\tN : new student");
                String a = sc.nextLine();
                if (a.equalsIgnoreCase("n")) {
                    la.CreateTableOfStudent();
                    System.out.println("Enter your your Id");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter your user name");
                    String name = sc.nextLine();
                    System.out.println("Enter your password");
                    String password = sc.nextLine();

                    Student student = new Student(name,password,id);

                    la.InsertInToTable(student);
                    List<Student> mpp = la.getAllStudentsData();
                    for (int i = 0; i < mpp.size(); i++) {
                        if (name.equalsIgnoreCase(mpp.get(i).getStudentName())) {
                            System.out.println("Welcome");
                             StudentLab studentLab  = new StudentLab();
                             studentLab.startStudent();

                        }
                    }
                } else if (a.equalsIgnoreCase("l")) {
                    la.CreatTableOfUser();
                    System.out.println("Enter your user name");
                    String name = sc.nextLine();
                    System.out.println("Enter your password ");
                    String password = sc.nextLine();
                    List<Student> mpp = la.getAllStudentsData();
                    for (int i = 0; i < mpp.size(); i++) {
                        if (name.equalsIgnoreCase(mpp.get(i).getStudentName())) {
                            System.out.println("Welcome");
               la.loginStudent(name,password);
                        }
                    }
                }
                break;
        }
    }
}