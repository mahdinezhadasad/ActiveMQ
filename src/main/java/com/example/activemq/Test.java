package com.example.activemq;

class Book {
    String title;
}

public class Test {
    public static void changeTitle(Book book) {
        book.title = "New Title"; // modify internal data
    }

    public static void main(String[] args) {
        Book b = new Book();
        b.title = "Old Title";

        changeTitle(b);

        System.out.println(b.title); // ➡️ prints "New Title"
    }
}
