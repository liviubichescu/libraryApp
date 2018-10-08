package UI;

import Domain.Book;
import Service.ServiceBook;

import java.util.Scanner;

public class Console {

    ServiceBook serviceBook;

    public Console(ServiceBook serviceBook){
        this.serviceBook = serviceBook;
    }

    private void printMeniu(){
        System.out.println();
        System.out.println("1. Adauga carte");
        System.out.println("2. Sterge carte");
        System.out.println("3. Cauta o carte dupa ID");
        System.out.println("4. Afiseaza toate cartile");
        System.out.println("0. Exit");

    }

    private void adaugaCarte(){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduceti ID-ul cartii: ");
        int id = scanner.nextInt();

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

    private void printAll(){
        for (int i = 0; i<serviceBook.getAll().size(); i++){
            System.out.println(serviceBook.getAll().get(i));
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
                    System.out.println("This option is not yet implemented");
                    break;
                case 3:
                    System.out.println("This option is not yet implemented");
                    break;
                case 4:
                    printAll();
                    break;
                default:
                    System.out.println("This option is not yet implemented");
            }
        }


    }



}
