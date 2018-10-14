package UI;

import Domain.Book;
import Domain.Client;
import Service.ServiceBook;
import Service.ServiceClienti;

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
        System.out.println("3. Update carte");
        System.out.println("4. Adauga client nou");
        System.out.println("5. Sterge client");
        System.out.println("6. Update client existent");
        System.out.println("7. Afiseaza toate cartile");
        System.out.println("8. Afiseaza toti clientii");
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
    }

    private void stergeCarte() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti ID-ul cartii: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        serviceBook.removeBook(id);
    }

    private void printAll(){
        for (Book b:serviceBook.getAll()) {
            System.out.println(b);
        }
    }

    private void updateBookConsole(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduceti id-ul cartii pe care doriti sa o modificati: ");
        Long idCarte = scanner.nextLong();
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

        Book book = new Book(idCarte,titlu,autor,editura,an,pret);
        serviceBook.updateBookService(book);
    }

    private void addClient(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduceti id-ul dorit: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Introduceti numele: ");
        String nume = scanner.nextLine();

        System.out.println("Introduceti prenumele: ");
        String prenume = scanner.nextLine();

        System.out.println("Introduceti varsta: ");
        int age = scanner.nextInt();

        Client client = new Client(id,nume,prenume,age);
        serviceClienti.addClient(client);
    }

    private void deleteClient(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti ID-ul clientului: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        serviceClienti.deleteClient(id);
    }

    private void updateClient(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti id-ul clientului ce va fi modificat: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Introduceti numele nou: ");
        String nume = scanner.nextLine();

        System.out.println("Introduceti prenumele nou: ");
        String prenume = scanner.nextLine();

        System.out.println("Introduceti varsta noua: ");
        int age = scanner.nextInt();

        Client client = new Client(id, nume, prenume, age);
        serviceClienti.updateClient(client);
    }

    private void printAllClients(){
        for (Client b:serviceClienti.getAll()) {
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
                    updateBookConsole();
                    break;
                case 4:
                    addClient();
                    break;
                case 5:
                    deleteClient();
                    break;
                case 6:
                    updateClient();
                    break;
                case 7:
                    printAll();
                    break;
                case 8:
                    printAllClients();
                    break;
                default:
                    System.out.println("This option is not yet implemented");
            }
        }


    }

}
