package ro.ubb.homeWorkLibrary.service;

import ro.ubb.homeWorkLibrary.domain.Book;
import ro.ubb.homeWorkLibrary.repository.Repository;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceBook {

    private Repository<Long, Book> repository;

    public ServiceBook(Repository<Long, Book> repository) {
        this.repository = repository;
    }

    public void addBook(Book book){
        repository.save(book);
    }

    public Set<Book> getAllBooks(){
        Iterable<Book> books = repository.findAll();
        return StreamSupport.stream(books.spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Book> filterBooksByName(String s){
        Iterable<Book> books = repository.findAll();

        Set<Book> filteredBooks = new HashSet<>();
        books.forEach(filteredBooks::add);
        filteredBooks.removeIf(book -> !book.getAuthor().contains(s));

        return filteredBooks;
    }

    public void removeBook(Long bookId){
        repository.delete(bookId);
    }


    public void updateBookService(Book book){
        repository.update(book);
    }

    public Set<Book> findBook(Long id){
        Iterable<Book> books = repository.findAll();

        Set<Book> filteredBooks = new HashSet<>();
        books.forEach(filteredBooks::add);
        filteredBooks.removeIf(book -> book.getId() != id);

        return filteredBooks;
    }

}
