package caseStudy.dao;

import java.util.List;

import caseStudy.dto.BookDto;
import caseStudy.dto.UpdatedBookDto;

public interface BookDAO {

	public List<BookDto> getBooks(int authorId, double maxPrice);

	public int getMaxBookId();

	public BookDto addBook(BookDto book, int authorId);

	public int authorExists(String authorName);

	public int getMaxAuthorId();

	public void addAuthor(int id, String authorName);

	public void deleteBook(int bookId);

	public void updateBook(UpdatedBookDto updatedBookDto);

	public boolean authorExists(int authorId);

	public boolean bookExists(int bookId);
}
