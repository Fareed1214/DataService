package com.dataservice;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.dataservice.bo.BookInfo;
import com.dataservice.bo.LibraryView;
import com.dataservice.constants.ResponseStatus;
import com.dataservice.repository.LibraryRepository;
import com.dataservice.service.LibraryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class LibraryServiceTest {

	@Mock
	BookInfo input;

	@Mock
	LibraryRepository repo;

	@InjectMocks
	LibraryServiceImpl serviceImpl;

	/******************* Library Service addBook() test cases *******************/

	// Passing InputObject as Null
	@Test
	public void testAddBookWithNullInput() {
		assertEquals(ResponseStatus.ADD_BOOK_FAIL, serviceImpl.addBook(null));
	}

	// Passing Valid InputObject data
	@Test
	public void testAddBookWithValidData() {
		when(input.getTitle()).thenReturn("Java Book");
		//when(repo.addBook(input)).thenReturn(ResponseStatus.ADD_BOOK_SUCCESS);
		assertEquals(ResponseStatus.ADD_BOOK_SUCCESS, serviceImpl.addBook(input));
	}

	// Passing InputObject of Title with Empty
	@Test
	public void testAddBookWithEmptyTitle() {
		when(input.getTitle()).thenReturn("");
		assertEquals(ResponseStatus.ADD_BOOK_FAIL, serviceImpl.addBook(input));
	}

	// Passing InputObject of Title with null
	@Test
	public void testAddBookWithNullTitle() {
		when(input.getTitle()).thenReturn(null);
		assertEquals(ResponseStatus.ADD_BOOK_FAIL, serviceImpl.addBook(input));
	}

	/***************** Library Service removeBook() test cases ***************/

	// Passing BookID or BookTitle as Null
	@Test
	public void testRemoveBookWithNullValue() {
		assertEquals(ResponseStatus.REMOVE_BOOK_FAIL, serviceImpl.removeBook(null));
	}

	// Passing BookID or BookTitle as Empty
	@Test
	public void testRemoveBookWithBlankValue() {
		assertEquals(ResponseStatus.REMOVE_BOOK_FAIL, serviceImpl.removeBook(""));
	}

	// Passing Valid BookID
	@Test
	public void testRemoveBookWithValidBookID() {
		//when(repo.removeBook("5b34843e57bf0d3704994853")).thenReturn(ResponseStatus.REMOVE_BOOK_SUCCESS);
		assertEquals(ResponseStatus.REMOVE_BOOK_SUCCESS, serviceImpl.removeBook("5b34843e57bf0d3704994853"));
	}

	// Passing Valid BookTitle
	@Test
	public void testRemoveBookWithValidTitle() {
		//when(repo.removeBook("Java Book")).thenReturn(ResponseStatus.REMOVE_BOOK_SUCCESS);
		assertEquals(ResponseStatus.REMOVE_BOOK_SUCCESS, serviceImpl.removeBook("Java Book"));
	}

	/***************** Library Service searchBook() test cases ***************/

	// Passing BookID or BookTitle as Null
	@Test
	public void testSearchBookWithNullValue() {
		BookInfo bookview = new BookInfo();
		//bookview.setResult(ResponseStatus.SEARCH_BOOK_FAIL);
		//assertEquals(bookview.getResult(), serviceImpl.searchBook(null).getResult());
	}

	// Passing BookID or BookTitle as Empty
	@Test
	public void testSearchBookWithEmptyValue() {
		BookInfo bookview = new BookInfo();
		//bookview.setResult(ResponseStatus.SEARCH_BOOK_FAIL);
		//assertEquals(bookview.getResult(), serviceImpl.searchBook("").getResult());
	}

	// Passing Valid BookID
	@Test
	public void testSearchBookWithValidID() {
		BookInfo bookview = new BookInfo();
		//bookview.setResult(ResponseStatus.SEARCH_BOOK_SUCCESS);
		//when(repo.searchBook("5b34843e57bf0d3704994853")).thenReturn(bookview);
		//assertEquals(bookview.getResult(), serviceImpl.searchBook("5b34843e57bf0d3704994853").getResult());
	}

	// Passing Valid BookTitle
	@Test
	public void testSearchBookWithValidTitle() {
		BookInfo bookview = new BookInfo();
		//bookview.setResult(ResponseStatus.SEARCH_BOOK_SUCCESS);
		//when(repo.searchBook("Java Book")).thenReturn(bookview);
		//assertEquals(bookview.getResult(), serviceImpl.searchBook("Java Book").getResult());
	}

	/***************** Library Service searchBooks() test cases ***************/

	// Testing search Books with failure case
	@Test
	public void testSearchBooksWithFailure() {
		LibraryView view = new LibraryView();
		//view.setResult(ResponseStatus.SEARCH_BOOKS_FAIL);
		when(repo.searchBooks()).thenReturn(view);
		//assertEquals(view.getResult(), serviceImpl.searchBooks().getResult());
	}

	// Testing search Books with success case
	@Test
	public void testSearchBooksWithSuccess() {
		LibraryView view = new LibraryView();
		//view.setResult(ResponseStatus.SEARCH_BOOKS_SUCCESS);
		when(repo.searchBooks()).thenReturn(view);
		//assertEquals(view.getResult(), serviceImpl.searchBooks().getResult());
	}
}
