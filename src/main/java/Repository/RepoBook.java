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
        if (myStore==null){
            throw new IllegalArgumentException("Store is empty!!!");
        }else if(findBook(book.getId())==false){
            myStore.add(book);
        }
        else
            throw new IllegalArgumentException("There already exists a book with this id!!!");
    }

    @Override
    public Book delBook(int id) {
        return null;
    }

    @Override
    public Boolean findBook(int id) {
        boolean findByID = false;
        for (int i=0; i<myStore.size();i++) {
            if (myStore.get(i).getId() == id) {
                findByID = true;
            }
        }
        return findByID;
    }

    @Override
    public ArrayList<Book> findAll() {
        return myStore;
    }

    @Override
    public Book updateBook(Book book) {
        return null;
    }
}
