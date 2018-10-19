import Domain.Book;
import Domain.Client;
import Domain.Validators.BookValidator;
import Domain.Validators.ClientValidator;
import Domain.Validators.Validator;
import Repository.InMemoryRepository;
import Repository.Repository;
import Repository.BookFileRepository;
import Repository.ClientFileRepository;
import Service.ServiceBook;
import Service.ServiceClienti;
import UI.Console;
import java.io.File;
import java.io.IOException;

/*
created by Liviu
 */
public class Main {

    public static void main(String[] args){

//        Validator<Book> validatorBook = new BookValidator();
//        Validator<Client> clientValidator = new ClientValidator();
//        Repository<Long, Book> bookRepository = new InMemoryRepository<>(validatorBook);
//        Repository<Long, Client> clientRepository = new InMemoryRepository<>(clientValidator);
//        ServiceBook serviceBook = new ServiceBook(bookRepository);
//        ServiceClienti serviceClienti = new ServiceClienti(clientRepository);
//        Console console = new Console(serviceBook, serviceClienti);
//
//        Book book = new Book(1L,"Povestiri","Ion Creanga","Humanitas", 1990,15);
//        Book book2 = new Book(2L,"La tiganci","Mircea Eliade","Litera", 1985,20);
//        Book book3 = new Book(3L,"Baltagul","Mihail Sadoveanu","Limes", 1985,20);
//        Book book4 = new Book(4L,"Valea Frumoasei", "Mihail Sadoveanu", "ALL" , 1990, 20);
//        Book book5 = new Book(5L,"Ultima noapte de dragoste, intaia noapte de razboi", "Liviu Rebreanu", "Corint" , 1983, 20);
//        Book book6 = new Book(6L,"Poezii celebre", "Mihai Eminescu", "ART" , 1989, 20);
//        Book book7 = new Book(7L,"Poezii si proza","George Cosbuc","Presa", 1979,15);
//
//        Client client = new Client(1L,"Ion","Avadanii",56);
//        Client client2 = new Client(2L,"Marius","Parpaliu",50);
//        serviceClienti.addClient(client);
//        serviceClienti.addClient(client2);
//
//        serviceBook.addBook(book);
//        serviceBook.addBook(book2);
//        serviceBook.addBook(book3);
//        serviceBook.addBook(book4);
//        serviceBook.addBook(book5);
//        serviceBook.addBook(book6);
//        serviceBook.addBook(book7);
//        console.meniu();
        /* file repo */
        try {
            System.out.println(new File(".").getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* in file repo */
        Validator<Book> bookValidator = new BookValidator();
        Validator<Client> clientValidator = new ClientValidator();
        Repository<Long, Book> bookRepository = new BookFileRepository(bookValidator, "./data/books.txt");
        Repository<Long, Client> clientRepository = new ClientFileRepository(clientValidator, "./data/clients.txt");
        ServiceBook serviceBook2 = new ServiceBook(bookRepository);
        ServiceClienti serviceClienti2 = new ServiceClienti(clientRepository);
        Console console2 = new Console(serviceBook2, serviceClienti2);
        console2.meniu();



    }
}
