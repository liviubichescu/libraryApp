package ro.ubb.homeWorkLibrary.service;

import ro.ubb.homeWorkLibrary.domain.Book;
import ro.ubb.homeWorkLibrary.domain.Client;
import ro.ubb.homeWorkLibrary.domain.Sales;
import ro.ubb.homeWorkLibrary.repository.Repository;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceSales {

    private Repository<String, Sales> repositorySales;
    private Repository<Long, Book> bookRepository;
    private Repository<Long, Client> clientRepository;
    private List<BestSellerBook> bestSellerBookList = new ArrayList<>();

    public ServiceSales(Repository<String, Sales> repositorySales, Repository<Long, Book> bookRepository, Repository<Long, Client> clientRepository) {
        this.clientRepository=clientRepository;
        this.bookRepository = bookRepository;
        this.repositorySales = repositorySales;
    }

    public void addSales(Sales sales) {
        repositorySales.save(sales);
    }

    public Set<Sales> getAllSales() {
        Iterable<Sales> sales = repositorySales.findAll();
        return StreamSupport.stream(sales.spliterator(), false).collect(Collectors.toSet());
    }

    public boolean checkSale(String sale) {
        Optional sales = repositorySales.findOne(sale);

        return sales.isPresent();
    }

    public void removeSale(String bookClientsPK) {
        repositorySales.delete(bookClientsPK);
    }

    /**
     * metoda care pune intr-o lista toate id-urile de carti care s-au vandut
     *
     * @return o lista de id-uri
     */
    public List<Long> bookIdsThatWhereSold() {
        List<Long> bookIds = new ArrayList<>();
        for (Sales s : repositorySales.findAll()) {
            if (!bookIds.contains(s.getBookIdSale())) {
                bookIds.add(s.getBookIdSale());
            }

        }
        return bookIds;
    }

    /**
     * Metoda numara de cate ori apare in repositoriul nostru de vanzari fiecare id de carte vandut
     *
     * @param bId - id-ul unei carti
     * @return un numar de aparitii al unui id
     */
    public int competeCount(Long bId) {
        int countNrAparitii = 0;
        for (Sales s : repositorySales.findAll()) {
            if (s.getBookIdSale().equals(bId)) {
                countNrAparitii++;
            }
        }
        return countNrAparitii;
    }

    /**
     * Metoda adauga intr-o lista de tip BestSellerBook toate vanzarile grupate dupa id-ul cartii vandute.
     * Sorteaza lista in ordinea numarului de carti vandute.
     *
     * @return returneaza best sellerul cu cele mai multe vanzari( de pe pozitia 0);
     */
    public BestSellerBook bestSeller() {
        ArrayList<BestSellerBook> bsArray = new ArrayList<>();
        for (Long bs : bookIdsThatWhereSold()) {
            int count = competeCount(bs);
            bestSellerBookList.add(new BestSellerBook(bs, count));
        }
        bestSellerBookList.sort(Comparator.comparing(BestSellerBook::getNumberOfSales));
        Collections.reverse(bestSellerBookList);

        return bestSellerBookList.get(0);
    }



    /**
     * Metoda adauga intr-o lista de tip BestSellerBook toate vanzarile grupate dupa id-ul cartii vandute.
     * Sorteaza lista in ordinea numarului de carti vandute.
     *
     * @return returneaza o lista de bestSeller-uri(cu id-ul cartii si numarul de vanzari)
     */
    public List<BestSellerBook> mostSoldBooks() {
        List<BestSellerBook> bsArray = new ArrayList<>();
        for (Long bs : bookIdsThatWhereSold()) {
            int count = competeCount(bs);
            bsArray.add(new BestSellerBook(bs, count));
        }
        return bsArray;
    }

    /**
     * Metoda primeste ca parametru o carte
     * Ia toata lista de carti cu numarul de bucati vandute
     * Calculeaza profitul pentru cartea data ca parametru pe baza numarului de carti vandute
     *
     * returneaza profitul
     */
    public BestProfit calcProfitForBook(Book book){
        double profit=0;
        String title="";
        List<BestSellerBook> soldBooks = mostSoldBooks();
        for (BestSellerBook bs : soldBooks) {
            if (bs.getBookId().equals(book.getId())){
                profit = bs.getNumberOfSales()* book.getPrice();
                title = book.getTitle();
            }
        }
        BestProfit bestProfit = new BestProfit(title,profit);
        return bestProfit;
    }

    // Radu version
    public BestProfit calcProfitForBookRaduVersion(Book book){

        int c = competeCount(book.getId());
        return new BestProfit(book.getTitle(),(book.getPrice()*c));
    }

    /**
     * Metoda adauga intr-o lista de BestProfit(titlul si profitul unei carti) un best profit
     */
    public List<BestProfit> bestProfit (){
        List<BestProfit> profitList = new ArrayList<>();
        Iterable<Book> sales = bookRepository.findAll();
        for (Book book: sales){
            profitList.add(calcProfitForBookRaduVersion(book));
        }
        profitList.sort(Comparator.comparing(BestProfit::getProfit));
        Collections.reverse(profitList);

        return profitList;
    }


    /**
     * Se citeste un numar nr.
     * Sa se afiseze clientii care au cumparat cele mai multe carti intr-o lista de forma
     * <nume-client> <numar-carti-cumparate>
     * sortata alfabetic dupa <nume-client>.
     */


    public int howManyBooksClientsBought(Long clientID) {
        int countNrCartiCumparate = 0;
        for (Sales s : repositorySales.findAll()) {
            if (s.getClientIdSale().equals(clientID)) {
                countNrCartiCumparate++;
            }
        }
        return countNrCartiCumparate;
    }
    /**
     * Metoda primeste ca parametru un client
     * Ia toata lista de clienti si calculeaza cate carti a cumparat fiecare client si ne returneaza un Active client
     *
     */
    public ActiveClients makeClientAsActiveClient(Client client){

        int cartiCump = howManyBooksClientsBought(client.getId());
        return new ActiveClients(client.getName(),cartiCump);
    }

    /*
     * creez o lista de clienti activi
     */
    public List<ActiveClients> addActiveClientsInList (){
        List<ActiveClients> activeClients = new ArrayList<>();
        Iterable<Client> clients = clientRepository.findAll();
        for (Client client: clients){
            activeClients.add(makeClientAsActiveClient(client));
        }
        activeClients.sort(Comparator.comparing(ActiveClients::getNumeClient));
//        Collections.reverse(profitList);

        return activeClients;
    }




}
