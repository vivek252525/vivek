package org.Libaray;

import
        java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class StudentLab {

    public static void bookSearch(String bookName, String authorName) {
        LabDatabase lab = new LabDatabase();
        for (Books book : lab.getAllBooks()) {
            {
                if (bookName.equalsIgnoreCase(book.getBookName()) && authorName.equalsIgnoreCase(book.getAuthorName())) {
                    System.out.println(book);
                }
            }
        }
    }

    public static void returnBook(int studentId , String bookName, String authorName) {
        Student s = new Student();
        LabDatabase lab = new LabDatabase();
        for (Books book : lab.getAllBooks()) {
            {
                if (studentId == s.getStudentId() && bookName.equalsIgnoreCase(book.getBookName()) && authorName.equalsIgnoreCase(book.getAuthorName())) {
                    System.out.println(book);
                }
            }
        }
    }
    public static void orderPlace(String bookName){
        LabDatabase lab = new LabDatabase();
        for (Books books : lab.getAllBooks()){
            if (bookName.equalsIgnoreCase(books.getBookName())){
                System.out.println(books);
            }
        }
    }



    public static void searchBook(int id) {
        Date g = new Date(04112023);
        Scanner sc = new Scanner(System.in);
        LabDatabase lab = new LabDatabase();

        for (Student student : lab.getAllStudentsData()) {
            if (id == student.getStudentId()) {
                System.out.println(student);
                System.out.println("Enter book name");
                String bookName = sc.nextLine();
                System.out.println("Enter expire days");
                int expiryDate = sc.nextInt();

                // Insert the book information into the database
                lab.insertBookForStudent(id, bookName, g, expiryDate);

                System.out.println();
                System.out.println("Your issued book is: " + bookName + "\t\tIssue Date: " + g.getDate() + "/" + g.getMonth() + "/" + (g.getYear() - 100));
                System.out.print("Expiry date of this book: ");
                System.out.println(expiryDate + g.getDate() + "/" + g.getMonth() + "/" + (g.getYear() - 100));
            }
        }
    }

    public static void startStudent () {
            Scanner sc = new Scanner(System.in);

            System.out.println("1 : Search Books" + "\t\t2 : View Book Issue Date" + "\t\t3 : Return Book" + "\t\t4 : Place Order" + "\t\t5 : Logout");
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    LabDatabase labDatabase1 = new LabDatabase();
                    List<Books> sca1 = labDatabase1.getAllBooks();
                    for (Books schoolDB : sca1) {
                        System.out.println(schoolDB);
                    }
                    sc.nextLine();
                    System.out.println("Enter book name");
                    String bookN = sc.nextLine();
                    System.out.println("Enter author name");
                    String authorN = sc.nextLine();
                    bookSearch(bookN, authorN);
                    startStudent();
                    break;

                case 2:
                    System.out.println("Enter student Id for search");
                    int num = sc.nextInt();
                    searchBook(num);
                    break;


                case 3:
                    LabDatabase labDatabase3 = new LabDatabase();
                    List<Books> sca3 = labDatabase3.getAllBooks();
                    for (Books schoolDB : sca3) {
                        System.out.println(schoolDB);
                    }
                    System.out.println("Enter Student Id");
                    int studentId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter book name");
                    String bookName1 = sc.nextLine();
                    System.out.println("Enter author name");
                    String authorName1 = sc.nextLine();
                    System.out.print("Your returned book is: ");
                    returnBook(studentId, bookName1, authorName1);
                    break;

                case 4:
                    LabDatabase labDatabase4 = new LabDatabase();
                    List<Books> sca4 = labDatabase4.getAllBooks();
                    for (Books schoolDB : sca4) {
                        System.out.println(schoolDB);
                    }
                    sc.nextLine();
                    System.out.println("Enter book name");
                    String bookName = sc.nextLine();
                    System.out.print("Your book ordered: ");
                    orderPlace(bookName);
                    break;
            }
        }
    }
