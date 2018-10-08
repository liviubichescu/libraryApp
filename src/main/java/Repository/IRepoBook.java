package Repository;

import Domain.Book;
import java.util.List;

/**
 *  Interface on CRUD operations for books
 *
 * @author Liviu
 */
public interface IRepoBook {
    /**
     * Add's a book to the store
     * @param book the book that you want to store
     * @returns - null if the book was added otherwise return the book;
     * @throws IllegalArgumentException if the given entity is null.
     */
    void addBook(Book book);

    /**
     * Deletes a book from the store
     * @param id must not be null.
     * @return - null if the book was deleted otherwise return the book;
     * @throws IllegalArgumentException if the given id is null.
     */
    void delBook(int id);

    /**
     * Find the book with the given id
     * @param id must be not null.
     * @return the book with the given id.
     * @throws IllegalArgumentException if the given id is null.
     */
    Book findBook(int id);

    /**
     * Finds all the books from the store;
     * @return a list with all the books;
     */
    List<Book> findAll();

    /**
     * Update's a book from the store
     * @param book the book that you want to update
     * @returns - the book if it was updated otherwise returns null.
     * @throws IllegalArgumentException if the given entity is null.
     */
    Book updateBook(Book book);
}
