package org.Libaray;
public class Books {
     private int bookId;
     private String bookName;
     private String authorName;
     private int price;
     private int quantity;

     public Books() {
     }

     public Books(int bookId, String bookName, String authorName, int price, int quantity) {
          this.bookId = bookId;
          this.bookName = bookName;
          this.authorName = authorName;
          this.price = price;
          this.quantity = quantity;
     }

     public int getBookId() {
          return bookId;
     }

     public void setBookId(int bookId) {
          this.bookId = bookId;
     }

     public String getBookName() {
          return bookName;
     }

     public void setBookName(String bookName) {
          this.bookName = bookName;
     }

     public String getAuthorName() {
          return authorName;
     }

     public void setAuthorName(String authorName) {
          this.authorName = authorName;
     }

     public int getPrice() {
          return price;
     }

     public void setPrice(int price) {
          this.price = price;
     }

     public int getQuantity() {
          return quantity;
     }

     public void setQuantity(int quantity) {
          this.quantity = quantity;
     }

     @Override
     public String toString() {
          return  "bookId=" + bookId +
                  ", bookName='" + bookName + '\'' +
                  ", authorName='" + authorName + '\'' +
                  ", price=" + price +
                  ", quantity=" + quantity;
     }
}
