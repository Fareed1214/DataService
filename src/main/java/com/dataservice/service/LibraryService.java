package com.dataservice.service;

import org.springframework.stereotype.Service;

import com.dataservice.bo.BookInfo;
import com.dataservice.bo.LibraryView;

@Service
public interface LibraryService
{
	Boolean addBook(BookInfo input);

	Boolean removeBook(String input);

	BookInfo searchBook(String input);

	LibraryView searchBooks();
	
	Boolean isAvailable(String input);

}
