package Service;

import Domain.Book;
import Repository.IRepoBook;
import Repository.RepoBook;
import java.util.ArrayList;
import java.util.List;

public class ServiceBook {

    private IRepoBook repoBook;

    public ServiceBook(RepoBook repoBook) {
        this.repoBook = repoBook;
    }

    public void addBook(Book book){
        repoBook.save(book);
    }

    public void removeBook(Long bookId){
        repoBook.delete(bookId);
    }

    public void updateBookService(Book book) {
        repoBook.update(book);
    }


    public Iterable<Book> getAll(){
        return this.repoBook.findAll();
    }
}
