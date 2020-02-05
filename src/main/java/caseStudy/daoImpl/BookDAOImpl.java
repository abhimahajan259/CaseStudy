package caseStudy.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import caseStudy.dao.BookDAO;
import caseStudy.dto.BookDto;
import caseStudy.dto.UpdatedBookDto;

@Repository
public class BookDAOImpl implements BookDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<BookDto> getBooks(int authorId, double maxPrice) {

		String sql = "SELECT book.id, book.name, description, category, publicationDate, price, author.name from book INNER JOIN author On book.authorId = author.id where book.authorId = ? AND book.price <= ?;";
		return jdbcTemplate.query(sql, new Object[] { authorId, maxPrice }, new RowMapper<BookDto>() {

			@Override
			public BookDto mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				BookDto bookDto = new BookDto();
				bookDto.setId(resultSet.getInt(1));
				bookDto.setName(resultSet.getString(2));
				bookDto.setDescription(resultSet.getString(3));
				bookDto.setCategory(resultSet.getString(4));
				bookDto.setAuthor(resultSet.getString(7));
				bookDto.setPublicationDate(resultSet.getDate(5));
				bookDto.setPrice(resultSet.getDouble(6));

				return bookDto;
			}
		});
	}

	@Override
	public int getMaxBookId() {

		try {
			String sql = "SELECT MAX(id) from book";
			return jdbcTemplate.queryForObject(sql, Integer.class);
		} catch (EmptyResultDataAccessException ex) {
			return 0;
		}
	}

	@Override
	public BookDto addBook(BookDto book, int authorId) {

		String sql = "INSERT INTO book VALUES (?, ?, ?, ?, ?, ?, ?);";
		jdbcTemplate.update(sql, new Object[] { book.getId(), book.getName(), book.getDescription(), book.getCategory(),
				authorId, book.getPublicationDate(), book.getPrice() });
		return book;
	}

	@Override
	public int authorExists(String authorName) {
		try {
			String sql = "SELECT id FROM author WHERE name = ?;";
			int id = jdbcTemplate.queryForObject(sql, new Object[] { authorName }, Integer.class);
			return id;
		} catch (EmptyResultDataAccessException e) {
			return -1;
		}
	}

	@Override
	public int getMaxAuthorId() {

		try {
			String sql = "SELECT MAX(id) FROM author;";
			return jdbcTemplate.queryForObject(sql, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}

	}

	@Override
	public void addAuthor(int id, String authorName) {

		String sql = "INSERT INTO author VALUES (?, ?);";
		jdbcTemplate.update(sql, new Object[] { id, authorName });
	}

	@Override
	public void deleteBook(int bookId) {

		String sql = "DELETE FROM book WHERE id = ?;";
		jdbcTemplate.update(sql, new Object[] { bookId });
	}

	@Override
	public void updateBook(UpdatedBookDto updatedBookDto) {

		String sql = "UPDATE book SET price = ? WHERE id = ?;";
		jdbcTemplate.update(sql, new Object[] { updatedBookDto.getPrice(), updatedBookDto.getId() });
	}

	public boolean authorExists(int authorId) {
		try {
			String sql = "SELECT id FROM author WHERE id = ?;";
			jdbcTemplate.queryForObject(sql, new Object[] { authorId }, Integer.class);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}

	}

	@Override
	public boolean bookExists(int bookId) {
		try {
			String sql = "SELECT id FROM book WHERE id = ?;";
			jdbcTemplate.queryForObject(sql, new Object[] { bookId }, Integer.class);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
}
