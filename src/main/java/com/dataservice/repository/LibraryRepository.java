package com.dataservice.repository;


import com.dataservice.bo.BookInfo;
import com.dataservice.bo.LibraryView;

public interface LibraryRepository {
	public Boolean addBook(BookInfo input);

	public Boolean removeBook(String input);

	public BookInfo searchBook(String input);

	public LibraryView searchBooks();
	
	Boolean isAvailable(String input);
}
