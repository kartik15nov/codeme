package com.codeme.pro.interview.ge1;

public class Book {

  private int id;
  private String title;
  private String author;
  private String category;
  private int price;

  public Book(int id, String title, String author, String category, int price) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.category = category;
    this.price = price;
  }

  public int getId() { return id; }
  public String getTitle() { return title; }
  public String getAuthor() { return author; }
  public String getCategory() { return category; }
  public int getPrice() { return price; }

}
