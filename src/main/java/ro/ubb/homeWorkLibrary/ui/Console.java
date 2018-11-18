package ro.ubb.homeWorkLibrary.ui;

import ro.ubb.homeWorkLibrary.domain.Book;
import ro.ubb.homeWorkLibrary.domain.Client;
import ro.ubb.homeWorkLibrary.domain.Sales;
import ro.ubb.homeWorkLibrary.service.ServiceBook;
import ro.ubb.homeWorkLibrary.service.ServiceClienti;
import ro.ubb.homeWorkLibrary.service.ServiceSales;

import java.util.Scanner;

public class Console {

    private ServiceBook serviceBook;
    private ServiceClienti serviceClienti;
    private ServiceSales serviceSales;

    public Console(ServiceBook serviceBook, ServiceClienti serviceClienti, ServiceSales serviceSales) {
        this.serviceBook = serviceBook;
        this.serviceClienti = serviceClienti;
        this.serviceSales = serviceSales;
    }


    private void printMeniu() {
        System.out.println();
        System.out.println("1. Adauga carte");
        System.out.println("2. Sterge carte");
        System.out.println("3. Update carte");
        System.out.println("4. Afiseaza toate cartile");
        System.out.println("5. Filtreaza dupa autor");
        System.out.println("*************************************");
        System.out.println("6. Adauga client nou");
        System.out.println("7. Sterge client");
        System.out.println("8. Update client");
        System.out.println("9. Afiseaza toti clientii");
        System.out.println("*************************************");
        System.out.println("10. Adauga Vanzare");
        System.out.println("11. Sterge Vanzare");
        System.out.println("12. Update Vanzare");
        System.out.println("13. Afiseaza toate vanzarile!");
        System.out.println("14. Best Seller!");
        System.out.println("0. Exit");
    }

    public void meniu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMeniu();
            int choice = scanner.nextInt();
            if (choice == 0)
                break;
            switch (choice) {
                case 1:
                    adaugaCarte();
                    break;
                case 2:
                    stergeCarte();
                    break;
                case 3:
                    updateCarte();
                    break;
                case 4:
                    printAllBooks();
                    break;
                case 5:
                    filterByAuthor();
                    break;
                case 6:
                    adaugaClient();
                    break;
                case 7:
                    stergeClient();
                    break;
                case 8:
                    updateClient();
                    break;
                case 9:
                    printAllClients();
                    break;
                case 10:
                    addSale();
                    break;
                case 11:
                    removeSale();
                    break;
                case 12:
                    updateSale();
                    break;
                case 13:
                    printAllSales();
                    break;
                case 14:
                    bestSeller();
                    break;
                default:
                    System.out.println("This option is not yet implemented");
            }
        }


    }

    private void adaugaCarte() {

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

        Book book = new Book(id, titlu, autor, editura, an, pret);

        serviceBook.addBook(book);
    }

    private void stergeCarte() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti ID-ul cartii: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        serviceBook.removeBook(id);
    }

    private void updateCarte() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduceti ID-ul cartii pe care doriti sa o modificati: ");
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

        Book book = new Book(id, titlu, autor, editura, an, pret);

        try {
            serviceBook.updateBookService(book);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void printAllBooks() {
        for (Book b : serviceBook.getAllBooks()) {
            System.out.println(b);
        }
    }

    private void filterByAuthor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti autorul cartii: ");
        String autor = scanner.nextLine();

        for (Book b : serviceBook.filterBooksByName(autor)) {
            System.out.println(b);
        }
    }


    private void adaugaClient() {

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

    private void stergeClient() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti ID-ul clientului: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        serviceClienti.removeClient(id);
    }

    private void updateClient() {

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduceti ID-ul clientului pentru update: ");
            Long id = scanner.nextLong();
            scanner.nextLine();

            System.out.print("Introduceti Numele: ");
            String nume = scanner.nextLine();

            System.out.print("Introduceti Prenumele: ");
            String prenume = scanner.nextLine();

            System.out.print("Introduceti varsta: ");
            int varsta = scanner.nextInt();

            Client client = new Client(id, nume, prenume, varsta);


            serviceClienti.updateClientService(client);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void printAllClients() {
        for (Client c : serviceClienti.getAllClients()) {
            System.out.println(c);
        }
    }


    private void addSale() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Please enter book ID you want to sell: ");
            Long idCarte = scanner.nextLong();
            scanner.nextLine();

            System.out.print("Please enter the customer ID: ");
            Long idClient = scanner.nextLong();
            scanner.nextLine();

            Sales sale = new Sales(idCarte, idClient);

            serviceSales.addSales(sale);

        } catch (RuntimeException e) {
            throw new ConsoleException("Transaction failed! Please enter valid data!!!" + e);
        }

    }

    private void removeSale() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Please enter book ID for the sale to delete: ");
            Long idCarte = scanner.nextLong();
            scanner.nextLine();

            System.out.print("Please enter client ID for the sale to delete: ");
            Long idClient = scanner.nextLong();
            scanner.nextLine();


            serviceSales.removeSale(idCarte.toString() + idClient.toString());

        } catch (RuntimeException e) {
            throw new ConsoleException("Transaction failed! Please enter valid data!!!" + e);
        }

    }

    private void updateSale() {
        System.out.println("Nu se poate face update!!! \n" +
                "ID-ul vanzarii este compus din 2 id-uri care identifica unic o vanzare!!! \n" +
                "Ex: Nu putem sa modificam vanzarea cu id-ul 23 sa aiba BookID=5 si ClientID=6 !!!");
//        Scanner scanner = new Scanner(System.in);
//        try {
//            System.out.print("Please enter book ID for the sale to update: ");
//            Long idCarte = scanner.nextLong();
//            scanner.nextLine();
//
//            System.out.print("Please enter client ID for the sale to delete: ");
//            Long idClient = scanner.nextLong();
//            scanner.nextLine();
//
//            Sales sale = new Sales(idCarte, idClient);
//
//            if (serviceSales.checkSale(sale.getId())) {
//                serviceSales.updateSaleService(sale);
//            } else {
//                System.out.println("***** Update failed!!! This sale is not in database!!! *****");
//            }
//        } catch (RuntimeException e) {
//            throw new ConsoleException("Transaction failed! Please enter valid data!!!" + e);
//        }
    }

    private void bestSeller() {
        System.out.println(serviceSales.bestSeller());
    }

    private void printAllSales() {
        serviceSales.getAllSales()
                .forEach(System.out::println);
    }


}
