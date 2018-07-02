package com.dataservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataservice.bo.BookInfo;
import com.dataservice.bo.LibraryView;
import com.dataservice.repository.LibraryRepository;

/**
 * service implementation for Library Service
 * @author farshaik4
 *
 */
@Service
public class LibraryServiceImpl implements LibraryService
{
	
	@Autowired
	LibraryRepository repository;

	@Override
	public Boolean addBook(BookInfo input)
	{
		Boolean result = false;
		try {
			if (input != null)
				result = repository.addBook(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Boolean removeBook(String input)
	{
		Boolean result = false;
		try {
			if (input != null && !input.isEmpty())
				result = repository.removeBook(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public BookInfo searchBook(String input)
	{		
		BookInfo bookView = null;
		try {
			if (input != null && !input.isEmpty())
				bookView = repository.searchBook(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookView;
	}

	@Override
	public LibraryView searchBooks()
	{
		LibraryView library = null;
		try {
			library = repository.searchBooks();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return library;
	}

	@Override
	public Boolean isAvailable(String input) {
		Boolean result = false;
		try {
			if (input != null && !input.isEmpty())
				result = repository.isAvailable(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
