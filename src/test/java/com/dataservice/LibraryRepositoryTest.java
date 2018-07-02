package com.dataservice;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Ignore;
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
import com.dataservice.repository.LibraryRepositoryImpl;
import com.dataservice.service.LibraryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class LibraryRepositoryTest {

	@Mock
	BookInfo input;

	@InjectMocks
	LibraryRepositoryImpl repoImpl;

	/*******************
	 * Library Repository addBook() test cases
	 *******************/

	// Passing InputObject as Null
	@Test
	public void testAddBookWithNullInput() {
		assertEquals(ResponseStatus.ADD_BOOK_FAIL, repoImpl.addBook(null));
	}

	// Passing Valid Title
	@Test
	public void testAddBookWithValidData() {
		when(input.getTitle()).thenReturn("Java Book");
		when(input.getAuthors()).thenReturn("['David','John','kiran']");
		//when(input.getCost()).thenReturn(300.00f);
		when(input.getDescription()).thenReturn("The Java Complete Book ");
		when(input.getGenres()).thenReturn("['Romance','Horror']");
		when(input.getNoOfCopies()).thenReturn(10);
		when(input.getPublisher()).thenReturn("Sapient");		
		assertEquals(ResponseStatus.ADD_BOOK_SUCCESS, repoImpl.addBook(input));
	}

	// Passing InputObject of Title with Empty
	@Test
	public void testAddBookWithEmptyTitle() {
		when(input.getTitle()).thenReturn("");
		assertEquals(ResponseStatus.ADD_BOOK_FAIL, repoImpl.addBook(input));
	}

	// Passing InputObject of Title with null
	@Test
	public void testAddBookWithNullTitle() {
		when(input.getTitle()).thenReturn(null);
		assertEquals(ResponseStatus.ADD_BOOK_FAIL, repoImpl.addBook(input));
	}

	/***************** Library Repository removeBook() test cases ***************/

	// Passing BookID or BookTitle as Null
	@Test
	public void testRemoveBookWithNullValue() {
		assertEquals(ResponseStatus.REMOVE_BOOK_FAIL, repoImpl.removeBook(null));
	}

	// Passing BookID or BookTitle as Empty
	@Test
	public void testRemoveBookWithBlankValue() {
		assertEquals(ResponseStatus.REMOVE_BOOK_FAIL, repoImpl.removeBook(""));
	}

	// Passing Valid BookID
	@Test
	public void testRemoveBookWithValidBookID() {
		assertEquals(ResponseStatus.REMOVE_BOOK_SUCCESS, repoImpl.removeBook("5b34843e57bf0d3704994853"));
	}

	// Passing Valid BookTitle
	@Test
	public void testRemoveBookWithValidTitle() {
		assertEquals(ResponseStatus.REMOVE_BOOK_SUCCESS, repoImpl.removeBook("Java Book"));
	}

	/***************** Library Repository searchBook() test cases ***************/

	// Passing BookID or BookTitle as Null
	@Test
	public void testSearchBookWithNullValue() {
		BookInfo bookview = new BookInfo();
		//bookview.setResult(ResponseStatus.SEARCH_BOOK_FAIL);
		//assertEquals(bookview.getResult(), repoImpl.searchBook(null).getResult());
	}

	// Passing BookID or BookTitle as Empty
	@Test
	public void testSearchBookWithEmptyValue() {
		BookInfo bookview = new BookInfo();
		//bookview.setResult(ResponseStatus.SEARCH_BOOK_FAIL);
		//assertEquals(bookview.getResult(), repoImpl.searchBook("").getResult());
	}

	// Passing Valid BookID
	@Test
	public void testSearchBookWithValidID() {
		BookInfo bookview = new BookInfo();
		//bookview.setResult(ResponseStatus.SEARCH_BOOK_SUCCESS);
		//assertEquals(bookview.getResult(), repoImpl.searchBook("5b34843e57bf0d3704994853").getResult());
	}

	// Passing Valid BookTitle
	@Test(expected=IllegalArgumentException.class)
	public void testSearchBookWithValidTitle() {
		BookInfo bookview = new BookInfo();
		//bookview.setResult(ResponseStatus.SEARCH_BOOK_SUCCESS);
		repoImpl.searchBook("Java Book");
	}

	/***************** Library Repository searchBooks() test cases ***************/

	// Testing search Books with failure case
	@Test
	public void testSearchBooksWithFailure() {
		LibraryView view = new LibraryView();
		//view.setResult(ResponseStatus.SEARCH_BOOKS_FAIL);
		//assertEquals(view.getResult(), repoImpl.searchBooks().getResult());
	}

	// Testing search Books with success case
	@Test
	public void testSearchBooksWithSuccess() {
		LibraryView view = new LibraryView();
		//view.setResult(ResponseStatus.SEARCH_BOOKS_SUCCESS);
		//assertEquals(view.getResult(), repoImpl.searchBooks().getResult());
	}
}
