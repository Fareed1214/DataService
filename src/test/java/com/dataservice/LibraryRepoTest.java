package com.dataservice;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.net.UnknownHostException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dataservice.bo.BookInfo;
import com.dataservice.bo.LibraryView;
import com.dataservice.repository.LibraryRepository;
import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.mongodb.MockMongoClient;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;


/**
 * Date:   6/28/13 / 10:41 AM
 * Author: Johnathan Mark Smith
 * Email:  john@johnathanmarksmith.com
 * <p/>
 * Comments:
 *
 *  This is a demo on how to use Fongo and nosqlunit-mongodb
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class LibraryRepoTest {

    @Rule
    public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("test");

    /**
     *
     *   nosql-unit requirement
     *
     */
	@Autowired private ApplicationContext applicationContext;
	
	@Autowired private LibraryRepository personRepositoryrepository;

	/**
	 * Expected results are in "one-person.json" file
	 */
	@Test
	@ShouldMatchDataSet(location = "/Book1.json")
    public void testInsertPersonWithNameJohnathanAndRandomAge(){
         this.personRepositoryrepository.insertPersonWithNameJohnathan(35);
         this.personRepositoryrepository.insertPersonWithNameJohnathan(67);
    }
	
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
		bookview.setResult(ResponseStatus.SEARCH_BOOK_FAIL);
		assertEquals(bookview.getResult(), repoImpl.searchBook(null).getResult());
	}

	// Passing BookID or BookTitle as Empty
	@Test
	public void testSearchBookWithEmptyValue() {
		BookInfo bookview = new BookInfo();
		bookview.setResult(ResponseStatus.SEARCH_BOOK_FAIL);
		assertEquals(bookview.getResult(), repoImpl.searchBook("").getResult());
	}

	// Passing Valid BookID
	@Test
	public void testSearchBookWithValidID() {
		BookInfo bookview = new BookInfo();
		bookview.setResult(ResponseStatus.SEARCH_BOOK_SUCCESS);
		assertEquals(bookview.getResult(), repoImpl.searchBook("5b34843e57bf0d3704994853").getResult());
	}

	// Passing Valid BookTitle
	@Test(expected=IllegalArgumentException.class)
	public void testSearchBookWithValidTitle() {
		BookInfo bookview = new BookInfo();
		bookview.setResult(ResponseStatus.SEARCH_BOOK_SUCCESS);
		repoImpl.searchBook("Java Book");
	}

	/***************** Library Repository searchBooks() test cases ***************/

	// Testing search Books with failure case
	@Test
	public void testSearchBooksWithFailure() {
		LibraryView view = new LibraryView();
		view.setResult(ResponseStatus.SEARCH_BOOKS_FAIL);
		assertEquals(view.getResult(), repoImpl.searchBooks().getResult());
	}

	// Testing search Books with success case
	@Test
	public void testSearchBooksWithSuccess() {
		LibraryView view = new LibraryView();
		view.setResult(ResponseStatus.SEARCH_BOOKS_SUCCESS);
		assertEquals(view.getResult(), repoImpl.searchBooks().getResult());
	}
	
	@Configuration
	@EnableMongoRepositories
	@ComponentScan(basePackageClasses = {LibraryRepository.class})
	@PropertySource("classpath:application.properties")
	static class PersonRepositoryTestConfiguration extends AbstractMongoConfiguration {

	    @Override
	    protected String getDatabaseName() {
	        return "test";
	    }
		
		public Mongo mongo() {
	    	// uses fongo for in-memory tests
			return new com.github.fakemongo.Fongo("test").getMongo();
		}
		
	    @Override
	    protected String getMappingBasePackage() {
	        return "com.johnathanmarksmith.mongodb.example.domain";
	    }

		@Override
		public MongoClient mongoClient()
		{
			MongoClient client = null;
			try {
				client = new MockMongoClient();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return client;
		}

	}
}
