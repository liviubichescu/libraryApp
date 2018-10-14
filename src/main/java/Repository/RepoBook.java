package Repository;
import Domain.Book;
import java.util.*;
import java.util.stream.Collectors;


/*
 * @author Liviu
 */
public class RepoBook implements IRepoBook {

    private ArrayList<Book> myStore;

    public RepoBook() {
        this.myStore = new ArrayList<>();
    }

    @Override
    public void addBook(Book book) throws IllegalArgumentException {

        if(findBook(book.getId())==null){
            myStore.add(book);
        }
        else
            throw new IllegalArgumentException("There already exists a book with this id!!!");
    }

    @Override
    public void delBook(int id) throws IllegalArgumentException {
        Book book = findBook(id);
        myStore.remove(book);
    }

    @Override
    public Book findBook(int id) {
//          nu reusesc sa scriu un stream care sa imi returneze un obiect de tip book;
//           streamul de mai joj imi face filtrarea dar imi returneaza un stream si nu un obiect de tip Book;

        List<Book> books = myStore.stream()
                .filter(p->p.getId()==id)
                .collect(Collectors.toList());
//                .forEach(System.out::println);

        return books.size()==0?null:books.get(0);
//        Book book=null;
//        for (int i=0; i<myStore.size();i++) {
//            if (myStore.get(i).getId() == id) {
//                book=myStore.get(i);
//            }
//
//        }
//        return book;
    }

    @Override
    public List<Book> findAll() {
        return myStore;
    }

    @Override
    public Book updateBook(Book carte) {
        Book book=findBook(carte.getId());

        for (int i=0; i<myStore.size();i++) {
            if (myStore.get(i)==book) {
                myStore.set(i,carte);
            }
        }
        return book;
    }
}
