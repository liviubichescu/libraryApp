import Domain.Book;
import Repository.RepoBook;
import Service.ServiceBook;
import UI.Console;

/*
created by Liviu
 */
public class Main {

    public static void main(String[] args){
        RepoBook repoBook = new RepoBook();
        ServiceBook serviceBook = new ServiceBook(repoBook);
        Console console = new Console(serviceBook);

        Book book = new Book(1,"Povestiri","Ion Creanga","Humanitas", 1990,15);
        Book book2 = new Book(2,"La tiganci","Mircea Eliade","Humanitas", 1985,20);
        Book book3 = new Book(3,"La tiganci","Mircea Eliade","Humanitas", 1985,20);
        Book book4 = new Book(4,"Ana are mere", "Si pere", "De la masa" , 1990, 20);

        serviceBook.addBook(book);
        serviceBook.addBook(book2);
        serviceBook.addBook(book3);

        console.meniu();




    }
}
