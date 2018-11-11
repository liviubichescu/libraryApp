package ro.ubb.homeWorkLibrary;

import ro.ubb.homeWorkLibrary.domain.Book;
import ro.ubb.homeWorkLibrary.domain.Client;
import ro.ubb.homeWorkLibrary.domain.Sales;
import ro.ubb.homeWorkLibrary.repository.inMemoryRepo.InMemoryRepository;
import ro.ubb.homeWorkLibrary.repository.inMemoryRepo.InMemorySalesRepo;
import ro.ubb.homeWorkLibrary.service.ServiceBook;
import ro.ubb.homeWorkLibrary.service.ServiceClienti;
import ro.ubb.homeWorkLibrary.service.ServiceSales;
import ro.ubb.homeWorkLibrary.ui.Console;
import ro.ubb.homeWorkLibrary.validators.BookValidator;
import ro.ubb.homeWorkLibrary.validators.ClientValidator;
import ro.ubb.homeWorkLibrary.validators.SalesValidator;
import ro.ubb.homeWorkLibrary.validators.Validator;

/*
created by Liviu
 */
public class Main {

    public static void main(String[] args){

        Validator<Book> validatorBook = new BookValidator();
        Validator<Client> clientValidator = new ClientValidator();
        Validator<Sales> salesValidator = new SalesValidator();
        InMemoryRepository<Long,Book> bookRepository = new InMemoryRepository<>(validatorBook);
        InMemoryRepository<Long,Client> clientRepository = new InMemoryRepository<>(clientValidator);
        InMemorySalesRepo<String, Sales> salesRepo = new InMemorySalesRepo<>(salesValidator);
        ServiceBook serviceBook = new ServiceBook(bookRepository);
        ServiceClienti serviceClienti = new ServiceClienti(clientRepository);
        ServiceSales serviceSales = new ServiceSales(salesRepo);
        Console console = new Console(serviceBook, serviceClienti,serviceSales);

        Book book = new Book(1L,"Povestiri","Ion Creanga","Humanitas", 1990,15);
        Book book2 = new Book(2L,"La tiganci","Mircea Eliade","Litera", 1985,20);
        Book book3 = new Book(3L,"Baltagul","Mihail Sadoveanu","Limes", 1985,20);
        Book book4 = new Book(4L,"Valea Frumoasei", "Mihail Sadoveanu", "ALL" , 1990, 20);
        Book book5 = new Book(5L,"Ultima noapte de dragoste, intaia noapte de razboi", "Liviu Rebreanu", "Corint" , 1983, 20);
        Book book6 = new Book(6L,"Poezii celebre", "Mihai Eminescu", "ART" , 1989, 20);
        Book book7 = new Book(7L,"Poezii si proza","George Cosbuc","Presa", 1979,15);

        Client client = new Client(1L,"Ion","Avadanii",56);
        Client client2 = new Client(2L,"Marius","Parpaliu",50);
        Client client3 = new Client(3L,"Ghita","Parpaliu",50);
        Client client4 = new Client(4L,"Vasile","Parpaliu",50);
        serviceClienti.addClient(client);
        serviceClienti.addClient(client2);
        serviceClienti.addClient(client3);
        serviceClienti.addClient(client4);

        serviceBook.addBook(book);
        serviceBook.addBook(book2);
        serviceBook.addBook(book3);
        serviceBook.addBook(book4);
        serviceBook.addBook(book5);
        serviceBook.addBook(book6);
        serviceBook.addBook(book7);

        Sales sales = new Sales(2L,1L);
        Sales sales1 = new Sales(1L,2L);
        Sales sales2 = new Sales(2L,4L);
        Sales sales3 = new Sales(1L,3L);
        Sales sales4 = new Sales(3L,1L);
        Sales sales5 = new Sales(2L,3L);

        serviceSales.addSales(sales);
        serviceSales.addSales(sales1);
        serviceSales.addSales(sales2);
        serviceSales.addSales(sales3);
        serviceSales.addSales(sales4);
        serviceSales.addSales(sales5);


        console.meniu();


//
//        /* file repo */
//        try {
//            System.out.println(new File(".").getCanonicalPath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        /* in file repo */
//        Validator<Book> bookValidator = new BookValidator();
//        Validator<Client> clientValidator = new ClientValidator();
//        repository<Long, Book> bookRepository = new BookFileRepository(bookValidator, "./data/books.txt");
//        repository<Long, Client> clientRepository = new ClientFileRepository(clientValidator, "./data/clients.txt");
//        ServiceBook serviceBook2 = new ServiceBook(bookRepository);
//        ServiceClienti serviceClienti2 = new ServiceClienti(clientRepository);
//        Console console2 = new Console(serviceBook2, serviceClienti2);
//        console2.meniu();
//
        /* xml repo*/
//        Validator<Book> bookValidator = new BookValidator();
//        Validator<Client> clientValidator = new ClientValidator();
//        repository<Long, Book> bookRepository = new XmlFileRepository(bookValidator, "./data/bookstore.xml");
//        repository<Long, Client> clientRepository = new ClientFileRepository(clientValidator, "./data/clients.txt");
//        ServiceBook serviceBook2 = new ServiceBook(bookRepository);
//        ServiceClienti serviceClienti2 = new ServiceClienti(clientRepository);
//        Console console2 = new Console(serviceBook2, serviceClienti2);
//        console2.meniu();



//         /* database repo */
//        Validator<Book> bookValidator = new BookValidator();
//        Validator<Client> clientValidator = new ClientValidator();
//        Repository<Long, Book> bookRepository = new BookDatabaseRepository(bookValidator);
//        Repository<Long, Client> clientRepository = new ClientDatabaseRepository(clientValidator);
//        ServiceBook serviceBook2 = new ServiceBook(bookRepository);
//        ServiceClienti serviceClienti2 = new ServiceClienti(clientRepository);
//        Console console2 = new Console(serviceBook2, serviceClienti2);
//        console2.meniu();




//        List<Book> books = loadBooks();
//        books.forEach(System.out::println);
//
//        Book book = new Book("c1", "t1", "a1", 2018, 50);
//        saveData(book);
//
//        System.out.println("bye");

    }
}
