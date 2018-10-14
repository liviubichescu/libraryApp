package Repository;
import Domain.Book;
import java.util.*;
import java.util.stream.Collectors;


/*
 * @author Liviu
 */
public class RepoBook implements IRepoBook {

    private Map<Long , Book> entities;

    public RepoBook(){
        this.entities = new HashMap<>();
    }

    @Override
    public Book findBook(Long id){
        throw new RuntimeException("Not implemented yes!");
    }

    @Override

    public Iterable<Book> findAll(){
        Set<Book> bookSet = entities.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toSet());
        return bookSet;
    }

    @Override

    public Book save(Book book){
        if (book == null) {
            throw new IllegalArgumentException("Entity must not be null.");
        }

        return entities.putIfAbsent(book.getId(), book);
    }

    @Override

    public Book delete(Long id){
        throw new RuntimeException("not yet implemented");
    }

    @Override

    public Book update(Book book){
        throw new RuntimeException("not yet implemented");
    }
}
