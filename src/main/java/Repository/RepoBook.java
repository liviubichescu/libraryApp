package Repository;

import Domain.Book;
import java.util.*;


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
//            throw new IllegalArgumentException("There already exists a book with this id!!!");
            System.out.println("There already exists a book with this id!!!");
    }

    @Override
    public void delBook(int id) throws IllegalArgumentException {
        Book book = findBook(id);
        myStore.remove(book);
    }

    @Override
    public Book findBook(int id) {
        Book book=null;
        for (int i=0; i<myStore.size();i++) {
            if (myStore.get(i).getId() == id) {
                book=myStore.get(i);
            }
        }
        return book;
    }

    @Override
    public ArrayList<Book> findAll() {
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
