package caseStudy.service;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import caseStudy.daoImpl.BookDAOImpl;
import caseStudy.dto.BookDto;
import caseStudy.dto.UpdatedBookDto;
import caseStudy.exception.AuthorNotExistException;
import caseStudy.exception.BookNotExistException;
import caseStudy.serviceImpl.BookServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Mock
	private BookDAOImpl bookDAOImpl;

	@InjectMocks
	private BookServiceImpl bookServiceImpl;

	@Test
	public void getBooksWithNonExistingAuthor() throws AuthorNotExistException {

		thrown.expect(AuthorNotExistException.class);
		thrown.expectMessage("Description: Author with given id does not exist in the database");
		Mockito.when(bookDAOImpl.authorExists(Mockito.anyInt())).thenReturn(false);
		bookServiceImpl.getBooks(1, 20.0);
	}

	@Test
	public void getBooksWithExistingAuthor() throws AuthorNotExistException, ParseException {
		BookDto book = new BookDto();
		book.setId(1);
		book.setName("As You Like It");
		book.setCategory("Romance");
		book.setDescription("A romantic play");
		book.setAuthor("William Shakespeare");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");
		Date date = simpleDateFormat.parse("1995-10-20");
		book.setPublicationDate(date);
		book.setPrice(230.0);

		List<BookDto> books = new ArrayList<>();
		books.add(book);

		Mockito.when(bookDAOImpl.authorExists(Mockito.anyInt())).thenReturn(true);
		Mockito.when(bookDAOImpl.getBooks(Mockito.anyInt(), Mockito.anyDouble())).thenReturn(books);

		assertEquals(books, bookServiceImpl.getBooks(1, 250.0));
	}

	@Test
	public void updateBookWithNonExistingId() {

		UpdatedBookDto updatedBookDto = new UpdatedBookDto();
		updatedBookDto.setId(999);
		updatedBookDto.setPrice(999.9);
		List<UpdatedBookDto> updatedBookDtos = new ArrayList<>();
		updatedBookDtos.add(updatedBookDto);

		Mockito.when(bookDAOImpl.bookExists(Mockito.anyInt())).thenReturn(false);
		assertEquals(new ArrayList<>(), bookServiceImpl.updateBooks(updatedBookDtos));
	}

	@Test
	public void updateBookWithExistingId() {

		UpdatedBookDto updatedBookDto = new UpdatedBookDto();
		updatedBookDto.setId(5);
		updatedBookDto.setPrice(999.9);
		List<UpdatedBookDto> updatedBookDtos = new ArrayList<>();
		updatedBookDtos.add(updatedBookDto);

		Mockito.when(bookDAOImpl.bookExists(Mockito.anyInt())).thenReturn(true);

		assertEquals(updatedBookDtos, bookServiceImpl.updateBooks(updatedBookDtos));
	}

	@Test
	public void deleteBookWithNonExistingId() throws BookNotExistException {

		thrown.expect(BookNotExistException.class);
		thrown.expectMessage("Description: Book with given id does not exist in the database");

		Mockito.when(bookDAOImpl.bookExists(Mockito.anyInt())).thenReturn(false);

		bookServiceImpl.deleteBook(999);
	}

}
