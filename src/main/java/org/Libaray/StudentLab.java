package org.Libaray;

import
        java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class StudentLab {

    public static void bookSearch(int bookId) {
        LabDatabase lab = new LabDatabase();
        for (Books book : lab.getAllBooks()) {
            {
                if (bookId == book.getBookId()) {
                    System.out.println(book);
                }
            }
        }
    }

    public static void returnBook(int studentId) {
        LabDatabase lab = new LabDatabase();
        for (String b : lab.getAllIssuedBookInfo(studentId)){
            System.out.println(b);
        }
    }

    public static void searchBook(int id) {
        LocalDate g = LocalDate.now();
        Scanner sc = new Scanner(System.in);
        int i = 0;
        LabDatabase lab = new LabDatabase();
       List<Books> name = lab.getAllBooks();
        for ( i = 0; i <name.size() ; i++) {
            System.out.println("Book Name :" + (i+1) + " " + name.get(i).getBookName());

        }

        for (Student student : lab.getAllStudentsData()) {
            lab.CreateTableOfBookDetail();
            if (id == student.getStudentId()) {
                System.out.println(student);
                System.out.println("Enter book name");
                String bookName = sc.nextLine();
                int expiryDate = 3;

                // Insert the book information into the database
                lab.insertBookForStudent( id,bookName, Date.valueOf(g), expiryDate);

                System.out.println();
                System.out.println("Your issued book is: " + bookName + "\t\tIssue Date: " + g.getDayOfMonth()+ "/" + g.getMonth() + "/" + g.getYear());
                System.out.print("Expiry date of this book: ");
                System.out.println(expiryDate + g.getDayOfMonth() + "/" + g.getMonth() + "/" + g.getYear());
            }
        }
        System.out.println("Do you want to add more book press Y , not press N");
        String choice = sc.next();
        if (choice.equalsIgnoreCase("Y")){
            searchBook(id);
        }
    }

    public static void startStudent () {
            Scanner sc = new Scanner(System.in);

            System.out.println("1 : Search Books" + "\t\t2 : View Issue Books & Date" + "\t\t3 : Return Book" + "\t\t4 : Logout");
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    sc.nextLine();
                    System.out.println("Enter book Id");
                    int bookId = sc.nextInt();
                    bookSearch(bookId);
                    startStudent();
                    break;

                case 2:
                    System.out.println("Enter student Id for search");
                    int num = sc.nextInt();
                    searchBook(num);
                    startStudent();
                    break;

                    case 3:
                    LabDatabase labDatabase3 = new LabDatabase();
                    List<Books> sca3 = labDatabase3.getAllBooks();
                    for (Books schoolDB : sca3) {
                        System.out.println(schoolDB);
                    }
                    System.out.println("Enter Student Id");
                    int studentId = sc.nextInt();
                    System.out.println("Your returned book is : ");
                    returnBook(studentId);
                    startStudent();
                    break;

                case 4:
                    System.out.println("You are logout");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
