package caseStudy.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import caseStudy.daoImpl.BookDAOImpl;
import caseStudy.dto.BookDto;
import caseStudy.dto.UpdatedBookDto;
import caseStudy.exception.AuthorNotExistException;
import caseStudy.exception.BookNotExistException;
import caseStudy.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAOImpl bookDAOImpl;

	@Override
	public List<BookDto> getBooks(int authorId, double maxPrice) throws AuthorNotExistException {

		if (!bookDAOImpl.authorExists(authorId)) {
			throw new AuthorNotExistException("ATR-001", "Author with given id does not exist in the database");
		}

		return bookDAOImpl.getBooks(authorId, maxPrice);
	}

	@Override
	public BookDto addBook(BookDto book) {

		int id = bookDAOImpl.getMaxBookId();
		book.setId(id + 1);

		int authorId = bookDAOImpl.authorExists(book.getAuthor());

		if (authorId == -1) {
			authorId = addAuthor(book.getAuthor());
		}

		return bookDAOImpl.addBook(book, authorId);
	}

	@Override
	public int addAuthor(String authorName) {

		int id = bookDAOImpl.getMaxAuthorId();
		bookDAOImpl.addAuthor(id + 1, authorName);
		return id + 1;
	}

	@Override
	public List<UpdatedBookDto> updateBooks(List<UpdatedBookDto> books) {

		List<UpdatedBookDto> updatedBooks = new ArrayList<>();
		for (UpdatedBookDto updatedBookDto : books) {
			if (bookDAOImpl.bookExists(updatedBookDto.getId())) {

				bookDAOImpl.updateBook(updatedBookDto);
				updatedBooks.add(updatedBookDto);
			}
		}
		return updatedBooks;
	}

	@Override
	public void deleteBook(int bookId) throws BookNotExistException {

		if (!bookDAOImpl.bookExists(bookId)) {
			throw new BookNotExistException("BOOK-1", "Book with given id does not exist in the database");
		}
		bookDAOImpl.deleteBook(bookId);
	}

}
