package org.Libaray;

import java.util.List;
import java.util.Scanner;

public class LibraryFunctions {
    public static void addBooks(){
        LabDatabase labDatabase = new LabDatabase();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Book Id");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Book Name");
        String book = sc.nextLine();
        System.out.println("Enter Author Name ");
        String author = sc.nextLine();
        System.out.println("Enter price");
        int price = sc.nextInt();
        System.out.println("Enter quantity");
        int ac = sc.nextInt();
        labDatabase.CreatTable();
        Books books = new Books(id,book,author,price,ac);
        labDatabase.InsertInToTable(books);
        List<Books> scav = labDatabase.getAllBooks();
        for (Books schoolDB : scav){
            System.out.println(schoolDB);
        }
        System.out.println("Do you want to add more book press Y , not press N");
        String choice = sc.next();
        if (choice.equalsIgnoreCase("Y")){
            addBooks();
        }

    }


    public static void searchBook(int id){
        LabDatabase lab = new LabDatabase();
        for (Books book : lab.getAllBooks()) {
            if (id == book.getBookId()) {
                System.out.println(book);
            }
        }

    }

    public static void updateBook(){
        LabDatabase lab = new LabDatabase();
        Scanner sc = new Scanner(System.in);
         Books books = new Books();
        System.out.println("Enter book Id");
        int old = sc.nextInt();
        List<Books> newbook = lab.getAllStudents1(old);
        System.out.println(newbook);
        System.out.println("Enter new quantity");
        int qu = sc.nextInt();
        lab.updateBookQuantity(old,qu);
    }
    public static void  start() {
        Scanner sc = new Scanner(System.in);

        System.out.println("1 : Add Books" + "\t\t2 : Search Books" + "\t\t3 : Update Book" + "\t\t4 : Show books" + "\t\t5 : Logout");
        int input = sc.nextInt();
        switch (input) {
            case 1:
                addBooks();
                start();
                break;
            case 2:
                System.out.println("Enter book Id for search");
                int num = sc.nextInt();
                searchBook(num);
                start();
                break;
            case 3:
                updateBook();
                start();
                break;
            case 4:
                LabDatabase labDatabase1 = new LabDatabase();
                List<Books> sca1 = labDatabase1.getAllBooks();
                for (Books schoolDB : sca1) {
                    System.out.println(schoolDB);
                }
                start();
                break;
            case 5:
                System.out.println("You are Logout");
                break;
            default:
                System.out.println("Invalid Choice");
        }
    }
}
