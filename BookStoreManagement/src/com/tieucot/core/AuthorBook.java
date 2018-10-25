package com.tieucot.core;

public class AuthorBook {
	private Author author;
	private Book book;
	private double revenueShare;
	
	
	
	public AuthorBook(Author author, Book book, double revenueShare) {
		super();
		this.author = author;
		this.book = book;
		this.revenueShare = revenueShare;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public double getRevenueShare() {
		return revenueShare;
	}
	public void setRevenueShare(double revenueShare) {
		this.revenueShare = revenueShare;
	}
	
}
