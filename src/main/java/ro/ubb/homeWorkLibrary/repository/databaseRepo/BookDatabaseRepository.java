package ro.ubb.homeWorkLibrary.repository.databaseRepo;

import ro.ubb.homeWorkLibrary.domain.Book;
import ro.ubb.homeWorkLibrary.exceptions.DatabaseException;
import ro.ubb.homeWorkLibrary.repository.inMemoryRepo.InMemoryRepository;
import ro.ubb.homeWorkLibrary.validators.Validator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDatabaseRepository extends InMemoryRepository<Long, Book> {

    private static final String URL = "jdbc:postgresql://localhost:5432/library";
    private static final String USERNAME = System.getProperty("user");
    private static final String PASSWORD = System.getProperty("password");

    public BookDatabaseRepository(Validator<Book> validator) {
        super(validator);
    }


    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement("select * from books");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String publishHouse = resultSet.getString("publishHouse");
                int relesedYear = resultSet.getInt("relesedYear");
                double price = resultSet.getDouble("price");

                Book book = new Book(id, title, author, publishHouse, relesedYear, price);

                books.add(book);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Finding all books from database failed!"+e);
        }
        return books;
    }

    @Override
    public Optional<Book> save(Book entity) {
        Optional<Book> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToDatabase(entity);
        return Optional.empty();
    }

    private void saveToDatabase(Book book) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "insert into books(id, title, author, publishHouse, relesedYear, price) " +
                             "values (?,?,?,?,?,?)");
        ) {
            statement.setLong(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getPublishHouse());
            statement.setInt(5, book.getRelesedYear());
            statement.setDouble(6, book.getPrice());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Save book to database failed!"+e);
        }
    }

    @Override
    public Optional<Book> update(Book book) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "update books set title=?, author=?, " +
                             "publishHouse=?, relesedYear=?, price=?  where id= ?")
        ) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublishHouse());
            statement.setInt(4, book.getRelesedYear());
            statement.setDouble(5, book.getPrice());
            statement.setLong(6, book.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Update book in database failed! " + e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> delete(Long id) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("delete from books where id = ?")
        ) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Delete book from database failed! " + e);
        }
        return Optional.empty();
    }


    public Optional<Book> findOne(Long id) {
        Book book = null;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement("select * from books where id = ?");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String publishHouse = resultSet.getString("publishHouse");
                int relesedYear = resultSet.getInt("relesedYear");
                double price = resultSet.getDouble("price");

                book = new Book(id, title, author, publishHouse, relesedYear, price);

            }

        } catch (SQLException e) {
            throw new DatabaseException("Find one book in database failed!"+e);
        }

        return Optional.of(book);
    }

}
