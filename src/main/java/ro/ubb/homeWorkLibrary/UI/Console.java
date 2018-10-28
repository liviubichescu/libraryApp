package ro.ubb.homeWorkLibrary.UI;

import ro.ubb.homeWorkLibrary.Domain.Book;
import ro.ubb.homeWorkLibrary.Domain.Client;
import ro.ubb.homeWorkLibrary.Exceptions.ValidatorException;
import ro.ubb.homeWorkLibrary.Service.ServiceBook;
import ro.ubb.homeWorkLibrary.Service.ServiceClienti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Console {

    private ServiceBook serviceBook;
    private ServiceClienti serviceClienti;


    public Console(ServiceBook serviceBook, ServiceClienti serviceClienti){
        this.serviceBook = serviceBook;
        this.serviceClienti = serviceClienti;
    }

    private void printMeniu(){
        System.out.println();
        System.out.println("1. Adauga carte");
        System.out.println("2. Sterge carte");
        System.out.println("3. Adauga client nou");
        System.out.println("4. Sterge client");
        System.out.println("5. Afiseaza toate cartile");
        System.out.println("6. Afiseaza toti clientii");
        System.out.println("7. Filtreaza dupa autor");
        System.out.println("0. Exit");

    }

    private void adaugaCarte(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti ID-ul cartii: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Introduceti Titlul: ");
        String titlu = scanner.nextLine();

        System.out.print("Introduceti Autorul: ");
        String autor = scanner.nextLine();

        System.out.print("Introduceti Editura: ");
        String editura = scanner.nextLine();

        System.out.print("Introduceti anul lansarii: ");
        int an = scanner.nextInt();

        System.out.print("Introduceti pretul:");
        double pret = scanner.nextDouble();

        Book book = new Book(id,titlu,autor,editura,an,pret);

        serviceBook.addBook(book);

//        addBooks();
    }

    private void adaugaClient(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti ID-ul clientului: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Introduceti Numele: ");
        String nume = scanner.nextLine();

        System.out.print("Introduceti Prenumele: ");
        String prenume = scanner.nextLine();

        System.out.print("Introduceti varsta: ");
        int varsta = scanner.nextInt();

        Client client = new Client(id, nume, prenume, varsta);
        serviceClienti.addClient(client);
    }

    private void stergeCarte() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti ID-ul cartii: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        serviceBook.removeBook(id);
    }

    private void stergeClient() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti ID-ul clientului: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        serviceClienti.removeClient(id);
    }

    private void printAllBooks(){
        for (Book b:serviceBook.getAllBooks()) {
            System.out.println(b);
        }
    }

    private void printAllClients(){
        for (Client c:serviceClienti.getAllClients()) {
            System.out.println(c);
        }
    }


    private void addBooks() {
        while (true) {
            Book book = readBook();
            if (book == null || book.getId() < 0) {
                break;
            }
            try {
                serviceBook.addBook(book);
            } catch (ValidatorException e) {
                e.printStackTrace();
            }
        }
    }

    private Book readBook() {
        System.out.println("Read book {id, Titlu, autor, editura, an, pret}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());
            String titlu = bufferRead.readLine();
            String autor = bufferRead.readLine();
            String editura = bufferRead.readLine();
            int an = Integer.parseInt(bufferRead.readLine());
            long pret = Long.parseLong(bufferRead.readLine());

            Book book = new Book(id,titlu,autor,editura,an,pret);
            book.setId(id);

            return book;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    private void filterByAuthor(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti autorul cartii: ");
        String autor = scanner.nextLine();

        for (Book b:serviceBook.filterBooksByName(autor)) {
            System.out.println(b);
        }
    }

    public void meniu(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            printMeniu();
            int choice=scanner.nextInt();
            if (choice==0)
                break;
            switch (choice){
                case 1:
                    adaugaCarte();
                    break;
                case 2:
                    stergeCarte();
                    break;
                case 3:
                    adaugaClient();
                    break;
                case 4:
                    stergeClient();
                    break;
                case 5:
                    printAllBooks();
                    break;
                case 6:
                    printAllClients();
                    break;
                case 7:
                    filterByAuthor();
                    break;
                default:
                    System.out.println("This option is not yet implemented");
            }
        }


    }
}
