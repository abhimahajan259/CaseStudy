package caseStudy.service;

import java.util.List;

import caseStudy.dto.BookDto;
import caseStudy.dto.UpdatedBookDto;
import caseStudy.exception.AuthorNotExistException;
import caseStudy.exception.BookNotExistException;

public interface BookService {

	public List<BookDto> getBooks(int authorId, double maxPrice) throws AuthorNotExistException;

	public BookDto addBook(BookDto book);

	public int addAuthor(String authorName);

	public List<UpdatedBookDto> updateBooks(List<UpdatedBookDto> books);

	public void deleteBook(int bookId) throws BookNotExistException;
}
