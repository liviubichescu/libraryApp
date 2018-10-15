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
        if (entities.containsKey(id))
            return entities.get(id);

        throw new RuntimeException("The specified book does not exist!");
    }

    @Override
    public Iterable<Book> findAll(){
        Set<Book> bookSet = entities.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toSet());
        return bookSet;
    }

    @Override
    public Book save(Book book){
        if (book == null) {
            throw new IllegalArgumentException("Entity - book - must not be null !!!");
        }

        return entities.putIfAbsent(book.getId(), book);
    }

    @Override
    public Book delete(Long id){
        if (entities.containsKey(id))
            return entities.remove(id);

        throw new RuntimeException("The specified book does not exists in database!!!");
    }

    @Override
    public Book update(Book book){
        if (entities.containsKey(book.getId())){
            entities.remove(book.getId());
            return entities.put(book.getId(),book);
        }
        throw new RuntimeException("Update error: The specified book does not exists in database!!!");
    }

}
