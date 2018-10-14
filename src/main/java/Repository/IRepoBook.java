package Repository;

import Domain.Book;
import java.util.List;

/**
 *  Interface on CRUD operations for books
 *
 * @author Liviu
 */
public interface IRepoBook {
    Book save(Book entity);

    Book delete(Long id);

    Book findBook(Long id);

    Iterable<Book> findAll();

    Book update(Book entity);
}
