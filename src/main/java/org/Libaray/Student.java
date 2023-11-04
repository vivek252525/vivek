package org.Libaray;

import java.util.Calendar;

public class Student {

    private String studentName;
    private  int studentId;
    private String password;
    private String issueBook;

    public Student() {

    }

    public Student( String issueBook) {

        this.issueBook = issueBook;
    }

    public Student(String studentName, String password) {
        this.studentName = studentName;
        this.password = password;
    }

    public Student(String studentName, String password,int studentId) {
        this.studentName = studentName;
        this.password = password;
        this.studentId=studentId;
    }

    public Student(String studentName, int studentId, String password, String issueBook) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.password = password;
        this.issueBook = issueBook;
    }

    public Student(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIssueBook() {
        return issueBook;
    }

    public void setIssueBook(String issueBook) {
        this.issueBook = issueBook;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentName='" + studentName + '\'' +
                ", studentId=" + studentId +
                '}';
    }
}
