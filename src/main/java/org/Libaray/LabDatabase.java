package org.Libaray;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LabDatabase {
    private Connection connectToDatabase(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab","root","mukulA!");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public boolean CreatTable(){
        Connection connection = connectToDatabase();
        boolean b = false;
        try {
            Statement st = connection.createStatement();
            String createQuery = "create table if not exists books( bookId int primary key , bookName varchar(30), authorName varchar(30), price int, quantity int)";
            b = st.execute(createQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  b;
    }
    public void InsertInToTable(Books books) {
        Connection connection = connectToDatabase();
        try {
            Statement st = connection.createStatement();
            String insertQuery = "INSERT INTO books (bookId, bookName, authorName, price, quantity) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, books.getBookId());  // bookId
            preparedStatement.setString(2, books.getBookName());  // bookName
            preparedStatement.setString(3, books.getAuthorName());  // authorName
            preparedStatement.setInt(4, books.getPrice());  // price
            preparedStatement.setInt(5, books.getQuantity());  // quantity (you should set a valid quantity)
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Books> getAllBooks() {
        Connection connection = connectToDatabase();
        List<Books> schoolDBList = new ArrayList<>();
        Books sc = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from books ");
            while (resultSet.next()) {
                sc = new Books();
                sc.setBookId(resultSet.getInt(1));
                sc.setBookName(resultSet.getString(2));
                sc.setAuthorName(resultSet.getString(3));
                sc.setPrice(resultSet.getInt(4));
                sc.setQuantity(resultSet.getInt(5));
                schoolDBList.add(sc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return schoolDBList;
    }


//==========================================================UPDATED BOOKS===================================================================
 public List<Books> getAllStudents1(int id) {
        Connection connection = connectToDatabase();
        List<Books> schoolDBList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE bookId = ?");
            statement.setInt(1, id); // Set the parameter for the book ID
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Books sc = new Books();
                sc.setBookId(resultSet.getInt("bookId"));
                sc.setBookName(resultSet.getString("bookName"));
                sc.setAuthorName(resultSet.getString("authorName"));
                sc.setPrice(resultSet.getInt("price"));
                sc.setQuantity(resultSet.getInt("quantity"));

                schoolDBList.add(sc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return schoolDBList;
    }
    public void updateBookQuantity(int bookId, int newQuantity) {
        Books books = new Books();
    Connection connection = connectToDatabase();
        System.out.println("\t\t1 : Add Quantity" +  "\t\t2: Less Quantity");
        Scanner sc = new Scanner(System.in);
        int  input = sc.nextInt();
        List<Books> newbook = getAllStudents1(bookId);
        switch (input) {
            case 1:
                int n = 0;
                int num = 0;
                for (int i = 0; i < newbook.size(); i++) {
                    n = newbook.get(i).getQuantity();
                }
                num = n + newQuantity;
                System.out.println("updated quantity = " + num);
                try {
                    String updateQuery = "UPDATE books SET quantity = ? WHERE bookId = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                    preparedStatement.setInt(1, num);  // New quantity value
                    preparedStatement.setInt(2, bookId);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Book quantity updated successfully.");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
               break;
            case 2:
                int n1 = 0;
                int num1 = 0;
                for (int i = 0; i < newbook.size(); i++) {
                    n1 = newbook.get(i).getQuantity();
                }
                num1 = n1 - newQuantity;
                System.out.println("updated quantity = " + num1);
                try {
                    String updateQuery = "UPDATE books SET quantity = ? WHERE bookId = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                    preparedStatement.setInt(1, num1);  // New quantity value
                    preparedStatement.setInt(2, bookId);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Book quantity updated successfully.");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }
}

//===============================================USER OR ADMIN=========================================================================
public boolean CreatTableOfUser(){
    Connection connection = connectToDatabase();
    boolean b = false;
    try {
        Statement st = connection.createStatement();
        String createQuery = "create table if not exists admin( password varchar(30) primary key , userName varchar(30))";
        b = st.execute(createQuery);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return  b;
}
    public void InsertInToUserTable(UserAccount userAccount) {
        Connection connection = connectToDatabase();
        try {
            Statement st = connection.createStatement();
            String insertQuery = "INSERT INTO admin (password, userName) VALUES ( ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, userAccount.getPassword());  // bookId
            preparedStatement.setString(2, userAccount.getUserName());  // bookName
            preparedStatement.executeUpdate();
            System.out.println("Values inserted into the 'admin' table.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<UserAccount> getAllAdminDetails() {
        Connection connection = connectToDatabase();
        List<UserAccount> schoolDBList = new ArrayList<>();
        UserAccount sc = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from admin ");
            while (resultSet.next()) {
                sc = new UserAccount();
                sc.setPassword(resultSet.getString(1));
                sc.setUserName(resultSet.getString(2));
                schoolDBList.add(sc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return schoolDBList;
    }
    public boolean loginUser(String username, String password) {
        Connection connection = connectToDatabase();
        try {
            String query = "SELECT * FROM admin WHERE userName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // User found, check the password
                String storedPassword = resultSet.getString("password");
                if (password.equals(storedPassword)) {
                    // Passwords match, login successful
                    List<UserAccount> mpp = getAllAdminDetails();
                    for (int i = 0; i < mpp.size(); i++) {
                        if (password.equalsIgnoreCase(mpp.get(i).getPassword())) {
                            System.out.println("Welcome");
                            LibraryFunctions lb = new LibraryFunctions();
                            lb.start();
                        }}
                    return true;
                } else {
                    // Password does not match
                    System.out.println("Wrong password. Please try again.");
                }
            } else {
                // User not found
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    //================================================For Students ===================================================================
public boolean CreateTableOfStudent(){
    Connection connection = connectToDatabase();
    boolean b = false;
    try {
        Statement st = connection.createStatement();
        String createQuery = "create table if not exists student( studentId int primary key ,studentName varchar(80),password varchar(20),issueBook varchar(50))";
        b = st.execute(createQuery);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return  b;
}
    public void InsertInToTable(Student student) {
        Connection connection = connectToDatabase();
        try {
            Statement st = connection.createStatement();
            String insertQuery = "INSERT INTO student (studentId, studentName,password,issueBook) VALUES (?,?,?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, student.getStudentId());  // studentId
            preparedStatement.setString(2, student.getStudentName());// studentName
            preparedStatement.setString(3, student.getPassword());// studentName
            preparedStatement.setString(4, student.getIssueBook());// studentName
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Student> getAllStudentsData() {
        Connection connection = connectToDatabase();
        List<Student> schoolDBList = new ArrayList<>();
        Student sc = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from student ");
            while (resultSet.next()) {
                sc = new Student();
                sc.setStudentId(resultSet.getInt(1));
                sc.setStudentName(resultSet.getString(2));
                sc.setPassword(resultSet.getString(3));
                sc.setIssueBook(resultSet.getString(4));
                schoolDBList.add(sc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return schoolDBList;
    }


    public boolean loginStudent(String userName, String password) {
        Connection connection = connectToDatabase();
        try {
            String query = "SELECT * FROM student WHERE studentName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // User found, check the password
                String storedPassword = resultSet.getString("password");
                if (password.equals(storedPassword)) {
                    // Passwords match, login successful
                    List<Student> mpp = getAllStudentsData();
                    for (int i = 0; i < mpp.size(); i++) {
                        if (password.equalsIgnoreCase(mpp.get(i).getPassword())) {
//                            System.out.println("Welcome");
                            StudentLab lb = new StudentLab();
                            lb.startStudent();
                        }}
                    return true;
                } else {
                    // Password does not match
                    System.out.println("Wrong password. Please try again.");
                }
            } else {
                // User not found
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    public void insertBookForStudent(int studentId, String bookName, Date issueDate, int expiryDays) {
        Connection connection = connectToDatabase();
        // Define the SQL INSERT query.
        String insertQuery = "INSERT INTO student (student_id, book_name, issue_date, expiry_days) VALUES (?, ?, ?, ?)";

        try {
            // Create a PreparedStatement with the SQL query.
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setString(2, bookName);
            preparedStatement.setDate(3, new java.sql.Date(issueDate.getTime())); // Convert Java Date to SQL Date
            preparedStatement.setInt(4, expiryDays);

            // Execute the INSERT query.
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book information inserted successfully.");
            } else {
                System.out.println("Failed to insert book information.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: Failed to insert book information.");
        }
    }

}
