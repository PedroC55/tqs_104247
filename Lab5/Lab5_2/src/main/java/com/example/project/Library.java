package com.example.project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
 
public class Library {
    private final List<Book> store = new ArrayList<>();

    public void addBook(final Book book) {
        store.add(book);
    }

    public List<Book> findBooks(final Date from, final Date to) {
		
        return store.stream().filter(book -> {
            return from.before(book.getPublished()) && to.after(book.getPublished());
        }).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
    }
}