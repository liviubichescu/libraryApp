import Domain.Book;
import Domain.Client;
import Repository.RepoBook;
import Repository.RepoClient;
import Service.ServiceBook;
import Service.ServiceClienti;
import UI.Console;

/*
created by Liviu
 */
public class Main {

    public static void main(String[] args){
        RepoBook repoBook = new RepoBook();
        RepoClient repoClient = new RepoClient();
        ServiceBook serviceBook = new ServiceBook(repoBook);
        ServiceClienti serviceClienti = new ServiceClienti(repoClient);
        Console console = new Console(serviceBook, serviceClienti);

        Book book = new Book(1L,"Povestiri","Ion Creanga","Humanitas", 1990,15);
        Book book2 = new Book(2L,"La tiganci","Mircea Eliade","Litera", 1985,20);
        Book book3 = new Book(3L,"Baltagul","Mihail Sadoveanu","Limes", 1985,20);
        Book book4 = new Book(4L,"Valea Frumoasei", "Mihail Sadoveanu", "ALL" , 1990, 20);
        Book book5 = new Book(5L,"Ultima noapte de dragoste, intaia noapte de razboi", "Liviu Rebreanu", "Corint" , 1983, 20);
        Book book6 = new Book(6L,"Poezii celebre", "Mihai Eminescu", "ART" , 1989, 20);
        Book book7 = new Book(7L,"Poezii si proza","George Cosbuc","Presa", 1979,15);


        Client client1 = new Client(1L,"Antonescu","Mihali", 22);
        Client client2 = new Client(2L,"Pupaza", "Marin", 55);


        serviceBook.addBook(book);
        serviceBook.addBook(book2);
        serviceBook.addBook(book3);
        serviceBook.addBook(book4);
        serviceBook.addBook(book5);
        serviceBook.addBook(book6);
        serviceBook.addBook(book7);

        serviceClienti.addClient(client1);
        serviceClienti.addClient(client2);

        console.meniu();



    }
}
