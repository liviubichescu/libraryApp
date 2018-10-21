package ro.ubb.homeWorkLibrary.Service;

import ro.ubb.homeWorkLibrary.Domain.Book;
import ro.ubb.homeWorkLibrary.Exceptions.ValidatorException;
import ro.ubb.homeWorkLibrary.Repository.Repository;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceBook {

    private Repository<Long, Book> repository;

    public ServiceBook(Repository<Long, Book> repository) {
        this.repository = repository;
    }

    public void addBook(Book book)throws ValidatorException {
        repository.save(book);
    }

    public Set<Book> getAllBooks() {
        Iterable<Book> books = repository.findAll();
        return StreamSupport.stream(books.spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Book> filterBooksByName(String s) {
        Iterable<Book> books = repository.findAll();
        //version 1
//        Set<Student> filteredStudents = StreamSupport.stream(students.spliterator(), false)
//                .filter(student -> student.getName().contains(s)).collect(Collectors.toSet());

        //version 2
        Set<Book> filteredBooks = new HashSet<>();
        books.forEach(filteredBooks::add);
        filteredBooks.removeIf(book -> !book.getAuthor().contains(s));

        return filteredBooks;
    }

    public void removeBook(Long bookId){
        repository.delete(bookId);
    }

//
//    public void updateBookService(Book book) {
//        repoBook.update(book);
//    }
//
//
//    public Iterable<Book> getAll(){
//        return this.repoBook.findAll();
//    }
}
