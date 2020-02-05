package caseStudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import caseStudy.dto.BookDto;
import caseStudy.dto.UpdatedBookDto;
import caseStudy.exception.AuthorNotExistException;
import caseStudy.exception.BookNotExistException;
import caseStudy.serviceImpl.BookServiceImpl;

@RestController
public class BookController {

	@Autowired
	private BookServiceImpl bookServiceImpl;

	@RequestMapping(value = "/books/authors/{authorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BookDto>> getBooks(@PathVariable("authorId") int authorId,
			@RequestParam("maxPrice") double maxPrice) throws AuthorNotExistException {

		List<BookDto> books = bookServiceImpl.getBooks(authorId, maxPrice);
		return new ResponseEntity<List<BookDto>>(books, HttpStatus.OK);
	}

	@RequestMapping(value = "/books", method = RequestMethod.POST)
	public ResponseEntity<Void> addBook(@RequestBody BookDto book) {

		BookDto createdBook = bookServiceImpl.addBook(book);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("bookId", Integer.toString(createdBook.getId()));
		return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/books", method = RequestMethod.PUT)
	public ResponseEntity<List<UpdatedBookDto>> updateBooks(@RequestBody List<UpdatedBookDto> books) {

		List<UpdatedBookDto> updatedBooks = bookServiceImpl.updateBooks(books);
		return new ResponseEntity<List<UpdatedBookDto>>(updatedBooks, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/books/{bookId}", method = RequestMethod.DELETE)
	public ResponseEntity deleteBook(@PathVariable("bookId") int bookId) throws BookNotExistException {

		bookServiceImpl.deleteBook(bookId);
		return new ResponseEntity(HttpStatus.OK);
	}
}