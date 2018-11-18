package ro.ubb.homeWorkLibrary.service;

import ro.ubb.homeWorkLibrary.domain.Book;
import ro.ubb.homeWorkLibrary.domain.Client;
import ro.ubb.homeWorkLibrary.domain.Sales;
import ro.ubb.homeWorkLibrary.repository.Repository;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ServiceSales {

    private Repository<String, Sales> repository;
    private List<BestSeller> bestSellerList = new ArrayList<>();

    public ServiceSales(Repository<String, Sales> repository) {
        this.repository = repository;
    }

    public void addSales(Sales sales) {
        repository.save(sales);
    }

    public Set<Sales> getAllSales() {
        Iterable<Sales> sales = repository.findAll();
        return StreamSupport.stream(sales.spliterator(), false).collect(Collectors.toSet());
    }

    public boolean checkSale(String sale) {
        Optional sales = repository.findOne(sale);

        return sales.isPresent();
    }

    public void removeSale(String bookClientsPK) {
        repository.delete(bookClientsPK);
    }


//    public void updateSaleService(Sales sale){
//        repository.update(sale);
//    }

    /**
     * metoda care pune intr-o lista toate id-urile de carti care s-au vandut
     *
     * @return o lista de id-uri
     */
    public List<Long> bookIdsThatWhereSold() {
        List<Long> bookIds = new ArrayList<>();
        for (Sales s : repository.findAll()) {
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
        for (Sales s : repository.findAll()) {
            if (s.getBookIdSale().equals(bId)) {
                countNrAparitii++;
            }
        }
        return countNrAparitii;
    }

    /**
     * Metoda adauga intr-o lista de tip BestSeller toate vanzarile grupare dupa id-ul cartii vandute.
     * Sorteaza lista in ordinea numarului de carti vandute.
     *
     * @return returneaza best sellerul cu cele mai multe vanzari( de pe pozitia 0);
     */
    public BestSeller bestSeller() {
        ArrayList<BestSeller> bsArray = new ArrayList<>();
        for (Long bs : bookIdsThatWhereSold()) {
            int count = competeCount(bs);
            bestSellerList.add(new BestSeller(bs, count));
        }
        bestSellerList.sort(Comparator.comparing(BestSeller::getNumberOfSales));
        Collections.reverse(bestSellerList);

//        for (int i=0;i<bestSellerList.size();i++){
//            if (bestSellerList.get(0).getNumberOfSales()== bestSellerList.get(1).getNumberOfSales()){
//                bsArray.add(bestSellerList.get(0));
//                bsArray.add(bestSellerList.get(1));
//            }
//            if (bestSellerList.get(0).getNumberOfSales()== bestSellerList.get(1).getNumberOfSales()){
//                bsArray.add(bestSellerList.get(0));
//                bsArray.add(bestSellerList.get(1));
//            }
//        }

        return bestSellerList.get(0);
    }


}
