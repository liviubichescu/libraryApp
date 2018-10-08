package Service;

import Domain.Book;
import Repository.RepoBook;
import java.util.ArrayList;

public class ServiceBook {

    private RepoBook repoBook;

    public ServiceBook(RepoBook repoBook) {
        this.repoBook = repoBook;
    }

    public void addBook(Book book){
        repoBook.addBook(book);
    }

    public void removeBook(int bookId){
        repoBook.delBook(bookId);
    }

    public void updateBookService(Book book) {
        repoBook.updateBook(book);
    }


    public ArrayList getAll(){
        return this.repoBook.findAll();
    }
}
