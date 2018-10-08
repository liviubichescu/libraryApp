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

    public ArrayList getAll(){
        ArrayList<Book> array = this.repoBook.findAll();
        return array;
    }
}
