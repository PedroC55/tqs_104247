package com.example.project;

import java.util.Date;
 
public class Book {
	private String title;
	private String author;
	private Date published;

    public Book(String title, String author, Date date) {
        this.title = title;
        this.author = author;
        this.published = date;
    }

    public String getTitle() {return this.title; }
    public String getAuthor() {return this.author; }
    public Date getPublished() {return this.published; }

}